package main.java.ui;

import java.util.ArrayList;
import java.util.List;

import main.java.BE2;
import main.java.util.InputHelper;

public abstract class Menu {
    protected String title;
    protected List<String> options = new ArrayList<>();
    
    protected abstract void initMenu();
    
    public static void showMenu(Menu menu) {
        int option = 0;
        menu.options.clear();
        menu.initMenu();
        for (int i = 0; i < menu.options.size(); i++)
        {
            System.out.println(i + 1 + ". " + menu.options.get(i));
        }
        while (option < 1 || option > menu.options.size())
        {
            try {
                option = Integer.parseInt(InputHelper.inputInt("Choose an option: "));
                if (option < 1 || option > menu.options.size())
                    System.out.println(BE2.ANSI_RED + "Option out of range, please try again." + BE2.ANSI_RESET);
            } catch (NumberFormatException e) {
                System.out.println(BE2.ANSI_RED + "Option cannot be empty, please try again." + BE2.ANSI_RESET);
                continue;
            }
        }
        menu.handleOption(option);
    }
    
    protected abstract void handleOption(int option);
}
