# Assignment 1 - Group 15 - Report

### Members

- Francis Kloskowski Gniady
- Markus Wessén
- David Aldenbro
- Zeynep Ebrar Karadeniz

---

## Essence Statement

We have identified that we have completed the first four stages, and are currently in the firth stage, "Working well". We have not yet fulfilled the 2nd ("The team naturally applies the practices without thinking about them."), 3rd ("The tools naturally support the way that the team works."), and 4th ("The team continually tunes their use of the practices and tools.") items in the "Working well" stage.

## Workflow

### Work division

We created an issue for each LIC, as well as for a few common functions shared among multiple LICs. Like the area of a triangle. We also created issues for the main functions like `PUM`, `CMV`, `FUV`, `DECIDE`.

At the start we vaguely divided most of the issues, and then as more issues were added, we assigned ourselves to the issues we were/planed to work on.

### Forks

Each person has their own for of the main repository that they can work in.

### Committing changes

All changes to the main repository are made through pull requests, each pull request gets reviewed and approved by at least one person before merging. Each pull request is linked to an issue that it addresses.

### CI

We have a github actions workflow to run our tests and ensure the code can be compiled on each pull request. We do not merge a pull request if CI does not pass.

### Commit message conventions

We use commit message conventions (`<type>: <message>`) to make it easier to understand what type of commit it is, for example `feat: add X feature` or `fix: fix bug Y`.
