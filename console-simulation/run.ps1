$ErrorActionPreference = "Stop"
New-Item -ItemType Directory -Force -Path out | Out-Null
javac -d out src/com/takshil/simulation/*.java
java -cp out com.takshil.simulation.SimulationApp
