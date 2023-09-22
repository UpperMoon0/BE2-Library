package main.java;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import main.java.ui.MainMenu;
import main.java.ui.Menu;
import main.java.util.DatabaseHelper;

public class BE2 {
    // Color codes for console output
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "a\u001B[37m";

    public static final boolean DEBUG = true; 

    public static Connection connection;

    public static Scanner scanner;

    public static void main(String[] args) {
        DatabaseHelper.connect();
        scanner = new Scanner(System.in);
        Menu.showMenu(new MainMenu());
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        scanner.close();
    }
}