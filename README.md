### Environment Setup
- Provide username and password details in application.properties file (calendar-jn\src\main\resources\application.properties)
- In frame.properties file (calendar-jn\src\main\resources\frame.properties) provide browser name on which the tests has to be run (default set to 'chrome'). 
- Download and include latest ChromeDriver and IEDriverServer in your PATH environment variable.
- Make sure you have java 1.8 installed and setup in you system 
- Download and setup Apache Maven 3.1.1 or higher version.

### Pre-Requisites
- Login to application manually before running the test cases to handle first time Alerts

### File Details
- HTMLElements.java: Implements WebElement interface to create WebElements directly using locators defined in  properties file.
- Application.java: contains all application related operations like launching and gracefully closing the application.
- Driver.java: Handles initializing WebDriver and maintaining its instance.  
- OR.properties: Object repository containing all locators information
- GoogleTest.java:  Contains all test cases. 

### Test Execution
- Download this project by clicking 'Download ZIP' button.
- Unzip the project and go to 'calender-jn\src\main\resources' to add Google username and password in application.properties.
- Then in the command prompt cd to 'calendar-jn' project folder where pom.xml is located.
- Run this command,
    ``` 
    mvn clean compile test -Dsuite=testng.xml surefire-report:report-only 
    ```
    To view the surefire report to go '\target\site\surefire-report.html' 
- Alternatively, import this project as maven project into Eclipse and run the testng.xml as TestNG suite.
