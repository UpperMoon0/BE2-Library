package main.java.core;

import main.java.BE2;
import main.java.util.DatabaseHelper;
import main.java.util.InputHelper;

public abstract class CustomerManagement {
    public static void registerCustomer() {
        String firstName = "";
        while (isFirstNameValid(firstName) != 0) {
            firstName = InputHelper.inputString("Enter first name: ");
            int isValid = isFirstNameValid(firstName);

            switch(isValid) {
                case 1:
                    System.out.println(BE2.ANSI_RED + "First name is empty." + BE2.ANSI_RESET);
                    break;
                case 2:
                    System.out.println(BE2.ANSI_RED + "First name is too long." + BE2.ANSI_RESET);
                    break;
                case 3:
                    System.out.println(BE2.ANSI_RED + "First name contains invalid characters." + BE2.ANSI_RESET);
                    break;
            }
        }

        String lastName = "";
        while (isLastNameValid(lastName) != 0 && lastName != null)    {
            lastName = InputHelper.inputString("Enter last name (optional): ");
            int isValid = isLastNameValid(lastName);

            switch(isValid) {
                case 1:
                    lastName = null;
                    break;
                case 2:
                    System.out.println(BE2.ANSI_RED + "Last name is too long." + BE2.ANSI_RESET);
                    break;
                case 3:
                    System.out.println(BE2.ANSI_RED + "Last name contains invalid characters." + BE2.ANSI_RESET);
                    break;
            }
        }

        int age = 0;
        while(age == 0) {
            try {
                age = Integer.parseInt(InputHelper.inputInt("Enter your age: "));
                if (age < 0) {
                    System.out.println(BE2.ANSI_RED + "Age cannot be negative." + BE2.ANSI_RESET);
                    age = 0;
                }
            } catch (NumberFormatException e) {
                System.out.println(BE2.ANSI_RED + "Age cannot be empty." + BE2.ANSI_RESET);
            }
        }

        String email = "";
        while (isEmailValid(email) != 0) {
            email = InputHelper.inputString("Enter your email: ");

            if (email == null) {
                System.out.println(BE2.ANSI_RED + "Email cannot be empty." + BE2.ANSI_RESET);
                email = "";
            } else {
                int isValid = isEmailValid(email);

                switch(isValid) {
                    case 1:
                        System.out.println(BE2.ANSI_RED + "Email already exists." + BE2.ANSI_RESET);
                        break;
                    case 2:
                        System.out.println(BE2.ANSI_RED + "Email is empty." + BE2.ANSI_RESET);
                        break;
                    case 3:
                        System.out.println(BE2.ANSI_RED + "Email is too long." + BE2.ANSI_RESET);
                        break;
                    case 4:
                        System.out.println(BE2.ANSI_RED + "Invalid email." + BE2.ANSI_RESET);
                        break;
                }
            }
        }

        String phoneNumber = "";
        while (isPhoneNumberValid(phoneNumber) != 0 && phoneNumber != null) {
            phoneNumber = InputHelper.inputString("Enter your phone number (optional): ");
            int isValid = isPhoneNumberValid(phoneNumber);

            switch(isValid) {
                case 1:
                    System.out.println(BE2.ANSI_RED + "Phone number already exists." + BE2.ANSI_RESET);
                    break;
                case 2:
                    phoneNumber = null;
                    break;
                case 3:
                    System.out.println(BE2.ANSI_RED + "Invalid phone number." + BE2.ANSI_RESET);
                    break;
            }
        }

        String address = "";
        while (isAddressValid(address) != 0 && address != null) {
            address = InputHelper.inputString("Enter your address (optional): ");
            int isValid = isAddressValid(address);

            switch(isValid) {
                case 1:
                    address = null;
                    break;
                case 2:
                    System.out.println(BE2.ANSI_RED + "Address is too long." + BE2.ANSI_RESET);
                    break;
                case 3:
                    System.out.println(BE2.ANSI_RED + "Address contains invalid characters." + BE2.ANSI_RESET);
                    break;
            }
        }

        Customer customer = new Customer(DatabaseHelper.getAdminIDFromUsername(BE2.currentAdmin), firstName, lastName, age, email, phoneNumber, address);
        DatabaseHelper.insertCustomer(customer);
        System.out.println(BE2.ANSI_GREEN + "Register successful." + BE2.ANSI_RESET);
    }

    private static int isFirstNameValid(String firstName) {
        if (firstName.matches("^[a-zA-Z\\s]{1,10}$"))
            return 0; // First name valid
        else {
            if (firstName.length() == 0)
                return 1; // First name empty

            if (firstName.length() > 10)
                return 2; // First name too long

            return 3; // Invalid characters
        }
    }

    private static int isLastNameValid(String lastName) {
        if (lastName == null || lastName.length() == 0)
            return 1; // Skip
    
        if (lastName.matches("^[a-zA-Z\\s]{1,10}$"))
            return 0; // Last name valid
        else {
            if (lastName.length() > 10)
                return 2; // Last name too long
                
            return 3; // Invalid characters 
        }
    }

    private static int isEmailValid(String email) {
        if (email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            if (DatabaseHelper.doesCustomerEmailExist(email))
                return 1; // Email already exists

            return 0; // Email is valid
        } else {
            if (email.length() == 0) {
                return 2; // Email is empty
            }
            
            if (email.length() > 30) {
                return 3; // Email is too long
            } 
                
            return 4; // Invalid email
        }
    }

    private static int isPhoneNumberValid(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.length() == 0)
            return 2; // Skip

        if (phoneNumber.matches("^[0-9]{10}$")) {
            if (DatabaseHelper.doesCustomerPhoneNumberExist(phoneNumber))
                return 1; // Phone number already exists

            return 0; // Phone number is valid
        } else  
            return 3; // Invalid phone number
    }

    private static int isAddressValid(String address) {
        if (address == null || address.length() == 0)
            return 1; // Skip

        if (address.matches("^[a-zA-Z0-9\\s.,]{1,40}$")) {
            return 0; // Address is valid
        } else {
            if (address.length() > 40) {
                return 2; // Address is too long
            }
                
            return 3; // Contains invalid characters
        }
    }

    public static void showAllCustomers() {
        var customerList = DatabaseHelper.getCustomerList();
        System.out.format("%-5s | %-30s | %-5s | %-30s | %-15s | %-40s%n", "ID", "Name", "Age", "Email", "Phone number", "Address");
        System.out.println("------|--------------------------------|-------|--------------------------------|-----------------|-----------------------------------------");

        for (Customer c : customerList) {
            System.out.println(c);
        }
    }

    public static boolean doesCustomerExist(int CustomerID) {
        var customerList = DatabaseHelper.getCustomerList();

        for (Customer c : customerList) {
            if (c.getCustomerID() == CustomerID)
                return true;
        }

        return false;
    }
}
