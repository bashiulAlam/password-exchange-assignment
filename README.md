# Selenium Automation Assignment

## Required Environment:

*Java 1.8*

### Language: Java
### Framework: Selenium, TestNG
### Build Tool: Maven
### IDE: IntelliJ Idea

## Project Structure:

1. The project has been implemented following POM (Page Object Model) structure. The page objects are implemented inside the package **com.assignment.pages**.
2. The test classes are implemented inside the package **com.assignment.tests****.
3. A configuration class is maintained to initialize the user controlled variables like user names, email and password.

## Project Import and How to Run:

1. Clone the project from GitHub or download the project and unzip it.
2. Open your IDE and import the project as a *maven project*.
3. Open the **config.properties** file from the path: *src/main/resources* and set the required variables here. Set your My Store e-commerce email and password for test.
4. Open the **testng.xml** file from *resources* path and run the tests as TestNG tests.

## Result Observation:

1. You can check the test cases executed from your IDE console.
2. An HTML file is also generated after the execution. It is named **index.html** and can be found in path: *test-output/html*.
