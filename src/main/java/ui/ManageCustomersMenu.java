package main.java.ui;

import main.java.BE2;
import main.java.core.CustomerManagement;

public class ManageCustomersMenu extends Menu {
    @Override
    protected void initMenu() {
        System.out.println("---MANAGE USERS-----------------------------------------------------------------------------------------------------------------------------------------------");
        options.add("Register New Customer");
        options.add("View All Customers");
        options.add("Back To Admin Menu");
    }

    @Override
    protected void handleOption(int option) {
        switch (option) {
            case 1:
                System.out.println("---REGISTER NEW CUSTOMER--------------------------------------------------------------------------------------------------------------------------------------");
                CustomerManagement.registerCustomer();
                break;
            case 2:
                System.out.println("---VIEW ALL CUSTOMERS-----------------------------------------------------------------------------------------------------------------------------------------");
                CustomerManagement.showAllCustomers();
                break;
            case 3:
                BE2.currentMenu = new AdminMenu();
                break;
        }
    }
}
