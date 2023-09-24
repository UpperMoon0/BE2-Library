package main.java.ui;

import main.java.BE2;
import main.java.core.BooksManagement;

public class ManageBooksMenu extends Menu {
    @Override
    protected void initMenu() {
        System.out.println("---MANAGE BOOKS MENU------------------------------------------------------------------------------------------------------------------------------------------");
        options.add("Add book");
        options.add("Update book");
        options.add("Delete book");
        options.add("View all books in the library");
        options.add("Search for book");
        options.add("Back to admin menu");
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
                BooksManagement.showAllBooks();
                break;
            case 5:
                BE2.currentMenu = new SearchBookMenu(true);
                break;
            case 6:
                BE2.currentMenu = new AdminMenu();
                break;
        }
    }
}
