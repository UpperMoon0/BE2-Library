package main.java.ui;

import main.java.BE2;

public class AdminMenu extends Menu {
    @Override
    protected void initMenu() {
        System.out.println("---ADMIN MENU-------------------------------------------------------------------------------------------------------------------------------------------------");
        options.add("Manage Books");
        options.add("Manage Categories");
        options.add("Manage Book Issues");
        options.add("Manage Users");
        options.add("Logout");
    }

    @Override
    protected void handleOption(int option) {
        switch (option) {
            case 1:
                BE2.currentMenu = new ManageBooksMenu();
                break;
            case 2:
                BE2.currentMenu = new ManageCategoriesMenu();
                break;
            case 3: 
                BE2.currentMenu = new ManageBookIssuesMenu();
                break;
            case 4:
                BE2.currentMenu = new ManageCustomersMenu();
                break;
            case 5:
                BE2.currentMenu = new MainMenu();
                BE2.currentAdmin = null;
                break;
        }
    }
}
