package main.java.util;

import java.util.InputMismatchException;
import java.util.Scanner;

import main.java.BE2;

public abstract class InputHelper {
    private static Scanner scanner = BE2.scanner;

    public static int inputInt(String msg) {
        int result = 0;
    
        while (true) {
            try {
                if (msg != null)
                    System.out.print(msg);
                String input = scanner.nextLine();
                if (input.isEmpty()) {
                    throw new InputMismatchException();
                }
                result = Integer.parseInt(input);
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
        return result;
    }
}
