# TMDb Search Automation Framework

This project implements various search techniques on The Movie Database (TMDb) webpage. It is designed to automate searches, validate data-driven results, and demonstrate best practices in test automation. Goal was to try and use different techniques for some basic searches

## Key Features

### Search Field Interaction
- **Automated Searches:** Leverage the search field on the TMDb website to perform automated searches.
- **Dynamic Interaction:** Ensure user-like interaction to simulate real-world search scenarios.

### Data-Driven Testing
- **JSON Driven Testing:** Read JSON files containing expected search results to verify the accuracy of the search functionality.
- **YAML Mapping:** Utilize YAML files to map movie names to URLs, making the tests flexible and easy to update.

### DataTable Examples
- **Cucumber DataTables:** Use Cucumber DataTables for table-driven tests, allowing for easy modification and extension of test scenarios.

### Maven & JUnit 5
- **Dependency Management:** Use Maven to manage project dependencies.
- **Test Execution:** Implement tests using JUnit 5 to ensure a modern and efficient testing environment.

### Page Object Model (POM)
- **Enhanced Maintainability:** Utilize the Page Object Model to improve test maintainability and readability.
- **Page Factory & @FindBy:** Demonstrate the use of Page Factory and `@FindBy` annotations in the Login class.
- **Further improvements:** Additional pages can be used following POM for improved reusability if more time.

### WireMock for Mocking Data
- **Simulated API Responses:** Use WireMock to simulate API responses.
- **Mock Data Serving:** Serve a basic `index.html` page that mocks data for testing purposes, ensuring isolation from external dependencies.

## Getting Started


### Installation and running
1. unzip the QATask.zip
2. run 'mvn clean install' within QATask root
3. dependcies should be installed and all tests with @smoke tag should run

