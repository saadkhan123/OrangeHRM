📘 Selenium + RestAssured Automation Framework
This project is a Test Automation Framework built using Java, TestNG, Selenium WebDriver, and RestAssured.
It is structured following the Page Object Model (POM) design pattern and supports UI + API testing, parallel
execution, and separate reporting.

🔧 Key Features
✅ UI Test Automation (Selenium)
- Built using TestNG and Selenium WebDriver

- Implements the Page Object Model (POM) pattern

- Uses UUID to generate unique test data (usernames, passwords, names)

- Integrated with ExtentReports for detailed HTML reporting

- Custom utilities for Waits, Screenshot capture, Delayed execution

- Automated workflow for:

    - Login

    - Navigating to PIM section

    - Adding an employee

    - Verifying employee records

    - Logging out
  

✅ API Test Automation (RestAssured)
Integrated RestAssured for REST API testing

- Tests cover:

    - GET /users?page=2 – Validate status, list size, and response structure

    - POST /users – Create user with data-driven input, validate response

    - DELETE /users/{id} – Simulated deletion, validate status

- Uses data providers to run POST requests with multiple payloads

- Validates status codes, response bodies, and schema consistency

- Separate reporting via ExtentReports


🔄 Parallel & Separate Execution
- Test execution is controlled through TestNG XML configuration

- Tests are grouped as:

    - UI Tests: Inherit from BaseUiTest

    - API Tests: Inherit from BaseApiTest

- Execution order:

    - UI tests run first, followed by API tests

- Configured for parallel execution if needed


🧪 Base Test Classes
BaseUiTest.java

- Handles:

    - WebDriver initialization based on browser parameter (Chrome, Firefox, Edge)

    - Setting up WebDriverWait, listeners, implicit waits

    - Launching browser and managing teardown

    - Initializes ExtentReports for UI test logging


BaseApiTest.java

- Handles:

    - Setup for RestAssured configurations

    - Centralized ExtentReport handling for API tests

    - Common setup/teardown logic if required


🧾 Reporting

- Two separate HTML reports are generated:

    - UITestReport.html for Selenium-based UI tests

    - APITestReport.html for RestAssured-based API tests

- Reports are created using ExtentReports

- Configured to overwrite previous reports on each run

- Includes detailed logging (test steps, assertions, responses)


🚀 Test Flow (UI)

1) Login with valid credentials and validate with “Dashboard” text

2) Navigate to PIM section

3) Click Add and enter: First name, Last name

4) Enable Login Details

5) Enter unique username, password

6) Save the user

7) Capture and verify firstName using EmployeeId from record table

8) Logout and verify login screen appears