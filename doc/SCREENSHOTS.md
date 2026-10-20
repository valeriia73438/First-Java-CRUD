# Screenshot checklist

Only save actual screenshots. Do not draw replacement screens or fabricate GitHub history.
On macOS use Shift-Command-4, then Space for a window, or select the relevant area.
Move the resulting PNG into this directory with the names below.

- [ ] `01-application.png`: open http://localhost:8080 with the heading and table visible.
- [ ] `02-create.png`: add a temporary student and capture the new row and success notification.
- [ ] `03-read.png`: show the table with all student columns.
- [ ] `04-update.png`: select the temporary student, change Course, save, and capture the updated row.
- [ ] `05-delete.png`: capture the confirmation dialog; confirm deletion and optionally add `05-delete-result.png`.
- [ ] `06-startup-logs.png`: show the actual terminal containing the successful Spring Boot startup and port 8080.
- [ ] `07-github-both-contributors.png`: after the partner PR is reviewed and merged, open
  https://github.com/valeriia73438/First-Java-CRUD/commits/main/ and capture both account-linked authors.

Also check that an empty form and an invalid email cannot be saved, and that Cancel
in the delete dialog preserves the record. Do not include credentials or private browser tabs.
Actual screenshots captured during implementation are listed in VERIFICATION.md.
