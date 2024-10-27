# Hospital Management System

## Overview

The Hospital Management System is a Java-based application that allows users to manage various operations in a hospital setting. It includes functionalities for managing patients, doctors, and appointments. The system interacts with a MySQL database to store and retrieve data efficiently.

## Features

- **Add Patient**: Register new patients with their details.
- **View Patients**: List all registered patients and their information.
- **View Doctors**: List all available doctors in the hospital along with their specialization.
- **Book Appointments**: Schedule appointments between patients and doctors based on availability.
- **Check Availability**: Ensure that doctors are available on the requested date for appointments.

## Technologies Used

- **Programming Language**: Java
- **Database**: MySQL
- **JDBC Driver**: MySQL Connector/J

## Requirements

- Java Development Kit (JDK) 8 or higher
- MySQL Server
- MySQL Connector/J

## Setup Instructions

1. **Clone the Repository**:

   ```bash
   git clone https://github.com/SudipPatra35/Hospital-Management-System.git)
   cd HospitalManagementSystem

2. **Create MySQL Database**:
Create a database named hospital in MySQL and run the following SQL commands to create the necessary tables:
CREATE TABLE patient (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    age INT,
    gender VARCHAR(10),
    address VARCHAR(255)
);

CREATE TABLE doctor (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    specialization VARCHAR(100),
    gender VARCHAR(10)
);

CREATE TABLE appointment (
    id INT AUTO_INCREMENT PRIMARY KEY,
    pid INT,
    did INT,
    appointment_date DATE,
    FOREIGN KEY (pid) REFERENCES patient(id),
    FOREIGN KEY (did) REFERENCES doctor(id)
);

3. **Add MySQL Connector**:

Download the MySQL Connector/J and place the JAR file in a lib directory within your project structure.

4. **Compile the Project**:
Navigate to the src directory and compile the Java files:

cd src
javac HospitalManagementSystem/*.java

5. **Run the Application**:

Use the following command to run the application, ensuring to include the classpath for the MySQL connector:

On macOS/Linux:
    java -cp ".:lib/mysql-connector-java-x.x.x.jar" HospitalManagementSystem.HosManSys

On Windows:
    java -cp ".;lib/mysql-connector-java-x.x.x.jar" HospitalManagementSystem.HosManSys


**Usage**
After running the application, you will be presented with a menu to choose different operations. Follow the prompts to add patients, view patients or doctors, and book appointments.

**Contributing**
Contributions are welcome! If you'd like to contribute to this project, please fork the repository and submit a pull request.


**Author**
Sudip Patra
