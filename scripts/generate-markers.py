#!/usr/bin/env python3
"""Wrap HF Java solutions into problems/<slug>/Marker.java for generated problems.

Existing dirs with Marker.java (the original 250) are never touched.
Report: %TEMP%/lc-marker-report.json
"""
import json
import os
import pathlib
import re
import sys

import pyarrow.parquet as pq

PROBLEMS = pathlib.Path("problems")
PARQUET = os.path.join(os.environ["TEMP"], "lc-solutions.parquet")
REPORT = os.path.join(os.environ["TEMP"], "lc-marker-report.json")

JAVA_TYPE = {
    "int": "int",
    "long": "long",
    "double": "double",
    "char": "char",
    "string": "String",
    "boolean": "boolean",
    "int_array": "int[]",
    "int_array_2d": "int[][]",
    "string_array": "String[]",
    "char_array": "char[]",
    "char_array_2d": "char[][]",
    "int_list": "List<Integer>",
    "int_list_2d": "List<List<Integer>>",
    "string_list": "List<String>",
    "string_list_2d": "List<List<String>>",
    "tree_node": "TreeNode",
    "list_node": "ListNode",
    "list_node_array": "ListNode[]",
    "graph_node": "GraphNode",
}

CMP = {
    "int": "{e} == {o}",
    "long": "{e} == {o}",
    "char": "{e} == {o}",
    "boolean": "{e} == {o}",
    "double": "Math.abs({e} - {o}) < 1e-5",
    "string": "{e}.equals({o})",
    "int_array": "Arrays.equals({e}, {o})",
    "string_array": "Arrays.equals({e}, {o})",
    "char_array": "Arrays.equals({e}, {o})",
    "int_array_2d": "Arrays.deepEquals({e}, {o})",
    "char_array_2d": "Arrays.deepEquals({e}, {o})",
    "int_list": "{e}.equals({o})",
    "int_list_2d": "{e}.equals({o})",
    "string_list": "{e}.equals({o})",
    "string_list_2d": "{e}.equals({o})",
    "tree_node": "sameTree({e}, {o})",
    "list_node": "sameList({e}, {o})",
    "list_node_array": "sameListArr({e}, {o})",
}

HELPERS = {
    "tree_node": """
    private static boolean sameTree(TreeNode a, TreeNode b) {
        if (a == null && b == null) return true;
        if (a == null || b == null) return false;
        if (a.val != b.val) return false;
        return sameTree(a.left, b.left) && sameTree(a.right, b.right);
    }
""",
    "list_node": """
    private static boolean sameList(ListNode a, ListNode b) {
        while (a != null && b != null) {
            if (a.val != b.val) return false;
            a = a.next;
            b = b.next;
        }
        return a == null && b == null;
    }
""",
    "list_node_array": """
    private static boolean sameList(ListNode a, ListNode b) {
        while (a != null && b != null) {
            if (a.val != b.val) return false;
            a = a.next;
            b = b.next;
        }
        return a == null && b == null;
    }
    private static boolean sameListArr(ListNode[] a, ListNode[] b) {
        if (a.length != b.length) return false;
        for (int i = 0; i < a.length; i++) if (!sameList(a[i], b[i])) return false;
        return true;
    }
""",
}


def build_marker(meta: dict, java_src: str):
    fn = meta["functionName"]
    ret = meta["outputType"]
    if ret not in JAVA_TYPE or ret not in CMP:
        return None, f"unsupported-return:{ret}"
    for p in meta["params"]:
        if p["type"] not in JAVA_TYPE:
            return None, f"unsupported-param:{p['type']}"
    src = java_src
    if not re.search(r"\bclass Solution\b", src):
        return None, "no-class-Solution"
    if not re.search(rf"\b{re.escape(fn)}\s*\(", src):
        # walkccc drift: case, typo, or renamed method — adopt when unambiguous
        pubs = [m for m in re.findall(r"public\s+[\w<>\[\], ]+\s+(\w+)\s*\(", src) if m != "Solution"]
        ci = [m for m in pubs if m.lower() == fn.lower()]
        if len(ci) == 1:
            src = re.sub(rf"\b{re.escape(ci[0])}\b", fn, src)
        elif len(pubs) == 1:
            src = re.sub(rf"\b{re.escape(pubs[0])}\b", fn, src)
        else:
            return None, "fn-not-found"

    # dataset upstream typos (present in the parquet source itself)
    src = src.replace("IllegalarrrgumentException", "IllegalArgumentException").replace("MIN_VnumsLUE", "MIN_VALUE")

    src = re.sub(r"\bclass Solution\b", "class Marker", src.strip(), count=1)
    # unconditional: walkccc sources may carry partial imports (e.g. stream-only), and
    # java.util.* alone never covers Collectors/IntStream/Function — duplicates are legal
    src = "import java.util.*;\nimport java.util.stream.*;\nimport java.util.function.*;\n" + src

    java_ret = JAVA_TYPE[ret]
    param_decls = ", ".join(f"{JAVA_TYPE[p['type']]} {p['name']}" for p in meta["params"])
    args = ", ".join(p["name"] for p in meta["params"])
    cmp_expr = CMP[ret].format(e="expected", o="output")
    helper = HELPERS.get(ret, "")

    is_correct = f"""
    public boolean isCorrect({param_decls}{', ' if param_decls else ''}{java_ret} output) {{
        {java_ret} expected = {fn}({args});
        return {cmp_expr};
    }}
"""
    i = src.rfind("}")  # Marker's closing brace — Pair must be appended AFTER this insert
    src = src[:i] + is_correct + helper + src[i:]
    if re.search(r"\bPair\b", src) and "class Pair" not in src:
        src += """
class Pair<K, V> {
    private final K key;
    private final V value;
    Pair(K key, V value) { this.key = key; this.value = value; }
    public K getKey() { return key; }
    public V getValue() { return value; }
}
"""
    if not src.endswith("\n"):
        src += "\n"
    return src, None


def main():
    args = sys.argv[1:]
    slugs_file = None
    force = "--force" in args
    if "--slugs-file" in args:
        slugs_file = pathlib.Path(args[args.index("--slugs-file") + 1])
    if force and slugs_file is None:
        print("refusing --force without --slugs-file (would overwrite original 250)", file=sys.stderr)
        return 1
    only = set(slugs_file.read_text().split()) if slugs_file else None

    table = pq.read_table(PARQUET)
    sols = {r["task_id"]: r["java"] for r in table.to_pylist() if r["task_id"] and r["java"]}

    written, skipped = 0, {}
    for d in sorted(PROBLEMS.iterdir()):
        if not d.is_dir():
            continue
        meta_path = d / "metadata.json"
        if not meta_path.exists():
            continue
        if only is not None and d.name not in only:
            continue
        if (d / "Marker.java").exists() and not force:
            continue  # original 250 — hand-written, never touched (unless explicitly forced)
        meta = json.loads(meta_path.read_text(encoding="utf-8"))
        java = sols.get(d.name)
        if not java:
            skipped.setdefault("no-java-solution", []).append(d.name)
            continue
        code, err = build_marker(meta, java)
        if err:
            skipped.setdefault(err, []).append(d.name)
            continue
        (d / "Marker.java").write_text(code, encoding="utf-8")
        written += 1

    report = {"written": written, "skipped_counts": {k: len(v) for k, v in skipped.items()}, "skipped": skipped}
    pathlib.Path(REPORT).write_text(json.dumps(report, indent=1), encoding="utf-8")
    print(json.dumps({"written": written, "skipped_counts": report["skipped_counts"]}, indent=1))


if __name__ == "__main__":
    sys.exit(main())
