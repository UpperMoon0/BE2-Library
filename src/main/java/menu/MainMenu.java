package main.java.menu;

import main.java.BE2;
import main.java.util.DatabaseHelper;
import main.java.util.InputHelper;

public class MainMenu extends Menu {
    @Override
    protected void initMenu() {
        System.out.println("---MAIN MENU--------------------------------------------------------------------------------------------------------------------------------------------------");
        options.add("Login");
        options.add("Quit");
    }

    @Override
    protected void handleOption(int option) {
        switch (option) {
            case 1: 
                System.out.println("---LOGIN------------------------------------------------------------------------------------------------------------------------------------------------------");
                String username = InputHelper.inputString("Enter username: ");
                String password = InputHelper.inputString("Enter password: ");

                if (DatabaseHelper.doesAdminAccountMatch(username, password)) {
                    System.out.println(BE2.ANSI_GREEN + "Login successful." + BE2.ANSI_RESET);
                    BE2.currentAdmin = username;
                    BE2.currentMenu = new AdminMenu();
                } else {
                    System.out.println(BE2.ANSI_RED + "Incorrect username or password." + BE2.ANSI_RESET);
                    System.out.println(BE2.ANSI_RED + "Login failed." + BE2.ANSI_RESET);
                }
                break;
            case 2:
                System.out.println("---QUIT-------------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.println(BE2.ANSI_BLUE + "Goodbye!" + BE2.ANSI_RESET);
                BE2.exitFlag = true;
                break;
        }
    }
}
