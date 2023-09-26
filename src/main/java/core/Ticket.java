package main.java.core;

import java.time.LocalDate;

import main.java.BE2;
import main.java.util.DatabaseHelper;

public class Ticket {
    private int ticketID;
    private int adminID;
    private int customerID;
    private int bookID;
    private int fee;
    LocalDate borrowDate;
    LocalDate returnDate;
    private int status;

    public Ticket(int ticketID, int adminID, int customerID, int bookID, int fee, LocalDate borrowDate,LocalDate returnDate, int status) {
        this.ticketID = ticketID;
        this.adminID = adminID;
        this.customerID = customerID;
        this.bookID = bookID;
        this.fee = fee;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.status = status;
    }
    
    public Ticket(int customerID, int bookID, LocalDate returnDate) {
        this(0, DatabaseHelper.getAdminIDFromUsername(BE2.currentAdmin), customerID, bookID, 0, LocalDate.now(), returnDate, 0);
    }

    public int getTicketID() {
        return ticketID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public int getBookID() {
        return bookID;
    }

    public int getFee() {
        return fee;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public int getStatus() {
        return status;
    }

    public int getAdminID() {
        return adminID;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        Customer customer = DatabaseHelper.getCustomerByID(this.customerID);
        String customerName = (customer == null) ? " " : customer.getFirstName() + " " + customer.getLastName();

        Book book = BooksManagement.getBookById(bookID);
        String bookTitle = (book == null) ? " " : book.getTitle();

        String statusString = "";
        switch (status) {
            case 0:
                statusString = BE2.ANSI_YELLOW + "Borrowing" + BE2.ANSI_RESET;
                break;
            case 1:
                statusString = BE2.ANSI_GREEN + "Returned" + BE2.ANSI_RESET;
                break;
            case 2:
                statusString = BE2.ANSI_RED + "Overdue" + BE2.ANSI_RESET;
                break;
        } 

        return String.format("%-5s | %-30s | %-30s | %-15s | %-15s | %-15s | %-10s", ticketID, customerName, bookTitle, fee * 1000, borrowDate, returnDate, statusString);
    }
}
