# MovieSystem

Movie System is written in Java 8 using Eclipse. It utilizes [Cliche CLI](http://cliche.sourceforge.net/). 
This project is an assignment for the module Data Structures and Algorithms taught in the course Software Systems Development 
at Waterford Institute of Technology.

## Features

This program is able to load existing data for users, movies and ratings from a file and import into a separate file. It's capable of adding/removing/searching/listing the data for movies, users and ratings they've given for the movies. A new feature I've implemented is calculating average rating score for each movie. Another thing is the capability of suggesting which movies are the Top 10 rated movies from all the movies. A user can get information on what ratings a movie has received, who rated them, with that score and etc.

## Libraries used

- [Guava](https://github.com/google/guava)
- [XStream](http://x-stream.github.io/)
- [Standard Libraries](https://introcs.cs.princeton.edu/java/stdlib/)
- [Cliche Command-Line Shell](http://cliche.sourceforge.net/)

## Manual

To start with, run Main class and after that enter '?list' into the command line. That will generate a list with all the commands available. If you need help with any command, just enter "?help " followed by the command's name. If you're not registered yet, you can create a new user by typing in: au "firstName" "surname" age "gender" "job" "zip" "password" "role" (The role is going to be default by default). You can change that to "admin" by editing the field in the myfiles.xml file.
~~~
au Tsvetoslav Dimov 21 male customerAssistant x91v5w6 pass default (add user example)
li 6 pass (login with the admin's account)
~~~
There are Default and Admin menu. Each of them contain different functions. A Default user cannot delete users/movies/ ratings; Default users cannot add new movies and users. Admin users have control over every functionality available from the application Movie System.