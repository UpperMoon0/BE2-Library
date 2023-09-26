package main.java.ui;

import main.java.BE2;
import main.java.core.BooksManagement;

public class SearchBookMenu extends Menu {
    @Override
    protected void initMenu() {
        System.out.println("---SEARCH FOR BOOK--------------------------------------------------------------------------------------------------------------------------------------------");
        options.add("Search By Title");
        options.add("Search By Category");
        options.add("Search By Author");
        options.add("Search By Publisher");
        options.add("Back To Manage Books Menu");
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
                BE2.currentMenu = new ManageBooksMenu();
        }
    }
}
