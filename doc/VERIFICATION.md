# Assignment verification

Date: 20 October 2026. This report distinguishes local work from GitHub collaboration.

## Implemented

- Java 21 application with Spring Boot, Vaadin, Spring Data JPA, and H2.
- Generated student ID, first name, last name, email, and course.
- Student table, add/edit form, validation, notifications, and confirmed deletion.
- File-based local database and three example students when the database is empty.
- Maven Wrapper, integration tests, README, PR template, and a partner task.
- Project structure is described in README.md.

## Environment and Git

The actual clone is `First-Java-CRUD/` inside the original working directory.
Its origin is `https://github.com/valeriia73438/First-Java-CRUD.git`.
The outer workspace belongs to a separate repository and was not reconfigured.

Initially Java 25 was installed and Maven was absent. A checksum-verified Eclipse
Temurin Java 21 JDK and Maven were downloaded to `/tmp` for verification.
The JDK is temporary and is not installed system-wide. For normal use install Java 21
and follow README.md. No partner identity was used to create commits.

The repository-only identity is `valeriia73438 <work0372@gmail.com>` as explicitly supplied
by the owner. Local commit author and committer fields use that identity.
GitHub account attribution cannot be verified until the commits have been pushed.

The initial push was attempted and failed with:

```text
remote: Permission to valeriia73438/First-Java-CRUD.git denied to kkkerber.
fatal: ... The requested URL returned error: 403
```

The GitHub connector is also signed in as kkkerber and reports no push permission.
The owner must authenticate as valeriia73438; do not change commit authors to bypass this.

## Checks

Build, test, startup, and screenshot results will be recorded here after execution.

## Collaboration status

| Step | Status |
| --- | --- |
| Implement initial application | Complete locally |
| Owner Git identity configured locally | Complete |
| Owner implementation commits | Created locally; see `git log` |
| Push initial application to main | Blocked by GitHub authentication (403) |
| Small partner task and PR instructions | Complete: Clear form button |
| Genuine 73952id implementation and commit | Pending partner action |
| Partner feature branch and PR | Pending partner action |
| Owner review and merge | Pending owner action; no automatic merge |
| Both accounts verified in GitHub main history | Pending push and reviewed partner merge |
| GitHub history screenshot in doc/ | Pending both students' contributions |
| Owner PR to partner's repository | Pending owner action |

See COLLABORATION.md for the workflow. No partner contribution, PR, review, merge,
or university submission has been fabricated or marked complete.

## Finish publishing after authentication

From this clone, authenticate GitHub CLI as the repository owner, then push:

```sh
gh auth login -h github.com
gh auth status
git config --local --get user.name
git config --local --get user.email
git remote -v
git push -u origin main
```

Open the owner commits on GitHub and verify the author links to valeriia73438.
If the email is not associated with that GitHub account, the owner must resolve the
account email association. A local Git name alone does not prove GitHub attribution.
Then pass COLLABORATION.md to the partner and complete the remaining table entries
with real PR links and screenshot evidence.
