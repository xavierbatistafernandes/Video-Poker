# Video Poker

## Introduction
This repository contains a project implemented in Java. 
Its purpose is to explore Object Oriented Programming.
This project implements the casino game 'Video Poker'. 


For context, Video Poker is a slot machine casino game.
The player tries to maximize his profit by betting in card hands.
The game is played through rounds. Each round 5 cards from a tradition Rank/Suit deck are drawn to the player.
The player has the change to hold or to trade each one of his 5 cards. 
If he decides to trade some cards, those are removed from is initial hand and new ones are given. 
The value of the final combination of cards is evaluated, and the player may win or not credits depending on the quality of the hand.

There are many variants to this game. (TODO)

To read more about the casino game please read:

- link1
- link2


## Setup & Repository Organization

### Java Installation

In order to run this project you need to have at least JRE (Java Runtime Environment) installed in your machine (so you can run java applications).

Check if you are able to run java applications by verifying the installed version:
```
java --version
```

If your machine didn't recognize the ```java``` command, you either don't have java installed or you don't have it properly configured yet. 

This project is compacted into a single JAR (Java ARchive), which will be used to run the application. Check if you have JAR configured by typing the following command:
```
jar --version
```



## Repository & Project Organization

Now that you have java configured, you may clone this repository to your machine.

This project was built in a way so that you can easily expand it (for example, by adding new variations of the Video Poker).

This repository provides a UML (Unified Modeling Language) diagram so you can better understand how the existing classes their respective object instances will relate with each other. To access it, open the `UML.pdf` file in the `UML/` directory.




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

A few examples for the `cmd-file` and `card-file` are available in the `TESTS/` directory.

### Simulation Mode (-s option)

To run the application in **Simulation** mode, you need to replace the `<arguments>` with:

```
-s credit bet nbdeals
```

- `-s` indicated the simulation mode
- `credit` is the player's initial credit amount
- `bet` is how much the player will be betting each round
- `nbdeals` is how many rounds the player will play



## Generating Documentation (JDOC)

In order to keep this repository a little bit simpler, the Java Documention hasn't been provided. Nonetheless, you can generate it by executing the ´javadoc´ command (installing JDK should also install this binary):

```
javadoc -d JDOC <packages>
```

Replace `<packages>` with all the source packages, namely `game`, `cards` and `main`. A new directory entitled `JDOC` will be generated, which holds the generated documentation of this project. To visualize it you simply need to open the `index.html` file in your browser and navigate.

## Extending this Project

If you intend  extend this project by adding new classes (for example, new variations for the Video Poker modes), you will require JDK (Java Development Kit). It will allow you to compile the new features (classes) and connect them to the existing ones. 

You can install JDK throught java's official website:

- java jdk and jre website

You may check if you have the compiler configured through the command:

```
javac --version
```
  
Again, in case you have installed JDK but the command isn't still recognizable, try adding the binaries of JDK to the path environment variable.





