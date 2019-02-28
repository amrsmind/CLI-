# CLI
a Command Line Interpreter (CLI) for your operating system. Your CLI should prompt the user to enter the input through the keyboard. After a sequence of characters is entered followed by a return, the string is parsed and the indicated command (s) executed. The user is then again prompted for another command.
Your program implements some built-in commands; the list of required commands is listed below. This means that your program must implement these commands directly by using the system calls that implement them. Do not use exec to implement any of these commands. The exit command is also a special case: it should simply cause termination of your program.


clear, cd, ls, cp, mv, rm, mkdir, rmdir, cat, more, pwd.
args,date,help,exit
