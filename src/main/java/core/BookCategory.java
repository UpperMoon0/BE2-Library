package main.java.core;

public class BookCategory {
    private int categoryID;
    private String categoryName;
    private String description;

    public BookCategory(int categoryID, String categoryName, String description) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
        this.description = description;
    }

    public BookCategory(String categoryName, String description) {
        this(0, categoryName, description);
    }

    public int getCategoryID() {
        return categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return String.format("%-5s | %-30s | %-100s", categoryID, categoryName, description);
    }
}
