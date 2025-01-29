# Assignment 1 - Group 15

This project is a part of an anti-ballistic missile system. The purpose is to determine whether an interceptor will launch or not to intercept an incoming anti-ballistic missile.

The code works by determining whether an interceptor will launch or not based on
planar data points and Launch Interceptor Conditions (LIC's).

## How to Build and Test

The code is written in Java and uses maven for building, for testing we use JUnit5.

To build the code, run `mvn clean install` in the root directory of the project. This will compile the code, run the tests, and package the code into a jar file inside of the `target` directory.

To just run the tests, run `mvn test` in the root directory of the project.

## Folder structure overview

The main file can be found in `/src/main/java/org/dd2480/Main.java`

All the unit tests can be found in `/src/test/java/org/dd2480/`

## Statement of Contributions

#### Francis
- Implemented lic3, lic4, lic6 and lic10
- Implemented triangle-area
- Wrote report

#### David
- Implemented lic1, lic8 and lic13
- Implemented function to to determine if a set can be obtained (or on) a circle

#### Markus
- Implemented lic0, lic7 and lic12
- Wrote README file

#### Ebrar
- Implemented lic2, lic5, lic9 and lic11
- Implemented function to calculate angle formed by 3 points.