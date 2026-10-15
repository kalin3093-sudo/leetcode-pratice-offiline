import {
    discoverCourses,
    loadProblemSummaries,
    orderProblemsForCourse,
    selectCourse
} from '$lib/server/courseCatalog';
import {
    ensureUserContentSeeded,
    getBundledCoursesDir,
    getBundledProblemsDir,
    getContentRoot,
    getCourseSources,
    getCoursesDir,
    getProblemsDir,
    getProblemSources
} from '$lib/server/contentPaths';
import fs, { existsSync } from 'node:fs';
import type { PageServerLoad } from './$types';

async function resolveContentDir(userDir: string, bundledDir: string): Promise<string> {
    await ensureUserContentSeeded();
    return existsSync(userDir) ? userDir : bundledDir;
}

async function computeHomeBase() {
    const coursesDir = await resolveContentDir(getCoursesDir(), getBundledCoursesDir());
    const problemsDir = await resolveContentDir(getProblemsDir(), getBundledProblemsDir());
    // Merge user (~/cojudge) and bundled content so newly shipped problems
    // still appear even before the next seed pass copies them over.
    const [userCourses, bundledCourses, userProblems, bundledProblems] = await Promise.all([
        discoverCourses(coursesDir),
        coursesDir === getBundledCoursesDir() ? Promise.resolve([]) : discoverCourses(getBundledCoursesDir()),
        loadProblemSummaries(problemsDir),
        problemsDir === getBundledProblemsDir() ? Promise.resolve([]) : loadProblemSummaries(getBundledProblemsDir())
    ]);
    const courses = [...userCourses];
    for (const c of bundledCourses) {
        if (!courses.some((existing) => existing.id === c.id)) courses.push(c);
    }
    const allProblems = [...userProblems];
    for (const p of bundledProblems) {
        if (!allProblems.some((existing) => existing.id === p.id)) allProblems.push(p);
    }
    courses.sort((a, b) => a.id.localeCompare(b.id));
    // Label user content: `custom` (only in ~/cojudge) or `modified`
    // (differs from the bundled copy) so the UI can badge overrides.
    const [courseSources, problemSources] = await Promise.all([
        getCourseSources(courses.map((course) => course.id)),
        getProblemSources(allProblems.map((problem) => problem.id))
    ]);
    return { courses, allProblems, courseSources, problemSources };
}

// ponytail: home load scans ~35k files (summaries + source badges for all
// 2941 problems) — that scan is course-independent, so cache it as one base
// and invalidate via fs.watch; only the cheap per-course selection runs per
// navigation, making course-tab switches instant too.
type HomeBase = Awaited<ReturnType<typeof computeHomeBase>>;
let base: HomeBase | null = null;
let watchersStarted = false;

function startContentWatchers(): void {
    if (watchersStarted) return;
    watchersStarted = true;
    for (const dir of [getContentRoot(), getBundledProblemsDir(), getBundledCoursesDir()]) {
        try {
            fs.watch(dir, { recursive: true }, () => {
                base = null;
            });
        } catch {
            // dir may not exist; content edits then only refresh on restart
        }
    }
}

export const load: PageServerLoad = async ({ url }) => {
    if (!base) {
        base = await computeHomeBase();
        startContentWatchers();
    }
    const { courses, allProblems, courseSources, problemSources } = base;
    const selectedCourse = selectCourse(courses, url.searchParams.get('course'));
    const problems = selectedCourse
        ? orderProblemsForCourse(
            selectedCourse.info,
            allProblems.map((problem) => ({ ...problem, source: problemSources[problem.id] ?? 'bundled' }))
        )
        : [];

    return {
        courses: courses.map((course) => ({ id: course.id, title: course.info.title, source: courseSources[course.id] ?? 'bundled' })),
        selectedCourseId: selectedCourse?.id ?? null,
        selectedCourseInfo: selectedCourse?.info ?? null,
        problems,
        // Every modified item (user copy differs from bundled) across all
        // courses, so Manage Problems can offer a reset-to-bundled recovery.
        // Needed because `problems` only covers the selected course.
        modifiedProblems: allProblems
            .filter((problem) => (problemSources[problem.id] ?? 'bundled') === 'modified')
            .map((problem) => ({ id: problem.id, title: problem.title })),
        modifiedCourses: courses
            .filter((course) => (courseSources[course.id] ?? 'bundled') === 'modified')
            .map((course) => ({ id: course.id, title: course.info.title })),
        // Absolute path of the user-editable content folder (~/cojudge by
        // default), shown in the "Manage Problems" popup.
        contentDir: getContentRoot()
    };
};
