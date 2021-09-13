package lv.javaguru.java2.jg_entertainment;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuListApplication {
    public static void main(String[] args) {
        List<Menu> menus = new ArrayList<>();
        while (true) {
            printProgramMenu();
            int menuNumber = getMenuNumberFromUser();
            executeSelectedMenuItem(menus, menuNumber);
        }
    }

    private static void printProgramMenu() {
        System.out.println("Program menu:");
        System.out.println("1. Add menu to list");
        System.out.println("2. Delete menu from list");
        System.out.println("3. Show all menus in the list");
        System.out.println("4. Exit");
        System.out.println("");
    }

    private static int getMenuNumberFromUser() {
        System.out.println("Enter menu item number to execute:");
        Scanner scanner = new Scanner(System.in);
        return Integer.parseInt(scanner.nextLine());
    }

    private static void executeSelectedMenuItem(List<Menu> menus, int selectedMenu) {
        switch (selectedMenu) {
            case 1: {
                addMenuAction(menus);
                break;
            }
            case 2: {
                removeMenuAction(menus);
                break;
            }
            case 3: {
                printAllMenusAction(menus);
                break;
            }
            case 4: {
                exitProgramAction();
                break;

            }
        }
    }

    private static void exitProgramAction() {
        System.out.println("Good by!");
        System.exit(0);
    }

    private static void printAllMenusAction(List<Menu> menus) {
        System.out.println("Menu list: ");
        for ( Menu menu : menus ) {
            System.out.println(menu);
        }
        System.out.println("Menu list end.");
    }

    private static void removeMenuAction(List<Menu> menus) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter menu number: ");
        int number = scanner.nextInt();
        menus.remove(new Menu(number));
        System.out.println("That menu was removed from the order.");
    }

    private static void addMenuAction(List<Menu> menus) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter menu number: ");
        int number = scanner.nextInt();
        Menu menu = new Menu(number);
        menus.add(menu);
        System.out.println("Your menu was added to the order.");
    }
}
