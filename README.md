# Employee Database Management App (Java + JDBC)

## Description
This is a simple Java console application that connects to a MySQL database using JDBC. It allows users to perform basic CRUD operations on an `employees` table. The program also auto-creates the database and table if they do not exist.

## Features
- Auto-create database and table if not present
- Add employee
- View all employees
- Update selected fields of an employee (name, designation, salary)
- Delete employee by ID

## Technologies Used
- Java (JDK 8 or higher)
- JDBC (Java Database Connectivity)
- MySQL
- Eclipse IDE

## Setup Instructions

### 1. Configure MySQL Credentials
Update the following in `EmployeeService.java`:
```java
private static final String USER = "your_mysql_username";
private static final String PASS = "your_mysql_password";
```

### 2. Add MySQL JDBC Driver to Eclipse
Download MySQL Connector JAR:  
https://search.maven.org/artifact/mysql/mysql-connector-java

In Eclipse:
- Right-click your project → Build Path → Configure Build Path
- Go to the Libraries tab → click **Add External JARs**
- Select the downloaded mysql-connector-java-x.x.xx.jar
- Click **Apply and Close**

## How to Run the Program
1. Clone or download this repository.
2. Open the project in Eclipse.
3. Navigate to: `src/employee/EmployeeApp.java`
4. Right-click the file → **Run As** → Java Application
5. Use the console menu to manage employee data

## Sample Output
```
=== Employee Database ===
1. Add Employee
2. View Employees
3. Update Employee
4. Delete Employee
5. Exit
Enter choice: 1
Enter name: Sai
Enter designation: Developer
Enter salary: 60000
Employee added.

=== Employee Database ===
Enter choice: 2

--- Employee List ---
ID: 1 | Name: Sai | Designation: Developer | Salary: 60000.00

=== Employee Database ===
Enter choice: 3
Enter employee ID to update: 1
Do you want to update name? (Y/N): N
Do you want to update designation? (Y/N): Y
Enter new designation: Senior Developer
Do you want to update salary? (Y/N): N
Employee updated.
```

## Project Structure
```
EmployeeDBApp/
├── src/
│   └── employee/
│       ├── EmployeeApp.java
│       └── EmployeeService.java
└── mysql-connector-java-8.0.xx.jar
```

## Author
Chinta Lakshmi Kanth
