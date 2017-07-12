# tree_example

To run, provide a file path, search type (bfs|dfs), and output (normal|detailed) in the syntax _path_ _type_ _output_

do mvn build, then run like so: java -jar tree-1.0-SNAPSHOT.jar /test bfs detailed


CODING CHALLENGE:
Given a directory of files and sub directories provide 2 output formats: 
 
*print out all file and directory names
*print out all file and directory names with size and date modified
 
Using 2 different traversal methods:
 
*traverse using depth first
*traverse using breadth first 
 
During the execution of your program, you will allow us to choose which function to run.
 
Your program must compile and it must be correct. The code you write should demonstrate sound design fundamentals. Please write as much of the code yourself as possible, avoiding library utility methods that traverse the file system on your behalf. You may take as much time as you see fit however we do not anticipate this to take more than 4 hours of your time. We really appreciate any time you can contribute towards showing us your design skills!
 
Expectations
Ease of importing and building the project directly into Eclipse/IntelliJ (Gradle, Maven etc)
Support with unit test (Junit)
Be cognizant of memory utilization and smartly address memory/CPU constraints, the when the test is extrapolated to a larger file-set/filesystem
Read the question, understand what is asked for and make sure it’s clearly addressed without any ambiguity. 
Given an arbitrary directory in any file-sytem, the program should
Output A:   Print out all file and directory names
Output B: Output A along with size and date modified
Demonstrate 1 and 2 using DFS and BFS