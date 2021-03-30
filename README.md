# Xeneta
Automation Testing Cucumber Project

Setup Used:
1.Eclipse IDE
2.Java 8
3.Selenium
4.Maven
5.Cucumber BDD Gherkins

Prerequisites: 
Java is installed in the system and path is set in environment variables.

Installation:
1.Download the project from the git.
2.Import the project in eclipse using- File> Import > existing maven project> Select the path where pom.xml is placed.
3.Once the project is imported, right click on the project and click on Maven> Maven Update , Maven Clean , Maven Install. (This will install all the dependencies from internet)
4.Project should not have any errors before running.
5.Install Cucumber plugin from marketplace (optional)
5.Navigate to Xeneta\src\test\java\Runner > Right click on the file and run as > Junit test.

Project Overview:
Runner File : The Junit file which has the cucumber options to run the tests.
BaseClass: Consists of generic and the util methods that are used by other java classes.
Feature Files: Includes test scenarios that are marked automated in the excel. 
StepDef : Includes the methods , java code that is called by the feature files.
Hooks.java - hooks class is the implementation of cucumber which runs itself before and after every scenarios, decreasing the redundancy of the code.
Execution: Run the TestRunner files and it will call the feature files which are mentioned in tag option. Further feature files fetches the code from the stepDef file. (everything might not be visible while running as cucumber is very fast and something validates in the background)

Once the project starts running successfully, the report will be generated in "\Xeneta\Xeneta\target\cucumber-reports" folder.

