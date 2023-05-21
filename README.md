# Video Poker

## Introduction
This repository contains a project implemented in Java.
Its purpose is to explore the concepts of Object Oriented Programming, by building a program that is highly extensible.
It is an implementation of the casino game **Video Poker**. 

For context, Video Poker is a slot machine casino game.

Some of the rules, in a simplified list, are as follows:
- it is a single player game
- the player tries to maximize his profit by betting in card hands
- the game is played through rounds or card deals
- every round the player receives 5 different cards from a 52 card deck
- the player then gets to choose which cards he wants to discard
- discarded cards are replaced with new ones
- the value of the final 5 cards is evaluated
- the more valuable the hand of the player, the higher is the reward

To read more about this casino game please read:

- https://en.wikipedia.org/wiki/Video_poker
- https://en.wikipedia.org/wiki/List_of_poker_hands


## Setup & Repository Organization

### Java Installation

In order to run this project you need to have at least JRE (Java Runtime Environment) installed in your machine (so you can run java applications).

You can install JRE through java's official website:
- https://www.java.com/


Check if you are able to run java applications by verifying the installed version:
```
java --version
```

If your machine didn't recognize the ```java``` command, you either don't have java installed or you don't have it properly configured yet.

In case you have installed JRE but the command isn't still recognizable, try adding the binaries of JRE (the path to the `bin` directory of JRE) to the path environment variable.

The project source files are compacted into a single JAR (Java Archive), which will simplify the process of running the application. Check if you have JAR configured by typing the following command:
```
jar --version
```

If the command is recognized, then you are now able to run the application!



## Repository & Project Organization

Now that you have java configured, you may clone this repository to your machine.

This project was built in a way so that you can easily expand it (for example, by adding new variations of the Video Poker).

The repository has three main directories at the root level:

- **src**: where the source files are stored
- **tests**: some input files which can be used when running the application
- **uml**: contains information regarding the structure of the application

This repository provides a UML (Unified Modeling Language) diagram so you can better understand how the classes (and their respective object instances) relate with each other. To view it, open the `UML.pdf` file in the `uml/` directory.


## Functionality

A VideoPoker.jar file has been created so you can easily execute the application with the command:

```
java -jar VideoPoker.jar <arguments>
```

The project currently has two different game modes: **Debug** and **Simulation**.
The `<arguments>` needs to be replaced accordingly to the mode you wish to run.

### Debug Mode (-d option)

To run the application in **Debug** mode, you need to replace the `<arguments>` with:

```
-d credit cmd-file card-file
```
- `-d` indicates the debug mode
- `credit` is the player's initial credit amount
- `cmd-file` is a file with the commands the players shall execute
- `card-file` is a file with the deck from which cards will be drawn

#### cmd-file & card-file

The possible commands are:

| Command | Meaning    | 
|---------|------------|
| b       | bet        |
| $       | credit     |
| d       | deal       |
| h       | hold       |
| a       | advice     |
| s       | statistics |

The cards are defined with a pair indicating the rank and the suit of the card.

A few examples for the `cmd-file` and `card-file` are available in the `tests/` directory.

### Simulation Mode (-s option)

To run the application in **Simulation** mode, you need to replace the `<arguments>` with:

```
-s credit bet nbdeals
```

- `-s` indicated the simulation mode
- `credit` is the player's initial credit amount
- `bet` is how much the player will be betting each round
- `nbdeals` is how many rounds the player will play


## Extending this Project

If you intend to extend this project by adding new classes (for example, new variations for the Video Poker modes), you will require JDK (Java Development Kit). It will allow you to compile the new features (classes) and connect them to the existing ones. 

You can install JDK throught Oracle's official website:

- https://www.oracle.com/java/technologies/downloads/

You may check if you have the compiler configured through the command:

```
javac --version
```
  
Again, in case you have installed JDK but the command isn't still recognizable, try adding the binaries of JDK to the path environment variable.


## Generating Documentation (JDOC)

In order to keep this repository a little bit lighter, the Java Documention hasn't been provided. Nonetheless, you can generate it by executing the ´javadoc´ command (installing JDK should also install this binary):

```
javadoc -d JDOC <packages>
```

Replace `<packages>` with all the source packages, namely `game`, `cards` and `main`, all inside the `src/` directory. A new directory entitled `JDOC` will be generated, which holds the generated documentation of this project. To visualize it you simply need to open the `index.html` file in your browser.

## Generating a Java Archive (JAR)

In case you have extended the project and want to update the `videopoker.jar` java archive, you can execute the following command:

```
jar -cmf <manifest> videopoker.jar <compiled sources>
```

- `<manifest>` is the MANIFEST. It is a file where you indicate the class containing the main method you want to run. In this context: `Main-Class: main.Main`. The MANIFEST should have no other character/text, it is space- and case-sensitive, and it **must end with a blank line (\n)**.
- `<compiled sources>` are all the `.class` files that your program uses. Optionally, you can also include the program's `.java` files.




