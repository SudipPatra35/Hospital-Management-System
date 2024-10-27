package HospitalManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Doctor {
    private Connection conn;

    public Doctor(Connection conn) {
        this.conn = conn;
    }

    public void viewDoctor() {
        String query = "SELECT * FROM doctor";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            System.out.println("Doctors:");
            System.out.println("--------------------------------------------------");
            System.out.println("| ID | Name           | Specialization | Gender |");
            System.out.println("--------------------------------------------------");
            while (rs.next()) {
                int did = rs.getInt("id");
                String name = rs.getString("name");
                String specialization = rs.getString("specialization");
                String gender = rs.getString("gender");
                System.out.printf("| %-2d | %-15s | %-15s | %-6s |\n", did, name, specialization, gender);
            }
            System.out.println("--------------------------------------------------");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean checkDoctor(int did) {
        String query = "SELECT * FROM doctor WHERE id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, did);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
