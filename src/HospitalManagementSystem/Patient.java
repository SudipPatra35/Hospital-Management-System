package HospitalManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Patient {
    private Connection conn;
    private Scanner sc;

    public Patient(Connection conn, Scanner sc) {
        this.conn = conn;
        this.sc = sc;
    }

    public void addPatient() {
        System.out.print("Enter patient name: ");
        String name = sc.nextLine();
        System.out.print("Enter age of " + name + ": ");
        int age = sc.nextInt();
        sc.nextLine(); // Consume the newline
        System.out.print("Enter gender of " + name + ": ");
        String gender = sc.nextLine();
        System.out.print("Enter address of " + name + ": ");
        String address = sc.nextLine();

        try {
            String query = "INSERT INTO patient(name, age, gender, address) VALUES(?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, name);
            ps.setInt(2, age);
            ps.setString(3, gender);
            ps.setString(4, address);
            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Patient details added successfully.");
            } else {
                System.out.println("Failed to add patient.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void viewPatient() {
        String query = "SELECT * FROM patient";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            System.out.println("Patients:");
            System.out.println("--------------------------------------------------");
            System.out.println("| ID | Name           | Age | Gender | Address  |");
            System.out.println("--------------------------------------------------");
            while (rs.next()) {
                int pid = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String gender = rs.getString("gender");
                String address = rs.getString("address");
                System.out.printf("| %-2d | %-15s | %-2d | %-6s | %-10s |\n", pid, name, age, gender, address);
            }
            System.out.println("--------------------------------------------------");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean checkPatient(int pid) {
        String query = "SELECT * FROM patient WHERE id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, pid);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
