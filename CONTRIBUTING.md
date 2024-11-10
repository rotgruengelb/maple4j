# Contributing to Maple4J

Thank you for your interest in contributing to Maple4J! Contributions are welcome from everyone, whether you are a new
or experienced developer. This document is here to help you get going.

## Getting Started

1. **Fork and Clone the Repository**  
   Start by [forking the repository](https://github.com/rotgruengelb/maple4j/fork) and then cloning it to your local
   machine:
   ```bash
   git clone https://github.com/username/maple4j.git
   ```

2. **Setting Up the Project**  
   Ensure you have Java 21 and Gradle installed. To set up all dependencies and generate grammar sources, use:
   ```bash
   ./gradlew build
   ```
   We recommend using IntelliJ IDEA as the IDE for development.

3. **Create a New Branch**  
   Always create a new branch for your changes:
   ```bash
   git checkout -b feature/your-feature-name
   ```
   Makes sure that your branch name is descriptive. Use an appropriate prefix like: `feature/` for new features, `fix/`
   for bug fixes, and `docs/` for documentation-only changes.

## Project Structure

- **`src/main/java/net/rotgruengelb/maple`** - Core library classes, including `Maple` and `MapleParse`.
- **`src/main/antlr`** - ANTLR Lexer and Parser grammar files.
- **`src/main/java/net/rotgruengelb/maple/internal/generated`** - Auto-generated files from the ANTLR grammar generated
  from the ANTLR grammar in `src/main/antlr`.
- **`src/test`** - Unit tests.
- **`build.gradle.kts`** - Gradle build configuration.

## Coding Guidelines

- **Code Style**  
  Follow standard Java conventions. Keep the code consistent and readable.

- **JavaDoc**  
  Include JavaDoc comments for public methods and classes to improve clarity.

- **JetBrains Annotations**  
  We encourage using JetBrains annotations (e.g., `@NotNull`, `@Nullable`) to make nullability explicit and improve code
  quality.

## Reporting Issues and Suggesting Features

- **Feature Requests and Bug Reports**  
  Please use the [GitHub Issues page](https://github.com/rotgruengelb/maple4j/issues) to suggest new features or report
  bugs. Include as much detail as possible to help us understand your ideas or the issues you encountered.

- **Security Issues**  
  If you discover a critical security vulnerability, please do not open a public issue. Instead, email us immediately at
  maple4j@rotgruengelb.net with details.

## License Notice

Maple4J is licensed under the [MIT License](LICENSE). By contributing to this project, you agree that your contributions
will be licensed under the MIT License.

## Making Changes

1. **Add Tests**  
   For any new functionality or bug fixes, add corresponding unit tests in `src/test`.

2. **Run Tests**  
   Ensure all tests pass before submitting:
   ```bash
   ./gradlew test intTest
   ```

3. **Documentation**  
   Update documentation as needed for your changes. This includes examples in the README or Javadoc comments.

4. **Changelog Update**  
   Maple4J follows a structured [Keep a Changelog](https://keepachangelog.com/) format. For every PR, update the
   `CHANGELOG.md` file under the appropriate section (`Unreleased`) and add a relevant entry, such as:
    - **Added** - for new features.
    - **Changed** - for changes in existing functionality.
    - **Fixed** - for bug fixes.

   The CI workflow will enforce that `CHANGELOG.md` is updated for every PR. If it’s missing, the PR check will fail.

## Pull Request Guidelines

1. **PR Checklist Workflow**  
   Each PR will trigger the `PR Checklist` workflow, which verifies that `CHANGELOG.md` was updated. Please ensure it is
   updated before submitting your PR.

2. **Write Descriptive PR Titles**

3. **Fill Out the PR Template**  
   Provide details in the PR template, including the type of change, context, and tests you ran.

4. **Link Issues**  
   If your PR addresses an open issue, link it by mentioning the issue number, like `Resolves #42`.

5. **Avoid Unnecessary Commits**  
   Squash or clean up any unnecessary commits before opening a PR to keep the history concise.

## Publishing

Maple4J uses GitHub Actions for CI, including builds, code analysis, integration tests, and releases.

The workflow publishes snapshots to Sonatype OSSRH, releases to Maven Central, and both snapshots and releases to
[maven.rotgruengelb.net](https://maven.rotgruengelb.net).

## Code of Conduct

Please read our [Code of Conduct](CODE_OF_CONDUCT.md) to understand the behavior we expect from all contributors.

Thank you considering contributing to Maple4J!
