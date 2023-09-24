package main.java.core;

import java.time.LocalDate;

import main.java.util.DatabaseHelper;

public class Book {
    private String title;
    private int categoryID;
    private int authorID;
    private int publisherID;
    private LocalDate publishDate;
    private int price;
    private int status;

    public Book(String title, int categoryID, int authorID, int publisherID, LocalDate publishDate, int price, int status) {
        this.title = title;
        this.categoryID = categoryID;
        this.authorID = authorID;
        this.publisherID = publisherID;
        this.publishDate = publishDate;
        this.price = price;
        this.status = status;
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

    @Override
    public String toString() {
        String statusStr = "";

        switch (this.status) {
            case 0: 
                statusStr = "Available";
                break;
            case 1:
                statusStr = "Not available";
                break;
        }

        return String.format("%-30s | %-20s | %-20s | %-30s | %-15s | %-10s | %-15s", title, DatabaseHelper.getCategoryNameFromID(categoryID), DatabaseHelper.getAuthorNameFromID(authorID), DatabaseHelper.getPublisherNameFromID(publisherID), publishDate, price * 1000, statusStr);
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
