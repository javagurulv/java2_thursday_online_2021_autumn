import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReservationDemo {
    public static void main(String[] args) {

        List<Reservation> reservations = new ArrayList<>();

        while (true) {
            System.out.println("Table reservation options");
            System.out.println("1. Add reservation");
            System.out.println("2. Check reservation");
            System.out.println("3. Exit");


            System.out.println("To continue, put option's number (1-4): ");
            Scanner scanner = new Scanner(System.in);
            int optionsNumber = Integer.parseInt(scanner.nextLine());

            switch (optionsNumber) {
                case 1: {
                    System.out.println("Enter Your name: ");
                    String clientName = scanner.nextLine();
                    System.out.println("Enter date, ex. (22.06.21): ");
                    String date = scanner.nextLine();
                    System.out.println("Enter number of persons (1-6)");
                    int numberOfPersons = scanner.nextInt();


                    Reservation reservation = new Reservation(clientName, numberOfPersons, date);
                    reservations.add(reservation);
                    System.out.println("Your reservation has been added");
                    break;
                }

                case 2: {
                    System.out.println("Reservation checking...");
                    for (Reservation reservation : reservations) {
                        System.out.println(reservation);
                    }
                    System.out.println("Checking done!");
                    break;
                }


                case 3: {
                    System.out.println("Reservation finished! Goodbye!");
                    System.exit(0);
                }
            }


        }


    }
}
