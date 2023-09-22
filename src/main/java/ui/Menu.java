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
        System.out.println("----------------------------------");
        int option = 0;
        menu.initMenu();
        System.out.println(menu.title);
        for (int i = 0; i < menu.options.size(); i++)
        {
            System.out.println(i + 1 + ". " + menu.options.get(i));
        }
        while (option < 1 || option > menu.options.size())
        {
            option = InputHelper.inputInt("Choose an option: ");
            if (option < 1 || option > menu.options.size())
                System.out.println(BE2.ANSI_RED + "Option out of range, please try again." + BE2.ANSI_RESET);
        }
        menu.handleOption(option);
    }
    
    protected abstract void handleOption(int option);
}
