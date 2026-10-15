#!/usr/bin/env node
/**
 * Bulk-fetch LeetCode problem data into a local JSON cache (NOT the repo).
 *
 * Usage:
 *   node scripts/bulk-fetch.mjs [--limit N] [--only slug1,slug2] [--delay MS] [--out DIR]
 *
 * Output: <cacheDir>/<slug>.json  — { question: {...} } raw GraphQL question node
 * Resumable: existing files are skipped unless --force.
 */
import fs from "node:fs/promises";
import path from "node:path";
import os from "node:os";

const args = process.argv.slice(2);
function arg(name, fallback) {
  const i = args.indexOf(`--${name}`);
  return i !== -1 && args[i + 1] ? args[i + 1] : fallback;
}
const LIMIT = parseInt(arg("limit", "0"), 10) || 0;
const ONLY = arg("only", "") ? arg("only", "").split(",") : null;
const DELAY = parseInt(arg("delay", "200"), 10);
const OUT = arg("out", path.join(os.tmpdir(), "lc-cache"));
const FORCE = args.includes("--force");
const CONCURRENCY = parseInt(arg("concurrency", "3"), 10);

const UA =
  "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36";

const QUESTION_QUERY = `
query questionData($titleSlug: String!) {
  question(titleSlug: $titleSlug) {
    questionId
    questionFrontendId
    title
    titleSlug
    content
    difficulty
    isPaidOnly
    topicTags { name }
    codeSnippets { lang langSlug code }
    exampleTestcases
    hints
    metaData
  }
}`;

async function fetchJson(url, opts = {}) {
  const res = await fetch(url, {
    ...opts,
    headers: { "User-Agent": UA, ...(opts.headers || {}) },
  });
  if (!res.ok) throw new Error(`HTTP ${res.status} for ${url}`);
  return res.json();
}

async function fetchQuestion(slug) {
  const res = await fetch("https://leetcode.com/graphql", {
    method: "POST",
    headers: { "Content-Type": "application/json", "User-Agent": UA },
    body: JSON.stringify({ query: QUESTION_QUERY, variables: { titleSlug: slug } }),
  });
  const text = await res.text();
  if (res.status === 403) throw new Error(`HTTP 403 (blocked) for ${slug}`);
  if (!res.ok) throw new Error(`HTTP ${res.status} for ${slug}: ${text.slice(0, 200)}`);
  const json = JSON.parse(text);
  if (json.errors) throw new Error(`GraphQL errors for ${slug}: ${JSON.stringify(json.errors).slice(0, 200)}`);
  return json.data?.question ?? null;
}

function sleep(ms) {
  return new Promise((r) => setTimeout(r, ms));
}

async function main() {
  await fs.mkdir(OUT, { recursive: true });
  let slugs;
  if (ONLY) {
    slugs = ONLY;
  } else {
    const idx = await fetchJson("https://leetcode.com/api/problems/all/");
    slugs = idx.stat_status_pairs.map((p) => p.stat.question__title_slug);
    // cache the index too for later stages
    await fs.writeFile(path.join(OUT, "_index.json"), JSON.stringify(idx));
  }
  if (LIMIT) slugs = slugs.slice(0, LIMIT);

  const stats = { ok: 0, nullq: 0, err: 0, skipped: 0 };
  const failures = [];
  let done = 0;

  async function worker(queue) {
    for (;;) {
      const slug = queue.shift();
      if (!slug) return;
      const file = path.join(OUT, `${slug}.json`);
      if (!FORCE) {
        try {
          await fs.access(file);
          stats.skipped++;
          done++;
          continue;
        } catch {}
      }
      let attempt = 0;
      for (;;) {
        try {
          const q = await fetchQuestion(slug);
          await fs.writeFile(file, JSON.stringify({ question: q }));
          if (q) stats.ok++;
          else stats.nullq++;
          break;
        } catch (e) {
          attempt++;
          if (attempt >= 4) {
            stats.err++;
            failures.push(`${slug}: ${e.message}`);
            break;
          }
          await sleep(1000 * attempt * 2);
        }
      }
      done++;
      if (done % 100 === 0) console.log(`[${done}/${slugs.length}] ok=${stats.ok} null=${stats.nullq} err=${stats.err} skip=${stats.skipped}`);
      await sleep(DELAY);
    }
  }

  const queue = [...slugs];
  await Promise.all(Array.from({ length: CONCURRENCY }, () => worker(queue)));

  console.log("DONE", JSON.stringify(stats));
  if (failures.length) {
    console.log("FAILURES:");
    for (const f of failures.slice(0, 50)) console.log("  " + f);
    await fs.writeFile(path.join(OUT, "_failures.txt"), failures.join("\n"));
  }
}

main().catch((e) => {
  console.error(e);
  process.exit(1);
});
