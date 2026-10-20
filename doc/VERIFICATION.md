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
After the owner refreshed GitHub CLI authentication, `gh api user --jq .login`
returned `valeriia73438` and the repository permissions included `push: true`.
The initial push to main succeeded. GitHub's commits API links all seven initial
commits to **valeriia73438**, including email `work0372@gmail.com`.
The CLI uses the Mac's gh authentication; the GitHub connector has a separate session.
Global Git settings were not changed.

An earlier push failed with HTTP 403 while the CLI was using kkkerber. This was
resolved by the owner's account switch; it is no longer a publishing blocker.

## Checks

- `./mvnw --version`: Maven 3.9.11 running on Temurin Java 21.0.12.1.
- `./mvnw clean test`: **BUILD SUCCESS**, 5 tests, 0 failures, 0 errors, 0 skipped,
  with the final Vaadin 24.9.2 configuration on 20 October 2026 at 17:22 Warsaw time.
- Database tests cover create/read/update/delete, blank fields, invalid email,
  maximum field length, and avoiding duplicate sample data.
- `./mvnw spring-boot:run`: started successfully on localhost:8080 at 17:23:59.
  A real HTTP request returned status 200. See `startup-output.txt` for actual output.
- Browser: read the three sample students; rejected an empty form and invalid email;
  created Alexandra BrowserTest; updated the course from Intro Java to Advanced Java;
  cancelled deletion and confirmed the record remained; confirmed deletion and verified
  the row disappeared. Reloading the page retained the three original records.
- The temporary test student was removed; the original example students remain.
- `git diff --check`: passed.

The original Vaadin 24.9.24 maintenance release requested a commercial license during
startup. The final project uses the freely usable 24.9.2 release, which started without
that requirement. A previous process waiting for license validation retained the H2 file
lock after normal termination; terminating that specific process resolved the lock.
There is no outstanding startup error in the final application.

### Screenshots actually captured

- `01-application.jpg`: running application and complete form.
- `02-create.jpg`: newly created student in the table.
- `03-read.jpg`: table and sample students (same view as the application screenshot).
- `04-update.jpg`: changed course visible in the table.
- `05-delete.jpg`: actual delete confirmation dialog.
- `05-delete-result.jpg`: table after deletion.
- `08-validation.jpg`: empty-form validation messages.

Terminal screenshot access was denied by the computer-use tool. Actual startup and
successful test output are included as text in `startup-output.txt` and `test-output.txt`;
these are evidence excerpts, not screenshots. Capture the terminal screenshot manually
using SCREENSHOTS.md. The two-contributor GitHub screenshot cannot exist yet because
73952id has not contributed a merged PR.

### Run in this current environment

Java 21 was unpacked temporarily rather than installed system-wide. While these files
remain available, use:

```sh
cd /Users/valeriahapilina/vistula/java_crud/First-Java-CRUD
export JAVA_HOME=/tmp/java-crud-jdk/jdk-21.0.12.1+1/Contents/Home
export PATH="$JAVA_HOME/bin:$PATH"
export MAVEN_USER_HOME=/tmp/java-crud-wrapper-home
export MAVEN_OPTS=-Dmaven.repo.local=/tmp/java-crud-m2
./mvnw clean test
./mvnw spring-boot:run
```

The verified application is already running on port 8080; stop that instance before
starting another. For lasting setup, install Java 21 and follow README.md.

## Collaboration status

| Step | Status |
| --- | --- |
| Implement initial application | Complete locally |
| Owner Git identity configured locally | Complete |
| Owner implementation commits | Pushed to main; see `git log` and GitHub history |
| Push initial application to main | Complete; GitHub owner attribution verified |
| Small partner task and PR instructions | Complete: Clear form button |
| Genuine 73952id implementation and commit | Pending partner action |
| Partner feature branch and PR | Pending partner action |
| Owner review and merge | Pending owner action; no automatic merge |
| Both accounts verified in GitHub main history | Owner verified; partner pending reviewed merge |
| GitHub history screenshot in doc/ | Pending both students' contributions |
| Owner PR to partner's repository | Pending owner action |

See COLLABORATION.md for the workflow. No partner contribution, PR, review, merge,
or university submission has been fabricated or marked complete.

## Publish later owner changes

From this clone, verify the active account and push future owner commits:

```sh
gh auth status
git config --local --get user.name
git config --local --get user.email
git remote -v
git push -u origin main
```

Open the owner commits on GitHub and verify the author links to valeriia73438.
If the email is not associated with that GitHub account, the owner must resolve the
account email association. A local Git name alone does not prove GitHub attribution.
Pass COLLABORATION.md to the partner and complete the remaining table entries
with real PR links and screenshot evidence.
