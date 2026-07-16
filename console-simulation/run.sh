#!/usr/bin/env bash
set -euo pipefail
mkdir -p out
javac -d out src/com/takshil/simulation/*.java
java -cp out com.takshil.simulation.SimulationApp
