# Automation Assignment

## Project Tools Used

- **Programming Language:** Java (JDK 1.8)
- **Framework and Tools Used:** Selenium (*Version: 4.3.0*), TestNG (*Version: 7.6.1*), JUnit (*Version: 4.13.2*), Maven
- **IDE:** Intellij Idea

## Project Structure

1. The project has been implemented following POM (Page Object Model) structure. The page objects are implemented inside the package **com.assignment.pages**.
2. The test classes are implemented inside the package ***com.assignment.tests***.
3. A configuration class is maintained to initialize the user controlled variables like user name, email and password.

## Prerequisites

- You need to have the following installations in your machine:
    - JDK
    - Maven
    - Any IDE of your choice that supports Java
- A browser of your choice to check the HTML report
- For windows machine, you will need the following added to your system environment variables:
    - JDK
    - Maven
    
## Implementation Details

The test steps and corresponding validations are as mentioned below:

Steps | Validation Points |
--- | --- | 
Signin to the platform using the provided credentials | 1. Validation completed using the user name found. |
Open the dashboard | 1. Validation completed using the dashboard title. |
Open Lists UI | 1. Validation completed using the UI title. |
Add a new List | 1. Success toast for adding new list displayed. <br> 2. Validate the content of the success toast. <br> 3. Validate the added term is displayed. |
Open Data View UI | 1. Validation completed using the UI title. |
Complete the Blacklist operation | 1. Validate the background color change of the row that has been blacklisted. |

We are also capturing screenshots which are stored in *{project_root}/screenshots* after each test case execution.

## Project Import and How to Run

1. Clone the project from GitHub or download the project and unzip it.
2. Open your IDE and import the project as a *maven project*.
3. Open the **config.properties** file from the path: *src/main/resources* and set the required variables here. Set your email and password for test.
4. Open the **testng.xml** file from *resources* path and run the tests as TestNG tests.
5. Right now, for integration with CI/CD pipeline we have enabled headless run configuration, to run in non headless mode, please comment out ***line number 42*** in the class ***BaseTest.java*** from *{project_root}/src/test/java/com/assignment/tests/*
6. Alternatively can run the project from command prompt or terminal by executing the following command from your *{project_root}*:

         mvn clean test -DsuiteXmlFile=testng.xml -Dbrowser=chrome

## Result Observation:

1. You can check the test cases executed from your IDE console.
2. An HTML file is also generated after the execution. It is named **index.html** and can be found in path: *test-output/html*.
3. You can see each test case result by checking the captured screenshots from *{project_root}/screenshots*.

## Future Improvements

1. Currently we only have Chrome browser support, we can extend it to other browsers.
2. Test cases have not been written in *BDD* format, we can add any *Gherkin* framework to achieve that.
3. In the CI/CD pipeline added with *CircleCI* we do not have any artifacts defined yet which can be added.
4. Logback support is not added yet.
