package lv.javaguru.java2.oddJobs;

import lv.javaguru.java2.oddJobs.console_ui.*;
import lv.javaguru.java2.oddJobs.database.Database;
import lv.javaguru.java2.oddJobs.database.InMemoryDatabaseImpl;
import lv.javaguru.java2.oddJobs.services.*;

import java.util.Scanner;

public class Application {
    private static Database database = new InMemoryDatabaseImpl();
    private static AddClientService addClientService = new AddClientService(database);
    private static UIAction addClient = new AddClientUIAction(addClientService);
    private static AddSpecialistService addSpecialistService = new AddSpecialistService(database);
    private static UIAction addSpecialist = new AddSpecialistUIAction(addSpecialistService);
    private static DeleteClientService deleteClientService = new DeleteClientService(database);
    private static UIAction deleteClient = new DeleteClientUIAction(deleteClientService);
    private static DeleteSpecialistService deleteSpecialistService = new DeleteSpecialistService(database);
    private static UIAction deleteSpecialist = new DeleteSpecialistUIAction(deleteSpecialistService);
    private static FindSpecialistByProfessionService findSpecialistByProfessionService = new FindSpecialistByProfessionService(database);
    private static UIAction findSpecialistByProfession = new FindSpecialistByProfessionUIAction(findSpecialistByProfessionService);
    private static GetAllSpecialistsService getAllSpecialistsService = new GetAllSpecialistsService(database);
    private static UIAction getAllSpecialists = new GetAllSpecialistUIAction(getAllSpecialistsService);
    private static UIAction menuExit = new ExitUIAction();


    public static void main(String[] args) {

        while (true) {
            printProgramMenu();
            int menuNumber = GetUserChoice();
            RunSelectedMenuNumber(menuNumber);
        }
    }


    private static int GetUserChoice() {
        System.out.println("Enter a menu number to continued");
        Scanner scanner = new Scanner(System.in);
        return Integer.parseInt(scanner.nextLine());
    }

    private static void printProgramMenu() {
        System.out.println("Menu:");
        System.out.println("1. Create client account");
        System.out.println("2. Create specialist account");
        System.out.println("3. Find specialist by profession");
        System.out.println("4. Show all specialist");
        System.out.println("5. Delete client account");
        System.out.println("6. Delete specialist account");
        System.out.println("7. Exit");
    }


    private static void RunSelectedMenuNumber(int selectedMenu) {
        switch (selectedMenu) {
            case 1: {
                addClient.execute();
                break;

            }
            case 2: {
                addSpecialist.execute();
                break;
            }
            case 3: {
                findSpecialistByProfession.execute();
                break;
            }

            case 4: {
                getAllSpecialists.execute();

                break;
            }
            case 5: {
                deleteClient.execute();

                break;
            }
            case 6: {
                deleteSpecialist.execute();

                break;
            }
            case 7: {
                menuExit.execute();


            }
        }
    }
}
