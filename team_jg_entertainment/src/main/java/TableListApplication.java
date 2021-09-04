import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TableListApplication {

    public static void main(String[] args) {
        List<Table> tables = new ArrayList<>();
        while (true) {
            System.out.println("Table selection:");
            System.out.println("1. Add table.");
            System.out.println("2. Delete table.");
            System.out.println("3. Show all tables.");
            System.out.println("4. Exit");

            System.out.println();

            System.out.println("To continue, put option's number (1-4): ");
            Scanner scanner = new Scanner(System.in);
            int optionsNumber = Integer.parseInt(scanner.nextLine());

            switch (optionsNumber) {
                case 1 -> {
                    System.out.println("Enter table number: ");
                    String tableNumber = scanner.nextLine();
                    System.out.println("Enter persons per table: ");
                    int capacityOfTable = scanner.nextInt();

                    Table table = new Table(tableNumber, capacityOfTable);
                    tables.add(table);
                    System.out.println("Table has been added");
                }
                case 2 -> {
                    System.out.println("Enter table number:");
                    String tableNumber = scanner.nextLine();
                    tables.remove(new Table(tableNumber));
                    System.out.println("Table" + tableNumber + " was removed from list.");
                }
                case 3 -> {
                    System.out.println("Table list: ");
                    for (Table table : tables) {
                        System.out.println(table);
                    }
                    System.out.println("Table list end.");
                }
                case 4 -> {
                    System.out.println("Good by!");
                    System.exit(0);
                }
            }
            System.out.println();
        }

    }
}







