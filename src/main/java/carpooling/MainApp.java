package carpooling;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class MainApp {
 public static void main(String[] args) {
     DatabaseConnection dbConnection = new DatabaseConnection();
     Connection connection = dbConnection.getConnection();

     Scanner scanner = new Scanner(System.in);

     while (true) {
         System.out.println("1. Offer a Ride");
         System.out.println("2. Find a Ride");
         System.out.println("3. Exit");
         System.out.print("Enter your choice: ");
         int choice = scanner.nextInt();
         scanner.nextLine(); // Clear the newline from the input buffer

         switch (choice) {
             case 1:
                 offerRide(scanner, connection);
                 break;
             case 2:
                 findRide(scanner, connection);
                 break;
             case 3:
                 System.out.println("Thank you for using the Carpooling App. Goodbye!");
                 dbConnection.closeConnection();
                 return;
             default:
                 System.out.println("Invalid choice. Please try again.");
         }
     }
 }

 private static void offerRide(Scanner scanner, Connection connection) {
     // Implementation for offering a ride
     // Get input for ride details (source, destination, date, time, availableSeats, contactNumber)
     System.out.print("Enter source: ");
     String source = scanner.nextLine();

     System.out.print("Enter destination: ");
     String destination = scanner.nextLine();

     System.out.print("Enter date (YYYY-MM-DD): ");
     String date = scanner.nextLine();

     System.out.print("Enter time: ");
     String time = scanner.nextLine();

     System.out.print("Enter available seats: ");
     int availableSeats = scanner.nextInt();
     scanner.nextLine(); // Clear the newline from the input buffer

     System.out.print("Enter contact number: ");
     String contactNumber = scanner.nextLine();

     // Create a Ride object
     Ride ride = new Ride(0, source, destination, date, time, availableSeats, contactNumber);

     // Save it to the database
     saveRide(ride, connection);
 }

 private static void saveRide(Ride ride, Connection connection) {
     // Implementation to save the ride to the database
     String sql = "INSERT INTO rides (source, destination, date, time, available_seats, contact_number) VALUES (?, ?, ?, ?, ?, ?)";
     try {
         PreparedStatement preparedStatement = connection.prepareStatement(sql);
         preparedStatement.setString(1, ride.getSource());
         preparedStatement.setString(2, ride.getDestination());
         preparedStatement.setString(3, ride.getDate());
         preparedStatement.setString(4, ride.getTime());
         preparedStatement.setInt(5, ride.getAvailableSeats());
         preparedStatement.setString(6, ride.getContactNumber());

         preparedStatement.executeUpdate();
         System.out.println("Ride offered successfully!");
     } catch (SQLException e) {
         e.printStackTrace();
         System.out.println("Error while offering the ride.");
     }
 }

 private static void findRide(Scanner scanner, Connection connection) {
     // Implementation for finding a ride
     // Get input for search criteria (source, destination, date)
     System.out.print("Enter source: ");
     String source = scanner.nextLine();

     System.out.print("Enter destination: ");
     String destination = scanner.nextLine();

     System.out.print("Enter date (YYYY-MM-DD): ");
     String date = scanner.nextLine();

     // Retrieve matching rides from the database
     searchRides(source, destination, date, connection);
 }

 private static void searchRides(String source, String destination, String date, Connection connection) {
     // Implementation to search for rides in the database based on source, destination, and date
     String sql = "SELECT * FROM rides WHERE source=? AND destination=? AND date=?";
     try {
         PreparedStatement preparedStatement = connection.prepareStatement(sql);
         preparedStatement.setString(1, source);
         preparedStatement.setString(2, destination);
         preparedStatement.setString(3, date);

         ResultSet resultSet = preparedStatement.executeQuery();

         if (resultSet.next()) {
             System.out.println("Matching rides found:");
             do {
                 int rideId = resultSet.getInt("ride_id");
                 String time = resultSet.getString("time");
                 int availableSeats = resultSet.getInt("available_seats");
                 String contactNumber = resultSet.getString("contact_number");

                 System.out.println("Ride ID: " + rideId);
                 System.out.println("Time: " + time);
                 System.out.println("Available Seats: " + availableSeats);
                 System.out.println("Contact Number: " + contactNumber);
                 System.out.println("--------------------------");
             } while (resultSet.next());
         } else {
             System.out.println("No matching rides found for the given criteria.");
         }
     } catch (SQLException e) {
         e.printStackTrace();
         System.out.println("Error while searching for rides.");
     }
 }
}

