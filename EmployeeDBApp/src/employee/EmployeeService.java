package employee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class EmployeeService {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/";
    private static final String DB_NAME = "employee_db";
    private static final String FULL_DB_URL = DB_URL + DB_NAME;
    private static final String USER = "root";
    private static final String PASS = "kyubi";

    private static Connection conn;

    public static void connect() throws SQLException {
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
        System.out.println("Connected to MySQL.");
    }

    public static void createDatabaseAndTable() throws SQLException {
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS " + DB_NAME);
        System.out.println("Database checked/created.");

        conn = DriverManager.getConnection(FULL_DB_URL, USER, PASS);
        stmt = conn.createStatement();
        String tableSQL = "CREATE TABLE IF NOT EXISTS employees (" +
                          "id INT PRIMARY KEY AUTO_INCREMENT," +
                          "name VARCHAR(100)," +
                          "designation VARCHAR(100)," +
                          "salary DOUBLE)";
        stmt.executeUpdate(tableSQL);
        System.out.println("Table checked/created.");
    }

    public static void addEmployee(Scanner sc) throws SQLException {
        System.out.print("Enter name: ");
        sc.nextLine(); // clear leftover newline
        String name = sc.nextLine();

        System.out.print("Enter designation: ");
        String desig = sc.nextLine();

        System.out.print("Enter salary: ");
        double salary = sc.nextDouble();

        String sql = "INSERT INTO employees (name, designation, salary) VALUES (?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, name);
        ps.setString(2, desig);
        ps.setDouble(3, salary);
        ps.executeUpdate();
        System.out.println("Employee added.");
    }

    public static void viewEmployees() throws SQLException {
        String sql = "SELECT * FROM employees";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        System.out.println("\n--- Employee List ---");
        while (rs.next()) {
            System.out.printf("ID: %d | Name: %s | Designation: %s | Salary: %.2f\n",
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("designation"),
                    rs.getDouble("salary"));
        }
    }

    public static void updateEmployee(Scanner sc) throws SQLException {
        System.out.print("Enter employee ID to update: ");
        int id = sc.nextInt();
        sc.nextLine(); // consume newline

        StringBuilder sql = new StringBuilder("UPDATE employees SET ");
        boolean updateName = false, updateDesig = false, updateSalary = false;
        String name = "", desig = "";
        double salary = 0.0;

        System.out.print("Do you want to update name? (Y/N): ");
        if (sc.nextLine().equalsIgnoreCase("Y")) {
            updateName = true;
            System.out.print("Enter new name: ");
            name = sc.nextLine();
            sql.append("name = ?, ");
        }

        System.out.print("Do you want to update designation? (Y/N): ");
        if (sc.nextLine().equalsIgnoreCase("Y")) {
            updateDesig = true;
            System.out.print("Enter new designation: ");
            desig = sc.nextLine();
            sql.append("designation = ?, ");
        }

        System.out.print("Do you want to update salary? (Y/N): ");
        if (sc.nextLine().equalsIgnoreCase("Y")) {
            updateSalary = true;
            System.out.print("Enter new salary: ");
            salary = sc.nextDouble();
            sc.nextLine(); // consume newline
            sql.append("salary = ?, ");
        }

        if (!updateName && !updateDesig && !updateSalary) {
            System.out.println("Nothing to update.");
            return;
        }

        // Remove the last comma and space
        if (sql.toString().endsWith(", ")) {
            sql.setLength(sql.length() - 2);
        }

        sql.append(" WHERE id = ?");

        PreparedStatement ps = conn.prepareStatement(sql.toString());
        int index = 1;

        if (updateName) ps.setString(index++, name);
        if (updateDesig) ps.setString(index++, desig);
        if (updateSalary) ps.setDouble(index++, salary);
        ps.setInt(index, id);

        int rows = ps.executeUpdate();
        if (rows > 0)
            System.out.println("Employee updated.");
        else
            System.out.println("Employee not found.");
    }

    public static void deleteEmployee(Scanner sc) throws SQLException {
        System.out.print("Enter employee ID to delete: ");
        int id = sc.nextInt();

        String sql = "DELETE FROM employees WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        int rows = ps.executeUpdate();

        if (rows > 0)
            System.out.println("Employee deleted.");
        else
            System.out.println("Employee not found.");
    }
}
