# Robot Navigation and Control System

## Overview
This project implements a robot navigation system that combines path planning using the A* algorithm and physical robot movement control. The system is designed to help a robot navigate through a maze or grid-like environment while avoiding obstacles, using line-following sensors for precise movement control.

## Features
- A* pathfinding algorithm implementation in Java
  - Finds optimal path in a grid-based environment
  - Calculates movement directions (forward, left, right)
  - Handles obstacle avoidance
- Robot Movement Control System in C++
  - Dual motor control for differential drive
  - Line following capability using two sensors
  - Basic movement functions: forward, left, right, stop
  - PWM-based speed control

## Components

### A* Pathfinding (A_Star_Search.java)
- Grid-based pathfinding implementation
- Uses heuristic approach to find optimal path
- Generates step-by-step movement instructions
- Supports 7x7 grid environment
- Includes path visualization through console output

### Robot Control (Robot_Movement.cpp)
- Motor control system for 2-wheel differential drive
- Line following implementation using two sensors
- Speed control through PWM
- Four basic movement functions:
  - Forward movement
  - Left turn
  - Right turn
  - Stop

## Requirements
### For A* Algorithm
- Java Development Kit (JDK)
- Java IDE (recommended)

### For Robot Control
- Arduino IDE
- Arduino-compatible microcontroller
- Hardware Components:
  - 2 DC motors
  - L298N motor driver (or similar)
  - 2 line following sensors
  - Power supply
  - Chassis and wheels

## Pin Configuration
### Motor Control Pins
- ENA (Motor 1 Speed) → Pin 6
- IN1 (Motor 1 Direction 1) → Pin 4
- IN2 (Motor 1 Direction 2) → Pin 5
- ENB (Motor 2 Speed) → Pin 9
- IN3 (Motor 2 Direction 1) → Pin 10
- IN4 (Motor 2 Direction 2) → Pin 11

### Sensor Pins
- Left Sensor → Pin 2
- Right Sensor → Pin 13

## Usage

### Running the A* Algorithm
1. Compile the Java file
```
javac A_Star_Search.java
```
2. Run the program
```
java A_Star_Search
```

### Implementing Robot Control
1. Upload the Robot_Movement.cpp code to your Arduino
2. Connect the motors and sensors according to the pin configuration
3. Power on the robot
4. Place the robot on a line-following track

## Potential Improvements
- Integration of A* pathfinding with physical robot movement
- Addition of obstacle detection sensors
- Implementation of PID control for smoother movement
- Support for dynamic obstacle avoidance
- Wireless control capabilities
