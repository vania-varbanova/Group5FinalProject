# WEare Social Testing Project

This repository contains automated tests for the social media platform "WEare Social". The tests are written in Postman for API testing, Rest Assured for integration testing, and Selenium WebDriver for UI testing.

## Table of Contents

- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Running Tests](#running-tests)
- [Test Reports](#test-reports)

## Prerequisites
<a name="prerequisites"></a>
Before running the tests, ensure you have the following dependencies installed:

- [Postman](https://www.postman.com/downloads/)
- [IntelliJ IDEA](https://www.jetbrains.com/idea/download/)
- [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/javase-downloads.html)
- [Maven](https://maven.apache.org/download.cgi)
- [Selenium WebDriver](https://www.selenium.dev/downloads/)

## Installation
<a name="installation"></a>

1. Clone the repository to your local machine: [git clone](https://github.com/vania-varbanova/Group5FinalProject).
2. Open the project in IntelliJ IDEA.
3. Set up the necessary configurations and dependencies in IntelliJ.
4. Install the required Postman collections.
5. Install any additional dependencies or plugins required for running the tests.

## Running Tests
<a name="running-tests"></a>

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

## Test reports
<a name="test-reports"></a>

### Jira report
Jira report click [here.](https://wearesocialfinalproject.atlassian.net/plugins/servlet/ac/com.xpandit.plugins.xray/testplans-metrics-report-page?project.key=WSFP&project.id=10002&ac.reportId=6531398bbefe8bcc6d876476)

### Postman reports
Location - Group5FinalProject -> Reports -> Postman - open the already generated reports or or use the provided command files in order to generate new ones.

### REST and Selenium reports
Location - Group5FinalProject -> Reports -> REST and Selenium - Surefire reports are located in the surefire-reports folder. There is also a Allure report as an image file.