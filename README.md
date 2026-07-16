# Java and JavaFX Simulation Suite

A portfolio repository demonstrating object-oriented design, queue-based simulation, Maven organisation, and an optional JavaFX dashboard.

## Included applications

### 1. Console queue simulation

A dependency-free discrete-event simulation of orders moving through preparation, a single-oven queue, cooking, and completion.

```powershell
cd console-simulation
javac -d out src/com/takshil/simulation/*.java
java -cp out com.takshil.simulation.SimulationApp
```

### 2. JavaFX dashboard

A Maven JavaFX application that visualises the same order queue and event log.

```powershell
cd javafx-dashboard
mvn clean javafx:run
```

Use JDK 17 or newer. The Maven project declares its JavaFX dependencies so they do not need to be installed globally.

## Concepts demonstrated

- Encapsulation and immutable event records
- Enums for explicit lifecycle states
- `PriorityQueue` for scheduled events
- `ArrayDeque` for the oven waiting queue
- Separation between simulation engine and user interface
- JavaFX controls and event handlers
- Maven compiler and JavaFX plugin configuration

## Evidence to add

Before pinning this repository, add a screenshot of the JavaFX dashboard and paste a short console output excerpt into the README. Run the application yourself so that the evidence matches your environment.

## Portfolio reconstruction

This repository is a clean demonstration based on the Java, JavaFX, Maven, and queue-simulation work described in the portfolio. It should not be presented as a byte-for-byte copy of an unavailable original submission.
