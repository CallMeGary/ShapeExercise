# Riplife Exercise set up instructions


*  You can use 'mvn clean test' to run all the unit tests.
**    There is a load test included in the unit test.
**    So it will take several minutes (depending on the machine) to run.
**    Try not to add/remove/edit any files at /src/test/resources, it might affect the test result.


*  You can use 'mvn compile exec:java' to launch the Main class - es.riplife.exercise.app.AppConsole
**    You can adjust the application settings in src/main/resources/appContext.xml
**    You can add/remove/edit shape data files at src/main/resources
