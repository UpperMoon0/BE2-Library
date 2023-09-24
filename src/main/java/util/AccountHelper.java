package main.java.util;

import main.java.BE2;
import main.java.ui.AdminMenu;
import main.java.ui.CustomerMenu;

public abstract class AccountHelper {
    public static void loginCustomer() {
        String username = InputHelper.inputString("Enter username: ");
        String password = InputHelper.inputString("Enter password: ");

        if (DatabaseHelper.doesCustomerAccountMatch(username, password)) {
            System.out.println(BE2.ANSI_GREEN + "Login successful." + BE2.ANSI_RESET);
            BE2.currentUsername = username;
            BE2.currentMenu = new CustomerMenu();
        } else {
            System.out.println(BE2.ANSI_RED + "Incorrect username or password." + BE2.ANSI_RESET);
            System.out.println(BE2.ANSI_RED + "Login failed." + BE2.ANSI_RESET);
        }
    }

    public static void registerCustomer() {
        String username = "";
        while (isUsernameValid(username) != 0) {
            username = InputHelper.inputString("Enter username: ");
            int isValid = isUsernameValid(username);

            if (isValid == 0)
                break;

            switch (isValid ) {
                case 1:
                    System.out.println(BE2.ANSI_RED + "Username already exists." + BE2.ANSI_RESET);
                    break;
                case 2:
                    System.out.println(BE2.ANSI_RED + "Username is empty." + BE2.ANSI_RESET);
                    break;
                case 3:
                    System.out.println(BE2.ANSI_RED + "Username is too short." + BE2.ANSI_RESET);
                    break;
                case 4:
                    System.out.println(BE2.ANSI_RED + "Username is too long." + BE2.ANSI_RESET);
                    break;
                case 5:
                    System.out.println(BE2.ANSI_RED + "Username contains invalid characters." + BE2.ANSI_RESET);
                    break;
            }
            System.out.format(BE2.ANSI_RED + "Username must:%n-Be between 6 and 20 characters%n-Contain only letters and numbers%n" + BE2.ANSI_RESET);
        }

        String password = "";
        while (isPasswordValid(password) != 0) {
            password = InputHelper.inputString("Enter password: ");
            int isValid = isPasswordValid(password);

            if (isValid == 0)
                break;

            switch (isValid) {
                case 1:
                    System.out.println(BE2.ANSI_RED + "Password is empty." + BE2.ANSI_RESET);
                    break;
                case 2:
                    System.out.println(BE2.ANSI_RED + "Password is too short." + BE2.ANSI_RESET);
                    break;
                case 3:
                    System.out.println(BE2.ANSI_RED + "Password is too long." + BE2.ANSI_RESET);
                    break;
                case 4:
                    System.out.println(BE2.ANSI_RED + "Password does not meet complexity requirements." + BE2.ANSI_RESET);
                    break;
            }
            System.out.format(BE2.ANSI_RED + "Password must:%n-Be between 8 and 20 characters%n-Contain at least one uppercase letter%n-Contain at least one lowercase letter%n-Contain at least one number%n-Contain at least one special character%n" + BE2.ANSI_RESET);
        }

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

        DatabaseHelper.insertCustomer(username, password, firstName, lastName, age, email, phoneNumber, address);
        System.out.println(BE2.ANSI_GREEN + "Register successful." + BE2.ANSI_RESET);
    }

    public static void loginAdmin() {
        String username = InputHelper.inputString("Enter username: ");
        String password = InputHelper.inputString("Enter password: ");

        if (DatabaseHelper.doesAdminAccountMatch(username, password)) {
            System.out.println(BE2.ANSI_GREEN + "Login successful." + BE2.ANSI_RESET);
            BE2.currentUsername = username;
            BE2.currentMenu = new AdminMenu();
        } else {
            System.out.println(BE2.ANSI_RED + "Incorrect username or password." + BE2.ANSI_RESET);
            System.out.println(BE2.ANSI_RED + "Login failed." + BE2.ANSI_RESET);
        }
    }

    private static int isUsernameValid(String username) {
        if (username.matches("^[a-zA-Z0-9]{6,20}$")) {
            if (DatabaseHelper.doesCustomerUsernameExist(username))
                return 1; // Username already exists
            else
                return 0; // Username is valid
        } else {
            if (username.length() == 0) {
                return 2; // Username is empty
            } 
            
            if(username.length() < 6) {
                return 3; // Username is too short
            } 
            
            if (username.length() > 20) {
                return 4; // Username is too long
            } 
                
            return 5; // Username contains invalid characters
        }
    }
    
    private static int isPasswordValid(String password) {
        if (password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=])(?=\\S+$).{8,20}$")) {
            return 0; // Password is valid
        } else {
            if (password.length() == 0) {
                return 1; // Password is empty
            } 
            
            if(password.length() < 8) {
                return 2; // Password is too short
            } 
            
            if (password.length() > 20) {
                return 3; // Password is too long
            } 
                
            return 4; // Password does not meet complexity requirements
        }
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
}
