package main.java.ui;

import main.java.BE2;
import main.java.core.TicketManagement;

public class ManageBookIssuesMenu extends Menu {
    @Override
    protected void initMenu() {
        System.out.println("---MANAGE BOOK ISSUES MENU------------------------------------------------------------------------------------------------------------------------------------");
        options.add("Create Book Issue");
        options.add("View All Book Issues");
        options.add("Mark Book As Returned");
        options.add("Back To Admin Menu");
    }

    @Override
    protected void handleOption(int option) {
        switch (option) {
            case 1:
                System.out.println("---CREATE BOOK ISSUE------------------------------------------------------------------------------------------------------------------------------------------");
                TicketManagement.createTicket();
                break;
            case 2: 
                System.out.println("---VIEW ALL BOOK ISSUES---------------------------------------------------------------------------------------------------------------------------------------");
                TicketManagement.showAllTickets();
                break;
            case 3:
                System.out.println("---MARK BOOK AS RETURNED--------------------------------------------------------------------------------------------------------------------------------------");
                TicketManagement.markTicketReturn();
                break;
            case 4:
                BE2.currentMenu = new AdminMenu();
                break;
        }
    }
}
