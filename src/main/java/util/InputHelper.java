package main.java.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

import main.java.BE2;

public abstract class InputHelper {
    private static Scanner scanner = BE2.scanner;

    public static String inputInt(String msg) {
        String result = "";
    
        while (true) {
            try {
                if (msg != null)
                    System.out.print(msg);
                String input = scanner.nextLine();
                if (input.isEmpty()) {
                    return "EMPTY_INPUT";
                }
                result = Integer.toString(Integer.parseInt(input));
                break;
            } catch (InputMismatchException | NumberFormatException e) {
                System.out.println(BE2.ANSI_RED + "Invalid input. Please try again." + BE2.ANSI_RESET);
            }
        }
    
        return result;
    }
    
    public static String inputString(String msg) {
        String result = "";
        if (msg != null)
            System.out.print(msg);
        result = scanner.nextLine();
        if (result.trim().isEmpty()) {
            return null;
        }
        return result;
    }       

    public static LocalDate inputDate(String msg) {
        LocalDate result = null;
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
            .appendPattern("[yyyy-MM-dd]")
            .appendPattern("[yyyy-M-d]")
            .appendPattern("[yyyy-MM-d]")
            .appendPattern("[yyyy-M-dd]")
            .toFormatter();
    
        while (result == null) {
            if (msg != null)
                System.out.print(msg);
            String input = scanner.nextLine();
    
            if (!input.trim().isEmpty()) {
                try {
                    result = LocalDate.parse(input, formatter);
                } catch (DateTimeParseException e) {
                    System.out.println(BE2.ANSI_RED + "Invalid date format. Please try again." + BE2.ANSI_RESET);
                }
            } else {
                return null; 
            }
        }
    
        return result;
    }    


    public static String inputBoolean(String msg) {
        String result = "";
    
        while (true) {
            try {
                if (msg != null)
                    System.out.print(msg);
                String input = scanner.nextLine().trim().toLowerCase();
                if (input.isEmpty()) {
                    return "EMPTY_INPUT";
                }
                switch (input) {
                    case "true":
                    case "t":
                    case "yes":
                    case "y":
                        result = "true";
                        break;
                    case "false":
                    case "f":
                    case "no":
                    case "n":
                        result = "false";
                        break;
                    default:
                        throw new IllegalArgumentException();
                }
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(BE2.ANSI_RED + "Invalid input. Please enter 'true', 'false', 'yes', 'no', 't', 'f', 'y', or 'n'." + BE2.ANSI_RESET);
            }
        }
    
        return result;
    }     
}
