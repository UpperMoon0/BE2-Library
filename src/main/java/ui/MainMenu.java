package main.java.ui;

import main.java.util.AccountHelper;

public class MainMenu extends Menu {

    @Override
    protected void initMenu() {
        title = "Main Menu";
        options.add("Login as customer");
        options.add("Register customer account");
        options.add("Login as admin");
    }

    @Override
    protected void handleOption(int option) {
        switch (option) {
            case 1:
                AccountHelper.loginCustomer();
                break;
            case 2:
                AccountHelper.registerCustomer();
                break;
            case 3: 
                
                break;
            default:

                break;
        }
    }
}
