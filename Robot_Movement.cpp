// Author: Tytus Felbor
// CPSC271 : Robotics
// Project 3 - Robot & Maze

// Define motor control pins
const int ENA = 6;  // PWM pin for Motor 1 speed control
const int IN1 = 4;  // Direction pin 1 for Motor 1
const int IN2 = 5;  // Direction pin 2 for Motor 1
const int ENB = 9;  // PWM pin for Motor 2 speed control
const int IN3 = 10; // Direction pin 1 for Motor 2
const int IN4 = 11; // Direction pin 2 for Motor 2

// Define sensor pins
const int leftSensorPin = 2;  // Digital pin for left sensor
const int rightSensorPin = 13; // Digital pin for right sensor

// Define threshold values for sensor readings

const int motorSpeed = 80; // Adjust this value to change motor speed (0 to 255)

void setup() {
  // Set motor control pins as outputs
  pinMode(ENA, OUTPUT);
  pinMode(IN1, OUTPUT);
  pinMode(IN2, OUTPUT);
  pinMode(ENB, OUTPUT);
  pinMode(IN3, OUTPUT);
  pinMode(IN4, OUTPUT);

  // Initialize motor speed (optional)
  analogWrite(ENA, motorSpeed); // Set initial speed for Motor 1
  analogWrite(ENB, motorSpeed); // Set initial speed for Motor 2
  
  // Set sensor pins as inputs
  pinMode(leftSensorPin, INPUT);
  pinMode(rightSensorPin, INPUT);
}

// Function to stop the robot
void stop() {
  digitalWrite(IN1, LOW);  // Motor 1 stopped
  digitalWrite(IN2, LOW);  // Motor 1 stopped
  digitalWrite(IN3, LOW);  // Motor 2 stopped
  digitalWrite(IN4, LOW);  // Motor 2 stopped
  delay(100);
}

// Function to move the robot forward
void forward() {
  analogWrite(IN1, motorSpeed); // Motor 1 forward
  digitalWrite(IN2, LOW);       // Motor 1 forward
  analogWrite(IN3, motorSpeed); // Motor 2 forward
  digitalWrite(IN4, LOW);       // Motor 2 forward
}

// Function to turn the robot left
void left() {
  digitalWrite(IN1, LOW);        // Motor 1 backward
  analogWrite(IN2, motorSpeed);  // Motor 1 forward
  analogWrite(IN3, motorSpeed);  // Motor 2 forward
  digitalWrite(IN4, LOW);        // Motor 2 forward
}

// Function to turn the robot right
void right() {
  analogWrite(IN1, motorSpeed);  // Motor 1 forward
  digitalWrite(IN2, LOW);        // Motor 1 forward
  digitalWrite(IN3, LOW);        // Motor 2 backward
  analogWrite(IN4, motorSpeed);  // Motor 2 forward
}

void loop() {
  // Read sensor values
  int leftSensorValue = digitalRead(leftSensorPin);
  int rightSensorValue = digitalRead(rightSensorPin);

  // Determine the movement based on sensor readings
  if (leftSensorValue != HIGH && rightSensorValue != HIGH) {
    forward(); // Both sensors are off the line, move forward
  } else if (leftSensorValue == HIGH && rightSensorValue == LOW) {
    left(); // Left sensor is on the line, turn left
  } else if (leftSensorValue == LOW && rightSensorValue == HIGH) {
    right(); // Right sensor is on the line, turn right
  } else {
    stop(); // Both sensors are on the line, stop
  }
  delay(200);
}
