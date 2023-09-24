package main.java.ui;

import main.java.BE2;

public class AdminMenu extends Menu {
    @Override
    protected void initMenu() {
        System.out.println("---ADMIN MENU-------------------------------------------------------------------------------------------------------------------------------------------------");
        options.add("Manage books");
        options.add("Manage categories");
        options.add("Manage authors");
        options.add("Manage publishers");
        options.add("Logout");
    }

    @Override
    protected void handleOption(int option) {
        switch (option) {
            case 1:
                BE2.currentMenu = new ManageBooksMenu();
                break;
            case 2:
                

                break;
            case 3: 
                

                break;
            case 4:
                

                break;
            case 5:
                BE2.currentMenu = new MainMenu();
                BE2.currentUsername = null;
                break;
        }
    }
}
