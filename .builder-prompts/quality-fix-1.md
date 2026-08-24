Fix the Android project at /tmp/ultra-strategy-hub so the failing quality-fix-1 step passes.

Use these orchestrator instructions: /home/codex-agent/codex-app-agent/AGENTS.md
Screen spec: /home/codex-agent/codex-app-agent/screens-service.md
Do not push to GitHub, do not update Asana, and do not send Slack.
Fix formatting failures by expanding the affected Kotlin code; do not suppress or bypass the formatting checks.

Recent failure log:
```text
=== QUALITY CHECK: /tmp/ultra-strategy-hub ===

WARN: Only 1 commit(s) — final implementation commit may not exist yet
  OK: Repository: 12 entries
  PLACEHOLDER-LIKE: app/src/main/res/drawable/service_performance.jpg (colors=55458, entropy=0.674078)
  OK: 9 images
  OK: All images valid
FAIL: 1 placeholder-like drawable image(s); use real photos or filesystem-backed imagegen output, not local generated placeholders
  OK: No empty onClick
  OK: No obvious no-op onClick handlers
  OK: icon.png (273354B, 512x512, rounded opaque canvas, transparent corners)
  OK: Application class ServiceApplication exists
  OK: HomeScreen.kt: 236 lines
  OK: No project-local agent instruction files
  OK: dynamicColor not enabled
  OK: Google Fonts dependency found
FAIL: font_certs.xml missing
  OK: HorizontalPager used
  OK: No drawable resources detected in AsyncImage lines
  OK: Kotlin source formatting

=== RESULT: 2 error(s) ===
FIX ALL ISSUES BEFORE PUSH

```
