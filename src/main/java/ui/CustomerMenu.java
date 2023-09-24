package main.java.ui;

import main.java.BE2;
import main.java.core.BooksManagement;

public class CustomerMenu extends Menu {
    @Override
    protected void initMenu() {
        System.out.println("---SEARCH FOR BOOK--------------------------------------------------------------------------------------------------------------------------------------------");
        options.add("View all books in the library");
        options.add("Search for a book");
        options.add("Issue a book");
        options.add("Return a book");
        options.add("View my issue tickets");
        options.add("Logout");
    }

    @Override
    protected void handleOption(int option) {
        switch (option) {
            case 1: 
                System.out.println("---VIEW ALL BOOKS IN THE LIBRARY------------------------------------------------------------------------------------------------------------------------------");
                BooksManagement.showAllBooks();
                break;
            case 2:
                BE2.currentMenu = new SearchBookMenu(false);
                break;
            case 3:
                
                break;
            case 4:
                
                break;
            case 5:

                break;
            case 6:
                BE2.currentMenu = new MainMenu();
                BE2.currentUsername = null;
                break;
        }
    }
}
