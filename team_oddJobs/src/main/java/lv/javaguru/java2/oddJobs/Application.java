package lv.javaguru.java2.oddJobs;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        List<Specialist> specialists = new ArrayList<>();
        List<Client> clients = new ArrayList<>();


        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Create client account");
            System.out.println("2. Create specialist account");
            System.out.println("3. Find specialist by profession");
            System.out.println("4. Show all specialist");
            System.out.println("5. Exit");
            Scanner scanner = new Scanner(System.in);
            int userChoice = Integer.parseInt(scanner.nextLine());

            switch (userChoice) {
                case 1: {
                    System.out.println("Enter your name");
                    String clientName = scanner.nextLine();
                    System.out.println("Enter your surname");
                    String clientSurname = scanner.nextLine();
                    Client client = new Client(clientName, clientSurname);
                    clients.add(client);
                    System.out.println("Registration OK");
                    break;

                }
                case 2: {
                    System.out.println("Enter your name");
                    String specialistName = scanner.nextLine();
                    System.out.println("Enter your surname");
                    String specialistSurname = scanner.nextLine();
                    System.out.println("Enter your profession");
                    String specialistProfession = scanner.nextLine();
                    Specialist specialist = new Specialist(specialistName, specialistSurname, specialistProfession);
                    specialists.add(specialist);
                    System.out.println("Registration OK");
                    break;
                }
                case 3: {
                    System.out.println("Enter profession");
                    for (Specialist specialist : specialists) {
                        if (specialist.getProfession().equals(scanner.nextLine()))
                            System.out.println(specialist);
                        else System.out.println("Specialists not found");
                    }
                    break;
                }

                case 4: {
                    System.out.println("Specialists list: ");
                    for (Specialist specialist : specialists) {
                        System.out.println(specialist);
                    }

                    break;
                }
                case 5: {
                    System.out.println("See you later, by!");
                    System.exit(0);


                }
            }
        }
    }
}
