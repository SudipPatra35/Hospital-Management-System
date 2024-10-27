package HospitalManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Appointment {
    private Connection conn;

    public Appointment(Connection conn) {
        this.conn = conn;
    }

    public void bookAppointment(int pid, int did, String date) {
        String query = "INSERT INTO Appointment(pid, did, appointment_date) VALUES(?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, pid);
            ps.setInt(2, did);
            ps.setString(3, date);
            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Appointment has been booked successfully.");
            } else {
                System.out.println("Failed to book appointment.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean checkDoctorAvailability(int did, String date) {
        String query = "SELECT COUNT(*) FROM Appointment WHERE did = ? AND appointment_date = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, did);
            ps.setString(2, date);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) == 0; // No appointments found
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
// javac HospitalManagementSystem/*.java
// java -cp ".;lib/mysql-connector-j-9.1.0.jar" HospitalManagementSystem.HosManSys

