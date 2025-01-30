# Simplified Java UNO Game Implementation

This project is a Java implementation of the classic UNO card game, featuring a graphical user interface built with JavaFX. The game allows players to play UNO against a computer opponent, implementing core UNO game mechanics including special cards like Skip cards and Wild cards.


## Table of Contents

1. Features
2. Prerequisites
3. Project Structure
4. How to Run
5. Gameplay Instructions
6. Technical Details
7. Potential Improvements


## 1. Features

- Graphical user interface using JavaFX
- Player vs Computer gameplay
- Implementation of different card types:
  - Number cards (0-9)
  - Action cards (Skip, Draw Two)
  - Wild cards
- Card drawing mechanism
- Visual representation of cards using sprite sheets
- Interactive gameplay with mouse controls


## 2. Prerequisites

- Java Development Kit (JDK) 8 or higher
- JavaFX library
- An IDE that supports JavaFX development (e.g., Eclipse, IntelliJ IDEA)


## 3. Project Structure

The project consists of several key classes and one sprite sheet:
- `mainClass.java` → Main application class handling the GUI and game loop
- `Card.java` → Base class for all card types
- `CommonCard.java` → Implementation of standard numbered cards
- `Player.java` → Manages player hands and card drawing
- `SkipCard.java` → Implementation of Skip cards
- `WildCard.java` → Implementation of Wild cards
- `Table.java` → Manages the game table state
- `cards.png` → sprite sheet with a template for all the UNO cards


## 4. How to Run

1. Ensure you have Java and JavaFX installed on your system
2. Compile all Java files in the `unoPackage` package
3. Run the `mainClass` as the main entry point
4. The game window will appear with the initial card layout


## 5. Gameplay Instructions

1. Click on a card in your hand to play it
2. Click the "DRAW" button to draw a new card when needed
3. The computer player will automatically play their turn after you
4. Cards are played based on matching colors or values, following standard UNO rules


## 6. Technical Details

- The game uses a sprite sheet (`cards.png`) for card images
- Cards are arranged in 5 rows and 13 columns in the sprite sheet
- The game implements basic UNO rules for card matching and play mechanics
- The GUI is built using JavaFX Canvas for rendering


## 7. Potential Improvements

- Implementation of additional UNO card types
- Multiplayer support
- Score tracking
- Sound effects
- Advanced computer AI strategies
- Network play capability


