package main.java.core;

import java.time.LocalDate;

import main.java.BE2;
import main.java.util.DatabaseHelper;
import main.java.util.InputHelper;

public abstract class TicketManagement {
    public static void createTicket() {
        CustomerManagement.showAllCustomers();

        int customerID = 0;

        while (!CustomerManagement.doesCustomerExist(customerID)) {
            try {
                customerID = Integer.parseInt(InputHelper.inputInt("Enter customer ID: "));

                if (!CustomerManagement.doesCustomerExist(customerID))
                    System.out.println(BE2.ANSI_RED + "Customer does not exist" + BE2.ANSI_RESET);
            } catch (NumberFormatException e) {
                System.out.println(BE2.ANSI_RED + "Customer ID cannot be empty" + BE2.ANSI_RESET);
            }
        }

        BooksManagement.showAllBooks(false);

        int bookID = 0;

        while (!BooksManagement.doesBookExist(bookID)) {
            try {
                bookID = Integer.parseInt(InputHelper.inputInt("Enter book ID: "));

                if (!BooksManagement.doesBookExist(bookID))
                    System.out.println(BE2.ANSI_RED + "Book does not exist" + BE2.ANSI_RESET);
            } catch (NumberFormatException e) {
                System.out.println(BE2.ANSI_RED + "Book ID cannot be empty" + BE2.ANSI_RESET);
            }
        }

        LocalDate returnDate = null;

        while (returnDate == null) {
            returnDate = InputHelper.inputDate("Enter return date (yyyy-MM-dd): ");

            if (returnDate == null) {
                System.out.println(BE2.ANSI_RED + "Return date cannot be empty" + BE2.ANSI_RESET);
                continue;
            }

            if (!returnDate.isAfter(LocalDate.now())) {
                returnDate = null;
                System.out.println(BE2.ANSI_RED + "Return date must be after today" + BE2.ANSI_RESET);
            }
        }

        Ticket ticket = new Ticket(customerID, bookID, returnDate);
        DatabaseHelper.insertTicket(ticket);
        BooksManagement.updateBookStatus(1, bookID);
        System.out.println(BE2.ANSI_GREEN + "Ticket created successfully" + BE2.ANSI_RESET);
    }

    public static void showAllTickets() {
        var ticketList = DatabaseHelper.getTicketList();

        if (ticketList.size() == 0) {
            System.out.println(BE2.ANSI_RED + "No tickets found" + BE2.ANSI_RESET);
        } else {
            System.out.format("%-5s | %-30s | %-30s | %-15s | %-15s | %-15s | %-10s%n", "ID", "Customer name", "Book title", "Fee (VND)", "Borrow date", "E. Return date", "Status");
            System.out.println("------|--------------------------------|--------------------------------|-----------------|-----------------|-----------------|-----------");
            for (Ticket ticket : ticketList) {
                System.out.println(ticket);
            }
        }
    }

    public static boolean doesTicketExist(int ticketID) {
        var ticketList = DatabaseHelper.getTicketList();

        for (Ticket ticket : ticketList) {
            if (ticket.getTicketID() == ticketID) {
                return true;
            }
        }

        return false;
    }

    public static void updateTicketList() {
        var ticketList = DatabaseHelper.getTicketList();

        for (Ticket ticket : ticketList) {
            if (ticket.getStatus() == 0 && ticket.getReturnDate().isBefore(LocalDate.now())) {
                ticket.setStatus(2);
                ticket.setFee(BooksManagement.getBookById(ticket.getBookID()).getPrice());
                DatabaseHelper.updateTicket(ticket);
            }
        }
    }

    public static void markTicketReturn() {
        showAllTickets();

        int ticketID = 0;

        while (!doesTicketExist(ticketID)) {
            try {
                ticketID = Integer.parseInt(InputHelper.inputInt("Enter ticket ID to mark as returned: "));

                if (!doesTicketExist(ticketID))
                    System.out.println(BE2.ANSI_RED + "Ticket does not exist" + BE2.ANSI_RESET);
            } catch (NumberFormatException e) {
                System.out.println(BE2.ANSI_RED + "Ticket ID cannot be empty" + BE2.ANSI_RESET);
            }
        }

        var ticketList = DatabaseHelper.getTicketList();

        for (Ticket ticket : ticketList) {
            if (ticket.getTicketID() == ticketID) {
                if (ticket.getStatus() != 1) {
                    ticket.setStatus(1);
                    DatabaseHelper.updateTicket(ticket);
                    BooksManagement.updateBookStatus(0, ticket.getBookID());
                    System.out.println(BE2.ANSI_GREEN + "Ticket marked as returned" + BE2.ANSI_RESET);
                } else {
                    System.out.println(BE2.ANSI_RED + "Ticket has already been returned" + BE2.ANSI_RESET);
                }
                return;
            }
        }
    }
}
