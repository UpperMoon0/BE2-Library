package main.java.core;

import java.time.LocalDate;
import java.util.List;

import main.java.BE2;
import main.java.util.DatabaseHelper;
import main.java.util.InputHelper;

public abstract class BooksManagement {
    public static void showAllBooks(boolean withCount) {
        var bookList = DatabaseHelper.getBookList();

        if (withCount)
        {
            System.out.println("Book count: " + bookList.size());
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        }
        showBookTableHeader();

        for (Book book : bookList) {
            System.out.println(book);
        }
    }    

    public static void addBook() {
        String title = "";
        while (title.isEmpty() || title == null) {
            title = InputHelper.inputString("Enter book title: ");
            if (title == null) {
                System.out.println(BE2.ANSI_RED + "Title cannot be empty." + BE2.ANSI_RESET);
            }
        }

        System.out.println();
        System.out.println("Category list:");
        CategoryManagement.showAllCategory();
        System.out.println();

        int categoryID = 0;
        while (categoryID == 0) {
            try {
                categoryID = Integer.parseInt(InputHelper.inputInt("Enter category ID: "));
                if (categoryID == 0) {
                    System.out.println(BE2.ANSI_RED + "Category ID cannot be 0." + BE2.ANSI_RESET);
                }
            } catch (NumberFormatException e) {
                System.out.println(BE2.ANSI_RED + "Category ID cannot be empty." + BE2.ANSI_RESET);
            }
        }

        System.out.println();
        System.out.println("Author list:");
        DatabaseHelper.printAuthorListMini();
        System.out.println();

        int authorID = 0;
        while (authorID == 0) {
            try {
                authorID = Integer.parseInt(InputHelper.inputInt("Enter author ID: "));
                if (authorID == 0) {
                    System.out.println(BE2.ANSI_RED + "Author ID cannot be 0." + BE2.ANSI_RESET);
                }
            } catch (NumberFormatException e) {
                System.out.println(BE2.ANSI_RED + "Author ID cannot be empty." + BE2.ANSI_RESET);
            }
        }

        System.out.println();
        System.out.println("Publisher list:");
        DatabaseHelper.printPublisherListMini();
        System.out.println();

        int publisherID = 0;
        while (publisherID == 0) {
            try {
                publisherID = Integer.parseInt(InputHelper.inputInt("Enter publisher ID: "));
                if (publisherID == 0) {
                    System.out.println(BE2.ANSI_RED + "Publisher ID cannot be 0." + BE2.ANSI_RESET);
                }
            } catch (NumberFormatException e) {
                System.out.println(BE2.ANSI_RED + "Publisher ID cannot be empty." + BE2.ANSI_RESET);
            }
        }

        LocalDate publishDate = null;
        while (publishDate == null) {
            publishDate = InputHelper.inputDate("Enter publish date (yyyy-MM-dd): ");
            if (publishDate == null) {
                System.out.println(BE2.ANSI_RED + "Publish date cannot be empty." + BE2.ANSI_RESET);
            }
        }

        int price = 0;
        while (price <= 0) {
            try {
                price = Integer.parseInt(InputHelper.inputInt("Enter price: "));
                if (price == 0) {
                    System.out.println(BE2.ANSI_RED + "Price cannot be 0." + BE2.ANSI_RESET);
                }
                if (price < 0) {
                    System.out.println(BE2.ANSI_RED + "Price cannot be negative." + BE2.ANSI_RESET);
                }
            } catch (NumberFormatException e) {
                System.out.println(BE2.ANSI_RED + "Price cannot be empty." + BE2.ANSI_RESET);
            }
        }

        Book book = new Book(title, categoryID, authorID, publisherID, publishDate, price, 0);
        DatabaseHelper.insertBook(book);
    }

