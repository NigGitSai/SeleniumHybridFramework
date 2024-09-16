This framework is a Selenium Java Hybrid Page Object Model (POM) designed for automated testing of Demo Web Shop web application. 
It leverages Maven as the build tool, Apache POI for handling Excel data, TestNG for test execution, Extent Reports for reporting, and Log4j for logging. 
The framework supports parameterized execution with Jenkins, enabling flexible test runs based on runtime inputs.

Key Features
Selenium WebDriver: For browser automation.
Hybrid POM: A combination of POM and data-driven approaches for robust test design.
Maven: Used as the build and dependency management tool.
Apache POI: For reading and writing data from Excel files.
TestNG: For test execution and management.
Extent Spark Reports: For generating comprehensive and visually appealing test reports.
Log4j: For logging the execution details.
Jenkins Integration: Supports parameterized execution triggered via Jenkins.

├── src
│   ├── main
│   │   ├── java
│   │   │   ├── Constants                     # Application Constants like Home page Title, URL
│   │   │   ├── Factory                       # Driver initialization and Report class configuration 
│   │   │   ├── pages                         # Page classes
│   │   │   ├── utils                         # Utility classes (e.g., Excel reader, Generic UI functions)
│   ├── test
│   │   ├── java
│   │   │   ├── Base                          # Excel workbook Set up, Report Setup, Browser Invocation , Application URL Navigation , teardown method
│   │   │   ├── testcases                     # TestNG Tests
│   └── resources
│   │   │   ├── config                        # config.properties
│   │   │   ├── TestData                      # Test data files (e.g., Excel files)
│   │   │   ├── runner                        # TestNG xml files
├── Reports                                   # Test execution Extent Reports
├── pom.xml
└── README.md
