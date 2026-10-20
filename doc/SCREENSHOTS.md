# Screenshot checklist

Only save actual screenshots. Do not draw replacement screens or fabricate GitHub history.
On macOS use Shift-Command-4, then Space for a window, or select the relevant area.
Move the resulting PNG into this directory with the names below.

- [x] `01-application.jpg`: open http://localhost:8080 with the heading and table visible.
- [x] `02-create.jpg`: add a temporary student and capture the new row and success notification.
- [x] `03-read.jpg`: show the table with all student columns.
- [x] `04-update.jpg`: select the temporary student, change Course, save, and capture the updated row.
- [x] `05-delete.jpg`: capture the confirmation dialog; confirm deletion and optionally add `05-delete-result.jpg` (captured).
- [ ] `06-startup-logs.png`: show the actual terminal containing the successful Spring Boot startup and port 8080.
- [ ] `07-github-both-contributors.png`: after the partner PR is reviewed and merged, open
  https://github.com/valeriia73438/First-Java-CRUD/commits/main/ and capture both account-linked authors.

Also check that an empty form and an invalid email cannot be saved, and that Cancel
in the delete dialog preserves the record. Do not include credentials or private browser tabs.

- [x] `08-validation.jpg`: genuine empty-form validation errors.

The automated browser captures are JPEG files. The terminal screenshot remains manual
because computer-use access to Terminal was denied. Capture it using the checklist above.
