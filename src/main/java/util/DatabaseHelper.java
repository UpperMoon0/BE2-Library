package main.java.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import main.java.BE2;
import main.java.core.Book;

public abstract class DatabaseHelper {
    public static void connect() {
        String url = "jdbc:mysql://localhost:3306/librarymanagement";
        String username = "root";
        String password = "123456789";

        try {
            BE2.connection = DriverManager.getConnection(url, username, password);
            if (BE2.DEBUG)
                System.out.println(BE2.ANSI_GREEN + "Connected to the database!" + BE2.ANSI_RESET);
        } catch (SQLException e) {
            if (BE2.DEBUG) {
                System.out.println(BE2.ANSI_RED + "Couldn't connect to the database." + BE2.ANSI_RESET);
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
                System.out.println(BE2.ANSI_GREEN + "A new user was inserted successfully!" + BE2.ANSI_RESET);
            }
        } catch (SQLException e) {
            System.out.println(BE2.ANSI_RED + "Failed to insert customer into database." + BE2.ANSI_RESET);
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
            System.out.println(BE2.ANSI_RED + "Failed to check if customer username exists from database." + BE2.ANSI_RESET);
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
            System.out.println(BE2.ANSI_RED + "Failed to check if customer email exists from database." + BE2.ANSI_RESET);
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
            System.out.println(BE2.ANSI_RED + "Failed to check if customer phone number exists from database." + BE2.ANSI_RESET);
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
            System.out.println(BE2.ANSI_RED + "Failed to check if customer account exists from database." + BE2.ANSI_RESET);
        }

        return exists;
    }

    public static boolean doesAdminAccountMatch(String userName, String password) {
        Connection connection = BE2.connection; 
        boolean exists = false;

        try {
            String query = "SELECT COUNT(*) FROM adminuser WHERE UserName = ? AND UserPassword = ?";
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
            System.out.println(BE2.ANSI_RED + "Failed to check if admin account exists from database." + BE2.ANSI_RESET);
        }

        return exists;
    }

    public static boolean doesBookExist(int bookID) {
        var bookList = getBookList();

        if (bookID > bookList.size() || bookID < 1) {
            return false;
        }

        return true;
    }

    public static Book getBook(int bookID) {
        var bookList = getBookList();

        if (!doesBookExist(bookID)) {
            System.out.println(BE2.ANSI_RED + "Book does not exist." + BE2.ANSI_RESET);
            return null;
        }

        return bookList.get(bookID - 1);
    }

    public static List<Book> getBookList() {
        List<Book> bookList = new ArrayList<>();
        String query = "SELECT book.Title, book.CategoryID, book.AuthorID, book.PublisherID, book.PublishDate, book.Price, book.Status FROM book";
    
        try {
            PreparedStatement statement = BE2.connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
    
            while (rs.next()) {
                String title = rs.getString("Title");
                int categoryID = rs.getInt("CategoryID");
                int authorID = rs.getInt("AuthorID");
                int publisherID = rs.getInt("PublisherID");
                LocalDate publishDate = rs.getDate("PublishDate").toLocalDate();
                int price = rs.getInt("Price");
                int status = rs.getInt("Status");
    
                Book book = new Book(title, categoryID, authorID, publisherID, publishDate, price, status);
                bookList.add(book);
            }
        } catch (SQLException e) {
            System.out.println(BE2.ANSI_RED + "Failed to get book list from database." + BE2.ANSI_RESET);
        }
    
        return bookList;
    }    

    public static List<Book> getBookListByCategory(int CategoryID) {
        List<Book> bookList = new ArrayList<>();
        String query = "SELECT book.Title, book.CategoryID, book.AuthorID, book.PublisherID, book.PublishDate, book.Price, book.Status FROM book WHERE CategoryID = ?";
    
        try {
            PreparedStatement statement = BE2.connection.prepareStatement(query);
            statement.setInt(1, CategoryID);
            ResultSet rs = statement.executeQuery();
    
            while (rs.next()) {
                String title = rs.getString("Title");
                int categoryID = rs.getInt("CategoryID");
                int authorID = rs.getInt("AuthorID");
                int publisherID = rs.getInt("PublisherID");
                LocalDate publishDate = rs.getDate("PublishDate").toLocalDate();
                int price = rs.getInt("Price");
                int status = rs.getInt("Status");
    
                Book book = new Book(title, categoryID, authorID, publisherID, publishDate, price, status);
                bookList.add(book);
            }
        } catch (SQLException e) {
            System.out.println(BE2.ANSI_RED + "Failed to get book list from database." + BE2.ANSI_RESET);
        }
    
        return bookList;
    }
    
    public static List<Book> getBookListByAuthor(int AuthorID) {
        List<Book> bookList = new ArrayList<>();
        String query = "SELECT book.Title, book.CategoryID, book.AuthorID, book.PublisherID, book.PublishDate, book.Price, book.Status FROM book WHERE AuthorID = ?";
    
        try {
            PreparedStatement statement = BE2.connection.prepareStatement(query);
            statement.setInt(1, AuthorID);
            ResultSet rs = statement.executeQuery();
    
            while (rs.next()) {
                String title = rs.getString("Title");
                int categoryID = rs.getInt("CategoryID");
                int authorID = rs.getInt("AuthorID");
                int publisherID = rs.getInt("PublisherID");
                LocalDate publishDate = rs.getDate("PublishDate").toLocalDate();
                int price = rs.getInt("Price");
                int status = rs.getInt("Status");
    
                Book book = new Book(title, categoryID, authorID, publisherID, publishDate, price, status);
                bookList.add(book);
            }
        } catch (SQLException e) {
            System.out.println(BE2.ANSI_RED + "Failed to get book list from database." + BE2.ANSI_RESET);
        }
    
        return bookList;
    }
    
