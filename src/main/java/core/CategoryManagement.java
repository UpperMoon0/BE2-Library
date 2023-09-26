package main.java.core;

import main.java.BE2;
import main.java.util.DatabaseHelper;
import main.java.util.InputHelper;

public class CategoryManagement {
    public static void showAllCategory() {
        var categoryList = DatabaseHelper.getCategoryList();

        System.out.format("%-5s | %-30s | %-100s%n", "ID", "Name", "Description");
        System.out.println("------|--------------------------------|----------------------------------------------------------------------------------------------------");
        
        for (BookCategory bc : categoryList) {
            System.out.println(bc);
        }
    }

    public static void addCategory() {
        String categoryName = "";

        while (categoryName.isEmpty() || categoryName == null) {
            categoryName = InputHelper.inputString("Enter category name: ");
            if (categoryName == null) {
                System.out.println(BE2.ANSI_RED + "Category name cannot be empty."  + BE2.ANSI_RESET);
            }
        }

        String description = "";

        while (description.isEmpty() || description == null) {
            description = InputHelper.inputString("Enter description: ");
            if (description == null) {
                System.out.println(BE2.ANSI_RED + "Description cannot be empty."  + BE2.ANSI_RESET);
            }
        }

        BookCategory category = new BookCategory(categoryName, description);
        DatabaseHelper.insertCategory(category);
        System.out.println(BE2.ANSI_GREEN + "Category created successfully." + BE2.ANSI_RESET);
    }
}
