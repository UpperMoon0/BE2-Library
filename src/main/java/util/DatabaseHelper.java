package main.java.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import main.java.BE2;

public class DatabaseHelper {
    public static void connect() {
        String url = "jdbc:mysql://localhost:3306/librarymanagement";
        String username = "root";
        String password = "123456789";

        try {
            BE2.connection = DriverManager.getConnection(url, username, password);
            if (BE2.DEBUG)
                System.out.println("Connected to the database!");
        } catch (SQLException e) {
            if (BE2.DEBUG) {
                System.out.println("Couldn't connect to the database.");
                e.printStackTrace();
            }
        }
    }

    public static void insertCustomer(String username, String password, String firstName, String lastName, int age, String email, String phoneNumber, String address) {
        try {
            PreparedStatement statement = BE2.connection.prepareStatement("INSERT INTO customer (Username, UserPassword, FirstName, LastName, Age, Email, PhoneNumber, Address) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, firstName);
            statement.setString(4, lastName);
            statement.setInt(5, age);
            statement.setString(6, email);
            statement.setString(7, phoneNumber);
            statement.setString(8, address);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0 && BE2.DEBUG) {
                System.out.println("A new user was inserted successfully!");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static boolean doesCustomerUsernameExist(String username) {
        Connection connection = BE2.connection; 
        boolean exists = false;

        try {
            String query = "SELECT COUNT(*) FROM customer WHERE UserName = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                if (count > 0) {
                    exists = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exists;
    }

    public static boolean doesCustomerEmailExist(String email) {
        Connection connection = BE2.connection; 
        boolean exists = false;

        try {
            String query = "SELECT COUNT(*) FROM customer WHERE Email = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                if (count > 0) {
                    exists = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exists;
    }

    public static boolean doesCustomerPhoneNumberExist(String phoneNumber) {
        Connection connection = BE2.connection; 
        boolean exists = false;

        try {
            String query = "SELECT COUNT(*) FROM customer WHERE PhoneNumber = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, phoneNumber);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                if (count > 0) {
                    exists = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exists;
    }

    public static boolean doesCustomerAccountMatch(String userName, String password) {
        Connection connection = BE2.connection; 
        boolean exists = false;

        try {
            String query = "SELECT COUNT(*) FROM customer WHERE UserName = ? AND UserPassword = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, userName);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                if (count > 0) {
                    exists = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exists;
    }
}