    public static List<Book> getBookListByPublisher(int PublisherID) {
        List<Book> bookList = new ArrayList<>();
        String query = "SELECT book.Title, book.CategoryID, book.AuthorID, book.PublisherID, book.PublishDate, book.Price, book.Status FROM book WHERE PublisherID = ?";
    
        try {
            PreparedStatement statement = BE2.connection.prepareStatement(query);
            statement.setInt(1, PublisherID);
            ResultSet rs = statement.executeQuery();
    
            while (rs.next()) {
                String title = rs.getString("Title");
                int categoryID = rs.getInt("CategoryID");
                int authorID = rs.getInt("AuthorID");
                int publisherID = rs.getInt("PublisherID");
                LocalDate publishDate = rs.getDate("PublishDate").toLocalDate();
                int price = rs.getInt("Price");
                int status = rs.getInt("Status");
    
                Book book = new Book(title, categoryID, authorID, publisherID, publishDate, price, status);
                bookList.add(book);
            }
        } catch (SQLException e) {
            System.out.println(BE2.ANSI_RED + "Failed to get book list from database." + BE2.ANSI_RESET);
        }
    
        return bookList;
    }    

    public static void insertBook(Book book) {
        try {
            String query = "INSERT INTO book (Title, CategoryID, AuthorID, PublisherID, PublishDate, Price, Status) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = BE2.connection.prepareStatement(query);
            statement.setString(1, book.getTitle());
            statement.setInt(2, book.getCategoryID());
            statement.setInt(3, book.getAuthorID());
            statement.setInt(4, book.getPublisherID());
            statement.setDate(5, java.sql.Date.valueOf(book.getPublishDate()));
            statement.setInt(6, book.getPrice());
            statement.setInt(7, book.getStatus());
    
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println(BE2.ANSI_GREEN + "A new book was inserted successfully!" + BE2.ANSI_RESET);
            }
        } catch (SQLException e) {
            System.out.println(BE2.ANSI_RED + "Failed to insert book into database." + BE2.ANSI_RESET);
        }
    }      

    public static void updateBook(int BookID, Book book) {
        try {
            String query = "UPDATE book SET Title = ?, CategoryID = ?, AuthorID = ?, PublisherID = ?, PublishDate = ?, Price = ?, Status = ? WHERE BookID = ?";
            PreparedStatement statement = BE2.connection.prepareStatement(query);
            statement.setString(1, book.getTitle());
            statement.setInt(2, book.getCategoryID());
            statement.setInt(3, book.getAuthorID());
            statement.setInt(4, book.getPublisherID());
            statement.setDate(5, java.sql.Date.valueOf(book.getPublishDate()));
            statement.setInt(6, book.getPrice());
            statement.setInt(7, book.getStatus());
            statement.setInt(8, BookID);
    
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println(BE2.ANSI_GREEN + "The book was updated successfully!" + BE2.ANSI_RESET);
            } else {
                System.out.println(BE2.ANSI_RED + "The book was not found." + BE2.ANSI_RESET);
            }
        } catch (SQLException e) {
            System.out.println(BE2.ANSI_RED + "Failed to update book in database." + BE2.ANSI_RESET);
        }
    }    

    public static void deleteBook(int BookID) {
        try {
            String query = "DELETE FROM book WHERE BookID = ?";
            PreparedStatement statement = BE2.connection.prepareStatement(query);
            statement.setInt(1, BookID);
    
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println(BE2.ANSI_GREEN + "The book was deleted successfully!" + BE2.ANSI_RESET);
            } else {
                System.out.println(BE2.ANSI_RED + "The book was not found." + BE2.ANSI_RESET);
            }
        } catch (SQLException e) {
            System.out.println(BE2.ANSI_RED + "Failed to delete book from database." + BE2.ANSI_RESET);
        }
    }

    public static String getCategoryNameFromID (int categoryID) {
        String categoryName = "";
        String query = "SELECT CategoryName FROM category WHERE CategoryID = ?";
    
        try {
            PreparedStatement statement = BE2.connection.prepareStatement(query);
            statement.setInt(1, categoryID);
            ResultSet rs = statement.executeQuery();
    
            if (rs.next()) {
                categoryName = rs.getString("CategoryName");
            }
        } catch (SQLException e) {
            System.out.println(BE2.ANSI_RED + "Failed to get category name from database." + BE2.ANSI_RESET);
        }
    
        return categoryName;
    }

    public static String getAuthorNameFromID(int authorID) {
        String authorName = "";
        String query = "SELECT FirstName, LastName FROM author WHERE AuthorID = ?";
    
        try {
            PreparedStatement statement = BE2.connection.prepareStatement(query);
            statement.setInt(1, authorID);
            ResultSet rs = statement.executeQuery();
    
            if (rs.next()) {
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                authorName = firstName + " " + lastName;
            }
        } catch (SQLException e) {
            System.out.println(BE2.ANSI_RED + "Failed to get author name from database." + BE2.ANSI_RESET);
        }
    
        return authorName;
    }    

    public static String getPublisherNameFromID (int publisherID) {
        String publisherName = "";
        String query = "SELECT PublisherName FROM publisher WHERE PublisherID = ?";
    
        try {
            PreparedStatement statement = BE2.connection.prepareStatement(query);
            statement.setInt(1, publisherID);
            ResultSet rs = statement.executeQuery();
    
            if (rs.next()) {
                publisherName = rs.getString("PublisherName");
            }
        } catch (SQLException e) {
            System.out.println(BE2.ANSI_RED + "Failed to get publisher name from database." + BE2.ANSI_RESET);
        }
    
        return publisherName;
    }
}
