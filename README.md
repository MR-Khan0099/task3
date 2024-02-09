# Charging System Project
This project is a Charging System implemented in Java, where users can reserve charging slots for their electric or solar-powered vehicles. The system includes features for logging and managing user details, administrator details, and charging station information.

# Project Structure
The project is organized into several classes and annotations:

Main: The main class that orchestrates the charging system, reads user and administrator details, and simulates the charging process.


Classes:

# UserClass: Represents a class annotated with user details.
AdminstratorClass: Represents a class annotated with administrator details.
ChargingUnitClass: Represents a class annotated with charging station details.
ChargingSystem: The core class handling the reservation system, processing day simulation, and charging unit management.
Logger Classes:

systemLogger: Logs system-related information such as user details, administrator details, and charging unit details. Provides functionality to archive, move, and delete log files.
stationLogger: Logs station-related information, including car user name, car plate, and car type. Provides functionality to archive, move, and delete log files.
energyLogger: Logs energy-related information, such as car type, arrival time, and fuel received by the car. Provides functionality to archive, move, and delete log files.
Usage
Run the Main class to start the charging system simulation.
The system will read user, administrator, and charging station details.
Reservation system will be initialized, and the day processing simulation will begin.
Various loggers will record system, station, and energy-related information.
Log files can be archived, moved, and deleted using the provided logger functionalities.
