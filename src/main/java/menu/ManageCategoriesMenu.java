package main.java.menu;

import main.java.BE2;
import main.java.core.CategoryManagement;

public class ManageCategoriesMenu extends Menu {
    @Override
    protected void initMenu() {
        System.out.println("---MANAGE CATEGORIES MENU-------------------------------------------------------------------------------------------------------------------------------------");
        options.add("Add Category");
        options.add("View All Categories");
        options.add("Back To Admin Menu");
    }

    @Override
    protected void handleOption(int option) {
        switch (option) {
            case 1:
                System.out.println("---ADD CATEGORY-----------------------------------------------------------------------------------------------------------------------------------------------");
                CategoryManagement.addCategory();
                break;
            case 2: 
                System.out.println("---VIEW ALL CATEGORIES----------------------------------------------------------------------------------------------------------------------------------------");
                CategoryManagement.showAllCategory();
                break;
            case 3:
                BE2.currentMenu = new AdminMenu();
                break;
        }
    }
}
