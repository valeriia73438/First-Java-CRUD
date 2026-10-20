# Student Management System

First Java CRUD university assignment by **valeriia73438**, student ID **73438**.
A small Java web application for adding, viewing, editing, and deleting student records.

## Technologies

Java 21, Spring Boot 3.5.16, Vaadin 24.9.24, Spring Data JPA, H2, and Maven 3.9.11 (Wrapper).
The structure follows the [Spring CRUD with Vaadin guide](https://spring.io/guides/gs/crud-with-vaadin/).

## Run on macOS

Install a Java 21 JDK (for example, Eclipse Temurin). Maven does not need to be installed.
The first run needs internet access for Maven and Vaadin dependencies.

```sh
git clone https://github.com/valeriia73438/First-Java-CRUD.git
cd First-Java-CRUD
export JAVA_HOME=$(/usr/libexec/java_home -v 21)
export PATH="$JAVA_HOME/bin:$PATH"
./mvnw clean test
./mvnw spring-boot:run
```

Open [localhost:8080](http://localhost:8080). Stop the application with Control-C.
Vaadin may download Node.js and prepare its frontend on first startup.

## Use the application

- **Read:** the table shows the saved students and their generated IDs.
- **Create:** click **Add Student**, enter all four fields, then **Save**.
- **Update:** select a row, change its fields, then **Save**.
- **Delete:** select a row, click **Delete**, and confirm. Cancel keeps the record.
- Blank fields and invalid email addresses show validation errors. Successful changes show notifications.

Three example students are added when the database is empty. Records are stored in `data/students.mv.db`
and survive application restarts. The local database and generated files are ignored by Git.
Tests use a separate in-memory database.

## Project structure

```text
.mvn/wrapper/                   Maven Wrapper settings
mvnw, mvnw.cmd                  Maven launchers
pom.xml                         Dependencies and build
src/main/java/pl/edu/vistula/students/
  StudentApplication.java       Startup and example data
  Student.java                  Entity and validation
  StudentRepository.java        Database access
  StudentService.java           CRUD operations
  StudentView.java              Vaadin table and form
src/main/resources/             Application configuration
src/main/frontend/themes/      Interface styling
src/test/java/                  CRUD and validation tests
doc/                            Evidence and collaboration instructions
.github/                        Pull Request template
```

## Collaboration

Owner: **valeriia73438**. Partner: **73952id**.
See [COLLABORATION.md](doc/COLLABORATION.md) for the partner task, branch and PR commands,
owner review checklist, and reciprocal contribution instructions.
The partner must make their own change and commit it using their own account.
Their work is not included in the initial implementation.

See [SCREENSHOTS.md](doc/SCREENSHOTS.md) for screenshot evidence and
[VERIFICATION.md](doc/VERIFICATION.md) for the actual checks performed and remaining steps.
