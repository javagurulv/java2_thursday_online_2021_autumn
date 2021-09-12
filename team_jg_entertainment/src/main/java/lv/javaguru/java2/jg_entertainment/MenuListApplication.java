package lv.javaguru.java2.jg_entertainment;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuListApplication {
    public static void main(String[] args) {
        List<Menu> menus = new ArrayList<>();

        while (true) {
            System.out.println("Program menu:");
            System.out.println("1. Add menu to list");
            System.out.println("2. Delete menu from list");
            System.out.println("3. Show all menus in the list");
            System.out.println("4. Exit");

            System.out.println("");

            System.out.println("Enter menu item number to execute:");
            Scanner scanner = new Scanner(System.in);
            int userChoice = Integer.parseInt(scanner.nextLine());

            switch (userChoice) {
                case 1: {
                    System.out.println("Enter menu number: ");
                    int number = scanner.nextInt();
                    Menu menu = new Menu(number);
                    menus.add(menu);
                    System.out.println("Your menu was added to the order.");
                    break;
                }
                case 2: {
                    System.out.println("Enter menu number: ");
                    int number = scanner.nextInt();
                    menus.remove(new Menu(number));
                    System.out.println("That menu was removed from the order.");
                    break;
                }
                case 3: {
                    System.out.println("Book list: ");
                    for (Menu menu : menus) {
                        System.out.println(menu);
                    }
                    System.out.println("Menu list end.");
                    break;
                }
                case 4: {
                    System.out.println("Good by!");
                    System.exit(0);
                }
            }
            System.out.println("");
        }
    }
}
