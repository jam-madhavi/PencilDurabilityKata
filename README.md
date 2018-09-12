# PencilDurabilityKata
This is Kata Pencil Durability Kata for Pillar Technologies.

Tools & Technologies used :
Java 8
Eclipse
Junit

Execution Instructions :
Requirements :
- Java 8 or Above configured to classpath
- JUnit (uploaded JUnit-4.12.jar & hamcrest-core-1.3.jar to the repository)

Steps for running the project via Runnable jar (without building it) :
1) Download the jar (PencilDurabilityKata.java) from repository 
2) Launch Java command line terminal
3) Move to the path where the jar is download/kept
4) execute the command : java -jar ./PencilDurabilityKata.jar 

The project was built in Eclipse and pushed into git.
This can be imported to any IDE by using git clone.

Steps for importing to eclipse :
https://github.com/collab-uniba/socialcde4eclipse/wiki/How-to-import-a-GitHub-project-into-Eclipse

Project structure :
PencilDurabilityKata
-- src
    -- Pencil.java 
    -- Paper.java
    -- Eraser.java
    -- Sharpner.java
    -- PointCounter.java
-- test
  -- com.kata.pencildurability
    -- PencilDurabilityTest.java
    -- KataTestSuite.java
    -- TestRunner.java
Please delete the jars below before building :
-- PencilDurabilityKata.jar - This is Runnable jar of the project, for running it command line.
-- hamcrest-core-1.3.jar - Add this as project dependency
-- junit-4.12.jar - Add this as project dependency

Future Enhancements
1) Pencil durability when editing text
2) Instead of using StringBuilder Paper instance for writing string, reading and writing it to a file
3) Create a test Suite instead of writing all test cases in a class
