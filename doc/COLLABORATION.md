# Two-student collaboration

Repository: https://github.com/valeriia73438/First-Java-CRUD

| Student | GitHub account | Commit email supplied by the student |
| --- | --- | --- |
| Owner | valeriia73438 | work0372@gmail.com |
| Partner | 73952id | mliuten1@stu.vistula.edu.pl |

Each student must work through their own GitHub account. An email in a Git commit alone
is not proof of account attribution: GitHub must associate it with the student's account.
Do not use co-author trailers or change the owner checkout's identity to simulate teamwork.

## Task for 73952id: Clear form button

The partner's change must be reviewed before it is merged into main.

Add a **Clear form** button beside Save and Delete in `StudentView.java`.
It must clear all four inputs, deselect any row, switch the heading to Add student,
and disable Delete. It must not save or delete a database record.
Use the existing `edit(null)` method, and follow the existing button style.

Acceptance checks:

1. Select an existing student and change the course without saving.
2. Click Clear form. Inputs must be empty, no row selected, and Delete disabled.
3. Select the same student again. The original course must still be present.
4. Run `./mvnw clean test` and capture a genuine screenshot of the new button.
5. Describe the change and manual checks in the PR.

## Partner: branch and Pull Request

The owner can invite 73952id through repository Settings → Collaborators.
If direct push access is unavailable, the partner should fork the repository in their own account.
Create the feature branch from the latest main.

In the partner's own clone only:

```sh
git clone https://github.com/valeriia73438/First-Java-CRUD.git
cd First-Java-CRUD
git config --local user.name 73952id
git config --local user.email mliuten1@stu.vistula.edu.pl
git switch main
git pull --ff-only origin main
git switch -c codex/clear-student-form
# The partner now implements and checks the task themselves.
./mvnw clean test
git add src/main/java/pl/edu/vistula/students/StudentView.java doc/
git commit -m "Add Clear form button to student editor"
git push -u origin codex/clear-student-form
```

For a fork, clone the fork URL instead. Open a PR in GitHub with base
`valeriia73438/First-Java-CRUD:main` and compare the partner's feature branch.
Use the title **Add Clear form button to student editor**.

## Owner: review and merge

Do these steps personally, while signed in as valeriia73438:

- Read the Files changed tab and confirm the partner authored the work.
- Run the branch locally and complete the acceptance checks above.
- Review the test output and leave your actual review on GitHub.
- Use **Create a merge commit** to preserve the partner's original commit in main.
- Do not enable automatic merging. Do not squash the partner's work into an owner-authored commit.

After merging:

```sh
git switch main
git pull --ff-only origin main
git log --format='%h %an <%ae> %s' --all
```

On GitHub open the main branch commit history. Click each student's commit and check
that GitHub links its author to the expected account. Both commits must be reachable
from main. Save a real screenshot as `doc/07-github-both-contributors.png` only after
both students' work is visible, then commit and push the evidence as the owner.
A local author name or the Contributors summary alone does not complete this check.

## Owner contribution to the partner repository

Partner repository: [73952id/First-Java-CRUD](https://github.com/73952id/First-Java-CRUD).
This is a separate Java console application.

The owner's contribution is [PR #1: Add exit confirmation to customer manager](https://github.com/73952id/First-Java-CRUD/pull/1),
authored by `valeriia73438`. It adds confirmation before exiting and documents the behavior.
GitHub confirms that this PR was **merged on 20 October 2026**.

## Collaboration status

Checked on GitHub on 20 October 2026:

- Initial owner push: complete; GitHub links the initial commits to valeriia73438.
- Incoming partner PR: [PR #1: Add Clear form button to student editor](https://github.com/valeriia73438/First-Java-CRUD/pull/1),
  authored by `73952id`; merged by `valeriia73438` on 20 October 2026.
- Owner merge: complete. No formal GitHub PR review is recorded; this does not verify
  whether the owner performed the manual review checks above.
- Both GitHub author links verified on main: complete; GitHub links the initial owner
  commits to `valeriia73438` and the Clear form commit to `73952id`.
- Commit-history screenshot: saved in `doc/07-github-both-contributors.png`; supplied by the owner.
- Owner PR to partner repository: complete; the exit confirmation PR linked above is merged.
