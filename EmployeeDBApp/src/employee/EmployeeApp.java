package employee;

import java.sql.SQLException;
import java.util.Scanner;

public class EmployeeApp {
    public static void main(String[] args) {
        try {
            EmployeeService.connect();
            EmployeeService.createDatabaseAndTable();

            Scanner sc = new Scanner(System.in);
            while (true) {
                System.out.println("\n=== Employee Database ===");
                System.out.println("1. Add Employee");
                System.out.println("2. View Employees");
                System.out.println("3. Update Employee");
                System.out.println("4. Delete Employee");
                System.out.println("5. Exit");
                System.out.print("Enter choice: ");
                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        EmployeeService.addEmployee(sc);
                        break;
                    case 2:
                        EmployeeService.viewEmployees();
                        break;
                    case 3:
                        EmployeeService.updateEmployee(sc);
                        break;
                    case 4:
                        EmployeeService.deleteEmployee(sc);
                        break;
                    case 5:
                        System.out.println("Exiting...");
                        sc.close();
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice!");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

