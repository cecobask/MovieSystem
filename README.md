# MovieSystem

Movie System is written in Java 8 using Eclipse. It utilizes [Cliche CLI](http://cliche.sourceforge.net/). 
This project is an assignment for the module Data Structures and Algorithms taught in the course Software Systems Development 
at Waterford Institute of Technology.

## Features

This program is able to load existing data for users, movies and ratings from a file and import into a separate file.
It's capable of adding/removing/searching/listing the data for movies, users and ratings they've given for the movies.

## Libraries used

- [Guava](https://github.com/google/guava)
- [XStream](http://x-stream.github.io/)
- [Standard Libraries](https://introcs.cs.princeton.edu/java/stdlib/)
- [Cliche Command-Line Shell](http://cliche.sourceforge.net/)

## Manual
To start with, run Main class and after that enter '?list' into the command line. That will generate a list with all the command available.
If you want to add a user, enter '?help au'. That will give you more information about what parameters must be entered.
An example for adding a user would be: 
~~~
au Tsvetoslav Dimov 21 male customerAssistant x91v5w6
~~~
