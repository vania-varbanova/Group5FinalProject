# WEare Social Testing Project

This repository contains automated tests for the social media platform "WEare Social".

## Table of Contents

- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Running Tests](#running-tests)
- [Test Reports](#test-reports)


## Prerequisites

Before running the tests, ensure you have the following dependencies installed:

- [Postman](https://www.postman.com/downloads/)
- [IntelliJ IDEA](https://www.jetbrains.com/idea/download/)
- [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/javase-downloads.html)
- [Maven](https://maven.apache.org/download.cgi)
- [Selenium WebDriver](https://www.selenium.dev/downloads/)
- [Allure framework](https://allurereport.org/docs/)

## Installation

1. Clone the repository to your local machine: [git clone](https://github.com/vania-varbanova/Group5FinalProject).
2. Open the project in IntelliJ IDEA.
3. Set up the necessary configurations and dependencies in IntelliJ.
4. Install the required Postman collections.
5. Install any additional dependencies or plugins required for running the tests.

## Running Tests

### Postman Tests
1. Open Postman and import the collections located in the "postman" folder.
2. Set up environment variables as needed.
3. Run the collections individually or use the provided command files.

### Rest Assured Tests
1. Navigate to the RESTAssuredTests folder in IntelliJ using path - test -> weAreSocialNetwork.tests -> src -> test -> java -> RESTAssuredTests.
2. Right-click on the test suite and select "Run" or use the provided command files.

### Selenium WebDriver Tests
1. Navigate to the SeleniumTests folder in IntelliJ using path - test -> weAreSocialNetwork.tests -> src -> test -> java -> SeleniumTests.
2. Right-click on the test suite and select "Run" or use the provided command files.

### Executing Tests
1. Navigate to to the  test -> weAreSocialNetwork.tests
2. Execute run-tests.cmd