    public static void updateBook() {
        int bookID = 0;

        while (!doesBookExist(bookID)) {
            try {
                String input = InputHelper.inputInt("Enter book ID: ");
                if (!"EMPTY_INPUT".equals(input)) {
                    bookID = Integer.parseInt(input);
                    if (bookID == 0 || !doesBookExist(bookID)) {
                        System.out.println(BE2.ANSI_RED + "Book ID cannot be 0 and must exist in the database." + BE2.ANSI_RESET);
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println(BE2.ANSI_RED + "Book ID must be an integer." + BE2.ANSI_RESET);
            }
        }
    
        Book oldBook = getBookById(bookID);
    
        String title = InputHelper.inputString("Enter book title (leave empty to keep the old title): ");
        if (title == null || title.isEmpty()) {
            title = oldBook.getTitle();
        }

        System.out.println();
        System.out.println("Category list:");
        CategoryManagement.showAllCategory();
        System.out.println();
    
        int categoryID = oldBook.getCategoryID();
        try {
            String input = InputHelper.inputInt("Enter category ID (leave empty to keep the old category ID): ");
            if (!"EMPTY_INPUT".equals(input)) {
                categoryID = Integer.parseInt(input);
            }
        } catch (NumberFormatException e) {
            System.out.println(BE2.ANSI_RED + "Category ID must be an integer." + BE2.ANSI_RESET);
        }

        System.out.println();
        System.out.println("Author list:");
        DatabaseHelper.printAuthorListMini();
        System.out.println();
    
        int authorID = oldBook.getAuthorID();
        try {
            String input = InputHelper.inputInt("Enter author ID (leave empty to keep the old author ID): ");
            if (!"EMPTY_INPUT".equals(input)) {
                authorID = Integer.parseInt(input);
            }
        } catch (NumberFormatException e) {
            System.out.println(BE2.ANSI_RED + "Author ID must be an integer." + BE2.ANSI_RESET);
        }

        System.out.println();
        System.out.println("Publisher list:");
        DatabaseHelper.printPublisherListMini();
        System.out.println();

        int publisherID = oldBook.getPublisherID();
        try {
            String input = InputHelper.inputInt("Enter publisher ID (leave empty to keep the old publisher ID): ");
            if (!"EMPTY_INPUT".equals(input)) {
                publisherID = Integer.parseInt(input);
            }
        } catch (NumberFormatException e) {
            System.out.println(BE2.ANSI_RED + "Publisher ID must be an integer." + BE2.ANSI_RESET);
        }

        LocalDate publishDate = oldBook.getPublishDate();
        LocalDate inputDate = InputHelper.inputDate("Enter publish date (yyyy-MM-dd) (leave empty to keep the old publish date): ");
        if (inputDate != null) {
            publishDate = inputDate;
        }

        int price = oldBook.getPrice();
        try {
            String input = InputHelper.inputInt("Enter price (leave empty to keep the old price): ");
            if (!"EMPTY_INPUT".equals(input)) {
                price = Integer.parseInt(input);
            }
        } catch (NumberFormatException e) {
            System.out.println(BE2.ANSI_RED + "Price must be an integer." + BE2.ANSI_RESET);
        }

        int status = oldBook.getStatus();
    
        Book newBook = new Book(bookID, title, categoryID, authorID, publisherID, publishDate, price, status);
        DatabaseHelper.updateBook(newBook);
    }     

    public static void deleteBook() {
        int bookID = 0;
        while (!doesBookExist(bookID)) {
            try {
                bookID = Integer.parseInt(InputHelper.inputInt("Enter book ID: "));
                if (!doesBookExist(bookID)) {
                    System.out.println(BE2.ANSI_RED + "Book ID cannot be 0 and must exist in the database." + BE2.ANSI_RESET);
                } else {
                    if (getBookById(bookID).getStatus() == 1) {
                        System.out.println(BE2.ANSI_RED + "Book is being borrowed." + BE2.ANSI_RESET);
                        bookID = 0;
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println(BE2.ANSI_RED + "Book ID cannot be empty." + BE2.ANSI_RESET);
            }
        }
    
        System.out.println("Book id: " + bookID);
        DatabaseHelper.deleteBook(bookID);
    }  

    public static void searchBookByCategory() {
        CategoryManagement.showAllCategory();

        try {
            int categoryID = Integer.parseInt(InputHelper.inputInt("Enter category ID: "));
            List<Book> bookList = DatabaseHelper.getBookListByCategory(categoryID);

            if (bookList.size() > 0) {
                System.out.println("Book count: " + bookList.size());
                System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------");
                showBookTableHeader();

                for (Book book : bookList) {
                    System.out.println(book);
                }
            } else {
                System.out.println(BE2.ANSI_RED + "No book found with the category ID " + categoryID + "." + BE2.ANSI_RESET);
            }
        } catch (NumberFormatException e) {
            System.out.println(BE2.ANSI_RED + "Category ID cannot be empty." + BE2.ANSI_RESET);
        }
    }

    public static void searchBookByAuthor() {
        try {
            int authorID = Integer.parseInt(InputHelper.inputInt("Enter author ID: "));
            List<Book> bookList = DatabaseHelper.getBookListByAuthor(authorID);
            
            if (bookList.size() > 0) {
                System.out.println("Book count: " + bookList.size());
                System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------");
                showBookTableHeader();

                for (Book book : bookList) {
                    System.out.println(book);
                }
            } else {
                System.out.println(BE2.ANSI_RED + "No book found with the author ID " + authorID + "." + BE2.ANSI_RESET);
            }
        } catch (NumberFormatException e) {
            System.out.println(BE2.ANSI_RED + "Author ID cannot be empty." + BE2.ANSI_RESET);
        }
    }

    public static void searchBookByPublisher() {
        try {
            int publisherID = Integer.parseInt(InputHelper.inputInt("Enter publisher ID: "));
            List<Book> bookList = DatabaseHelper.getBookListByPublisher(publisherID);
            
            if (bookList.size() > 0) {
                System.out.println("Book count: " + bookList.size());
                System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------");
                showBookTableHeader();

                for (Book book : bookList) {
                    System.out.println(book);
                }
            } else {
                System.out.println(BE2.ANSI_RED + "No book found with the publisher ID " + publisherID + "." + BE2.ANSI_RESET);
            }
        } catch (NumberFormatException e) {
            System.out.println(BE2.ANSI_RED + "Publisher ID cannot be empty." + BE2.ANSI_RESET);
        }
    }

    public static void searchBookByTitle() {
        try {
            String title = InputHelper.inputString("Enter book title: ");
            List<Book> bookList = DatabaseHelper.getBookListByTitle(title);
    
            if (bookList.size() > 0) {
                System.out.println("Book count: " + bookList.size());
                System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------");
                showBookTableHeader();

                for (Book book : bookList) {
                    System.out.println(book);
                }
            } else {
                System.out.println(BE2.ANSI_RED + "No book found with the title '" + title + "'." + BE2.ANSI_RESET);
            }
        } catch (Exception e) {
            System.out.println(BE2.ANSI_RED + "An error occurred while searching for books by title." + BE2.ANSI_RESET);
        }
    }    

    public static boolean doesBookExist(int bookID) {
        var bookList = DatabaseHelper.getBookList();

        for (Book b : bookList) {
            if (b.getBookID() == bookID) {
                return true;
            }
        }

        return false;
    }

    public static Book getBookById(int bookID) {
        var bookList = DatabaseHelper.getBookList();

        for (Book b : bookList) {
            if (b.getBookID() == bookID) {
                return b;
            }
        }

        return null;
    }

    public static void updateBookStatus(int status, int bookID) {
        var bookList = DatabaseHelper.getBookList();

        for (Book b : bookList) {
            if (b.getBookID() == bookID) {
                b.setStatus(status);
                DatabaseHelper.updateBook(b);
                break;
            }
        }
    }

    private  static void showBookTableHeader() {
        System.out.println(String.format("%-5s | %-30s | %-20s | %-20s | %-30s | %-15s | %-10s | %-15s", "ID" , "Title", "Category", "Author", "Publisher", "Publish Date", "Price (VND)", "Status"));
        System.out.println("------|--------------------------------|----------------------|----------------------|--------------------------------|-----------------|------------|----------------");
    }
}
