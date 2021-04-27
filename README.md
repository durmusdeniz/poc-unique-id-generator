# Unique Number Generator

Generates incremental unique numbers, with sequential order (i.e previously created number is always smaller than the following one), in a thread-safe manner.

- There are multiple ways to achive an unique ID generator, like using UUID libraries or Twitter's 
SnowFlake or using a DB to handle the creation of the ids by using the auto-gen feature of the underlying DB. 
This application uses the default classes from the JVM (except JUnit Library itself) to have a concise code and 
provide a proof of concept, rather than being a perfect application.

- In order to run the code&tests, simply cd to the project folder and run "mvn clean;mvn install". It requires JDK-8
