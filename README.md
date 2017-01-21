# Amazon Online Demo Tests
Automated Demo Tests using Java +TestNG + Selenium WebDriver + Maven 

## Introduction
This project is to demonstrate creating a test framework from scratch using the following stack:

1. Programming Language: JAVA
2. IDE Used: Eclipse
3. Browser Driver Tool: Selenium WedDriver
4. Testing framework / Style of Writing Tests: TestNG
5. Build/Dependency Management Tool: Maven
6. Application Under Test : http://www.amazon.in 

## Requirements
* Java 8
* Maven 3.3

## Usage
`mvn test` to run tests

`mvn clean test` will remove the target directory, all the compiled resources, reports and run tests. 

### Cross browser
`mvn test -Dbrowser=firefox`

Options are:

* firefox (default)
* chrome (not supported yet) 
* ie (not supported yet)

Driver executable locations are stored in `config.properties`. Hack as necessary.

### Site url
`mvn test -Durl=http://www.amazon.in`
