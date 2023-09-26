package main.java;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import main.java.core.TicketManagement;
import main.java.menu.MainMenu;
import main.java.menu.Menu;
import main.java.util.DatabaseHelper;

public class BE2 {
    // Color codes for console output
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";

    public static final boolean DEBUG = true; 

    public static Menu currentMenu = new MainMenu();

    public static String currentAdmin;

    public static Connection connection;

    public static Scanner scanner;

    public static boolean exitFlag = false;

    public static void main(String[] args) {
        DatabaseHelper.connect();
        scanner = new Scanner(System.in);

        TicketManagement.updateTicketList();

        while (!exitFlag) {
            Menu.showMenu(currentMenu);
        }

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        scanner.close();
    }
}