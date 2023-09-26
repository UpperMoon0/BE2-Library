package main.java.ui;

import main.java.BE2;
import main.java.core.BooksManagement;

public class ManageBooksMenu extends Menu {
    @Override
    protected void initMenu() {
        System.out.println("---MANAGE BOOKS MENU------------------------------------------------------------------------------------------------------------------------------------------");
        options.add("Add Book");
        options.add("Update Book");
        options.add("Delete Book");
        options.add("View All Books In The Library");
        options.add("Search For Book");
        options.add("Back To Admin Menu");
    }

    @Override
    protected void handleOption(int option) {
        switch (option) {
            case 1:
                System.out.println("---ADD BOOK---------------------------------------------------------------------------------------------------------------------------------------------------");
                BooksManagement.addBook();
                break;
            case 2:
                System.out.println("---UPDATE BOOK------------------------------------------------------------------------------------------------------------------------------------------------");
                BooksManagement.updateBook();
                break;
            case 3: 
                System.out.println("---DELETE BOOK------------------------------------------------------------------------------------------------------------------------------------------------");
                BooksManagement.deleteBook();
                break;
            case 4:
                System.out.println("---VIEW ALL BOOKS IN THE LIBRARY------------------------------------------------------------------------------------------------------------------------------");
                BooksManagement.showAllBooks(true);
                break;
            case 5:
                BE2.currentMenu = new SearchBookMenu();
                break;
            case 6:
                BE2.currentMenu = new AdminMenu();
                break;
        }
    }
}
