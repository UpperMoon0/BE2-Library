package main.java.ui;

import main.java.BE2;
import main.java.util.AccountHelper;

public class MainMenu extends Menu {
    @Override
    protected void initMenu() {
        System.out.println("---MAIN MENU--------------------------------------------------------------------------------------------------------------------------------------------------");
        options.add("Login as customer");
        options.add("Register customer account");
        options.add("Login as admin");
        options.add("Quit");
    }

    @Override
    protected void handleOption(int option) {
        switch (option) {
            case 1:
                System.out.println("---CUSTOMER LOGIN---------------------------------------------------------------------------------------------------------------------------------------------");
                AccountHelper.loginCustomer();
                break;
            case 2:
                System.out.println("---CUSTOMER REGISTER------------------------------------------------------------------------------------------------------------------------------------------");
                AccountHelper.registerCustomer();
                break;
            case 3: 
                System.out.println("---LOGIN ADMIN------------------------------------------------------------------------------------------------------------------------------------------------");
                AccountHelper.loginAdmin();
                break;
            case 4:
                System.out.println("---QUIT-------------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.println("Thank you for using our service!");
                System.out.println("Goodbye!");
                BE2.exitFlag = true;
                break;
        }
    }
}
