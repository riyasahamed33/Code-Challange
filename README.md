# Code-Challange - Amazon application

Many features to building and facility automation tests using Page Object Pattern.

Process to follow:

1.Install Java 11 and set Java homepath in the enviornment variable( Control panel -> Edit the system enviornment variable->Advanced->Settings-> System variable-> set jabva home )
2. open cmd and type java -- version and hit enter -> you can see the java version
3. Install any IDE (example: Eclipse)
4. Install TestNG plugin for Eclipse ( go market place -> type testng -> select the testng plugin and click on install)
5. Create Maven Project and add the dependencies in the pom.xml (go to maven repository (https://mvnrepository.com/)and select the desired dependencies code and paste in pom.xml) if you are facing any issue in any downloading dependencies, go to project folder and select the folder and select maven ->update project and it will force fully update all the depedencies.
6. Create Properties file to store Url and other information
7. To Run the testcases using chrome (chrome version:Version 102.0.5005.63) , user need to download the chrome driver and  should paste the driver file in the project root folder (chrome driver version: 102.0.5005.61)


Tecnologies:
Eclipse Version: 2022-03 (4.23.0)
Java 11
Maven
Selenium 3.141.59
TestNG 6.8
Log4J
ExtentReport

Steps:
Create a Maven Project
Creating Base Class and utility functions
Creating Page Objects
Creating Test Cases
Converting into TestNG Framework (XML)
Adding Log4j API for Logging inside Framework
Creating HTML Reports for Test Results using Extent Reports
