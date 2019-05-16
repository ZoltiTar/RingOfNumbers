Ring of Numbers
===============
---
Description
-----------
Ring of Numbers is a puzzle, where you start with a ring of numbers from [1, 20] in ascending order, going clockwise.
The goal is to reverse the numbers in a ring, so that they are in descending order, going clockwise.

Requirements
------------
The user must have Apache Maven installed, preferably anything above version 3.6.0, and Java JDK 11.0.2 or above to compile/package and run the game.

Packaging/Playing
-----------------
To run the game, one must run the maven command ```mvn package``` to get the runnable jar.

Then to run the game ```java -jar target/RingOfNumbers-1.0.jar```.

About
------
This game is a school project. Due to lack of time, instead of an OpenJFX GUI it has a command line interface.
For printing the scoreboard and the ring, it uses user vdmeer's asciitable: https://github.com/vdmeer/asciitable.
For printing the welcoming header, uses user lalyos' JFiglet: https://github.com/lalyos/jfiglet
