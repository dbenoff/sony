_RegexTextReplacementInFiles_

This package contains the skeleton structure for this exercise.
Use only built-in libraries come with Oracle's JDK 6/7/8, implement the process() method in the code (also
shown below) to perform string replacement for each of the qualified files under a starting directory.
Before the program terminates, display a summary report of the strings and occurrences count that were
replaced (see sample output). Save the processed output to files in the same directories of the source files
with new file names by appending the original names with ".processed".
Please assume the file size can be arbitrarily large. The file repository can also be arbitrarily large which
may contain large number of sub directories and files. It might be crucial to process them as efficient as
possible.
To demonstrate the level of your OOP understanding and programming skill, please structure your
implementation to allow easy customization in the future for 1) Directory walking strategies. 2) Tasks to
be performed against each file. 3) Stats to be collected and reports to be generated.
• The program takes 3 required parameters and one optional parameter:
o Starting directory/file
o String pattern to be replaced - in Java supported regular expression, see (1)
o String to be replaced with
o File naming pattern - UNIX wild-card filename syntax, see (2)
• Notes
1. The string pattern is a JDK supported regular expression with exactly 1 match group.
Find all texts, which match the pattern, and replace ONLY the match group 1 with the
replacement. If the match group in the given pattern doesn't equal to one, the program can
exit immediately by throwing and/or printing error.
2. The file naming pattern will be the generally accepted file naming pattern in UNIX where
'*' matches any number of characters, '?' matches one character.