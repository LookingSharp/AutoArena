## Getting Started

Welcome to the VS Code Java world. Here is a guideline to help you get started to write Java code in Visual Studio Code.

## Folder Structure

The workspace contains the following folders:

- `src`: the folder to maintain sources
- `test/java`: the folder to maintain test sources
- `lib`: the folder to maintain dependencies (includes JUnit 4.13.2)
- `bin`: the folder for compiled output files

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Building and Running

### Compile the Project
```bash
javac -d bin src/*.java
```

### Run Tests
```bash
# Easy way - use the test runner script
./run_tests.sh

# Manual way - compile and run tests individually
javac -cp "bin:lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar" -d bin test/java/*.java
java -cp "bin:lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar" org.junit.runner.JUnitCore LlanowarElvesTest
```

## Testing

This project uses **JUnit 4** for unit testing. See [TESTING_GUIDE.md](TESTING_GUIDE.md) for:
- Comprehensive testing strategies
- How to write new tests
- Test coverage goals
- Examples and best practices

Current test coverage includes:
- ✅ C_Llanowar_Elves (14 tests)
- ✅ L_Forest (10 tests)
- ✅ L_Island (6 tests)
- ✅ C_Azure_Beastbinder (14 tests)
- ✅ C_Frenzied_Baloth (17 tests)
- ✅ Generic CREATURE tests (11 tests)

**Total: 72 passing tests**

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).

Current dependencies:
- JUnit 4.13.2 (testing framework)
- Hamcrest Core 1.3 (assertion library)
