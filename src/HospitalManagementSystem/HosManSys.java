package HospitalManagementSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class HosManSys {
    private static final String url = "jdbc:mysql://localhost:3306/hospital";
    private static final String username = "root";
    private static final String password = "Sudip@7797";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, username, password);
            Patient patient = new Patient(conn, sc);
            Doctor doctor = new Doctor(conn);
            Appointment ap = new Appointment(conn);

            while (true) {
                System.out.println("\nWelcome to LifeLine Hospital......");
                System.out.println("1.   Add Patient");
                System.out.println("2.   View Patient");
                System.out.println("3.   View Doctor");
                System.out.println("4.   Book Appointment");
                System.out.println("5.   Exit....");
                System.out.print("Enter your choice: ");
                int choice = sc.nextInt();
                sc.nextLine(); // Clear newline character

                switch (choice) {
                    case 1:
                        patient.addPatient();
                        break;
                    case 2:
                        patient.viewPatient();
                        break;
                    case 3:
                        doctor.viewDoctor();
                        break;
                    case 4:
                        bookAppointment(ap, patient, doctor, sc);
                        break;
                    case 5:
                        System.out.println("Exiting the system. Goodbye!");
                        conn.close(); // Close the connection
                        sc.close(); // Close the scanner
                        return;
                    default:
                        System.out.println("Enter Valid Choice");
                }
            }
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL Driver not found. Make sure the JDBC driver is included in the classpath.");
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } 
    }

    public static void bookAppointment(Appointment ap, Patient patient, Doctor doctor, Scanner sc) {
        System.out.print("Enter patient ID: ");
        int pid = sc.nextInt();
        System.out.print("Enter doctor ID: ");
        int did = sc.nextInt();
        sc.nextLine(); // Clear newline character
        System.out.print("Enter date (YYYY-MM-DD): ");
        String appDate = sc.nextLine();

        if (!patient.checkPatient(pid)) {
            System.out.println("Patient does not exist... Add Patient First.");
            return;
        } else if (!doctor.checkDoctor(did)) {
            System.out.println("Invalid Doctor ID. List of doctors:");
            doctor.viewDoctor();
            return;
        }

        if (ap.checkDoctorAvailability(did, appDate)) {
            ap.bookAppointment(pid, did, appDate);
        } else {
            System.out.println("Doctor is not available on this date.");
        }
    }
}
