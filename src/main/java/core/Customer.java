package main.java.core;

public class Customer {
    private int customerID;
    private int adminID;
    private String firstName;
    private String lastName;
    private int age;
    private String email;
    private String phoneNumber;
    private String address;

    public Customer(int customerID, int adminID, String firstName, String lastName, int age, String email, String phoneNumber, String address) {
        this.customerID = customerID;

        this.adminID = adminID;

        this.firstName = firstName;

        if (lastName != null)
            this.lastName = lastName;
        else
            this.lastName = "";

        this.age = age;

        this.email = email;

        if (phoneNumber != null)
            this.phoneNumber = phoneNumber;
        else
            this.phoneNumber = "";
        
        if (address != null)
            this.address = address;
        else
            this.address = "";
    }

    public Customer(int adminID, String firstName, String lastName, int age, String email, String phoneNumber, String address) {
        this(0, adminID, firstName, lastName, age, email, phoneNumber, address);
    }

    public int getCustomerID() {
        return customerID;
    }

    public int getAdminID() {
        return adminID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return this.age;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return this.address;
    }

    @Override 
    public String toString() {return String.format("%-5s | %-30s | %-5s | %-30s | %-15s | %-40s", customerID, firstName + " " + lastName, age, email, phoneNumber, address);
        
    }
}



