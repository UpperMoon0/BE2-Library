package main.java.ui;

import main.java.BE2;
import main.java.core.BooksManagement;

public class SearchBookMenu extends Menu {
    private boolean isAdmin;

    public SearchBookMenu(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    @Override
    protected void initMenu() {
        System.out.println("---SEARCH FOR BOOK--------------------------------------------------------------------------------------------------------------------------------------------");
        options.add("Search by title");
        options.add("Search by category");
        options.add("Search by author");
        options.add("Search by publisher");
        if (isAdmin)
            options.add("Back to manage books menu");
        else {
            options.add("Back to customer menu");
        }
    }

    @Override
    protected void handleOption(int option) {
        switch (option) {
            case 2:
                System.out.println("---SEARCH BY CATEGORY-----------------------------------------------------------------------------------------------------------------------------------------");
                BooksManagement.searchBookByCategory();
                break;
            case 3:
                System.out.println("---SEARCH BY AUTHOR-------------------------------------------------------------------------------------------------------------------------------------------");
                BooksManagement.searchBookByAuthor();
                break;
            case 4:
                System.out.println("---SEARCH BY PUBLISHER----------------------------------------------------------------------------------------------------------------------------------------");
                BooksManagement.searchBookByPublisher();
                break;
            case 5:
                if (isAdmin)
                    BE2.currentMenu = new ManageBooksMenu();
                else
                    BE2.currentMenu = new CustomerMenu();
                break;
        }
    }
}
