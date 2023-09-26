package main.java.core;

import java.time.LocalDate;

import main.java.BE2;
import main.java.util.DatabaseHelper;

public class Book {
    private int bookID;
    private String title;
    private int categoryID;
    private int authorID;
    private int publisherID;
    private LocalDate publishDate;
    private int price;
    private int status;

    public Book(int bookID, String title, int categoryID, int authorID, int publisherID, LocalDate publishDate, int price, int status) {
        this.bookID = bookID;
        this.title = title;
        this.categoryID = categoryID;
        this.authorID = authorID;
        this.publisherID = publisherID;
        this.publishDate = publishDate;
        this.price = price;
        this.status = status;
    }

    public Book(String title, int categoryID, int authorID, int publisherID, LocalDate publishDate, int price, int status) {
        this(0, title, categoryID, authorID, publisherID, publishDate, price, status);
    }

    public String getTitle() {
        return title;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public int getAuthorID() {
        return authorID;
    }

    public int getPublisherID() {
        return publisherID;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public int getPrice() {
        return price;
    }

    public int getStatus() {
        return status;
    }

    public int getBookID() {
        return bookID;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        String statusStr = "";

        switch (this.status) {
            case 0: 
                statusStr = BE2.ANSI_GREEN + "Available" + BE2.ANSI_RESET;
                break;
            case 1:
                statusStr = BE2.ANSI_RED + "Not available" + BE2.ANSI_RESET;
                break;
        }

        return String.format("%-5s | %-30s | %-20s | %-20s | %-30s | %-15s | %-10s | %-15s", bookID, title, DatabaseHelper.getCategoryNameFromID(categoryID), DatabaseHelper.getAuthorNameFromID(authorID), DatabaseHelper.getPublisherNameFromID(publisherID), publishDate, price * 1000, statusStr);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Book) {
            Book book = (Book) obj;
            if (this.title.equals(book.title) 
                && this.categoryID == book.categoryID
                && this.authorID == book.authorID 
                && this.publisherID == book.publisherID) {
                return true;
            }
        }
        return false;
    }
}
