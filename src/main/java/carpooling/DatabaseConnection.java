package carpooling;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
 private static final String DB_URL = "jdbc:mysql://localhost:3306/carpool"; // Replace with your DB URL
 private static final String DB_USER = "root";
 private static final String DB_PASSWORD = "mysql123456";

 private Connection connection;

 public Connection getConnection() {
     if (connection == null) {
         try {
             Class.forName("com.mysql.cj.jdbc.Driver");
             connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
         } catch (ClassNotFoundException | SQLException e) {
             e.printStackTrace();
         }
     }
     return connection;
 }

 public void closeConnection() {
     if (connection != null) {
         try {
             connection.close();
         } catch (SQLException e) {
             e.printStackTrace();
         }
     }
 }
}
