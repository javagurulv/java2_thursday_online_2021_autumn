package lv.javaguru.java2;


import lv.javaguru.java2.console_ui.Add.AddAdvertismentUIAction;
import lv.javaguru.java2.console_ui.Add.AddClientUIAction;
import lv.javaguru.java2.console_ui.Add.AddSpecialistUIAction;
import lv.javaguru.java2.console_ui.Exit.ExitMenuUIAction;
import lv.javaguru.java2.console_ui.Find.*;
import lv.javaguru.java2.console_ui.Get.GetAllClientsUIAction;
import lv.javaguru.java2.console_ui.Get.GetAllSpecialistUIAction;
import lv.javaguru.java2.console_ui.Remove.RemoveClientUIAction;
import lv.javaguru.java2.console_ui.Remove.RemoveSpecialistUIAction;
import lv.javaguru.java2.console_ui.UIAction;
import lv.javaguru.java2.core.validations.*;
import lv.javaguru.java2.database.Database;
import lv.javaguru.java2.database.InMemoryDatabaseImpl;
import lv.javaguru.java2.services.Add.AddAdvertismentService;
import lv.javaguru.java2.services.Add.AddClientService;
import lv.javaguru.java2.services.Add.AddSpecialistService;
import lv.javaguru.java2.services.Exit.ExitMenuService;
import lv.javaguru.java2.services.Find.*;
import lv.javaguru.java2.services.Get.GetAllClientsService;
import lv.javaguru.java2.services.Get.GetAllSpecialistsService;
import lv.javaguru.java2.services.Remove.RemoveClientService;
import lv.javaguru.java2.services.Remove.RemoveSpecialistService;

import java.util.Scanner;

public class Application {
    private static final Database database = new InMemoryDatabaseImpl();

    private static AddClientValidator addClientValidator = new AddClientValidator();
    private static final AddClientService addClientService = new AddClientService(database,addClientValidator);
    private static final UIAction addClient = new AddClientUIAction(addClientService);

   private static AddSpecialistValidator addSpecialistValidator = new AddSpecialistValidator();
    private static final AddSpecialistService addSpecialistService = new AddSpecialistService(database,addSpecialistValidator);
    private static final UIAction addSpecialist = new AddSpecialistUIAction(addSpecialistService);

    private static final AddAdvertismentService addAdvertisementService = new AddAdvertismentService(database);
    private static final UIAction addAdvertisement = new AddAdvertismentUIAction(addAdvertisementService);

    private static RemoveClientValidator removeClientValidator = new RemoveClientValidator();
    private static final RemoveClientService deleteClientService = new RemoveClientService(database,removeClientValidator);
    private static final UIAction deleteClient = new RemoveClientUIAction(deleteClientService);

    private static RemoveSpecialistValidator removeSpecialistValidator = new RemoveSpecialistValidator();
    private static final RemoveSpecialistService deleteSpecialistService = new RemoveSpecialistService(database,removeSpecialistValidator);
    private static final UIAction deleteSpecialist = new RemoveSpecialistUIAction(deleteSpecialistService);

private static FindSpecialistByProfessionValidator findSpecialistByProfessionValidator = new FindSpecialistByProfessionValidator();
    private static final FindSpecialistByProfessionService findSpecialistByProfessionService = new FindSpecialistByProfessionService(database,findSpecialistByProfessionValidator);
    private static final UIAction findSpecialistByProfession = new FindSpecialistByProfessionUIAction(findSpecialistByProfessionService);

    private static final FindClientByIdService findClientByIdService = new FindClientByIdService(database);
    private static final UIAction findClientByIdUI = new FindClientByIdUIAction(findClientByIdService);

    private static final FindClientByNameService findClientByNameService = new FindClientByNameService(database);
    private static final UIAction findClientByNameUI = new FindClientByNameUIAction(findClientByNameService);

    private static final FindClientBySurnameService findClientBySurname = new FindClientBySurnameService(database);
    private static final UIAction findClientBySurnameUI = new FindClientBySurnameUIAction(findClientBySurname);

    private static final FindClientBySearchCriteriaService findClientBySearchCriteria = new FindClientBySearchCriteriaService(database);
    private static final UIAction findClientBySearch = new FindClientBySearchCriteriaUIAction(findClientByIdService, findClientByNameService, findClientBySurname);


    private static final GetAllSpecialistsService getAllSpecialistsService = new GetAllSpecialistsService(database);
    private static final UIAction getAllSpecialists = new GetAllSpecialistUIAction(getAllSpecialistsService);

    private static final GetAllClientsService getAllClientsService = new GetAllClientsService(database);
    private static final UIAction getAllClients = new GetAllClientsUIAction(getAllClientsService);

    private static final ExitMenuService exitMenuService = new ExitMenuService();
    private static final UIAction menuExit = new ExitMenuUIAction(exitMenuService);

    private static final FindAdvertisementByTitleService findAdvertisementByTitleService = new FindAdvertisementByTitleService(database);
    private static final UIAction findAdvertisementByTitle = new FindAdvertisementByTitleUIAction(findAdvertisementByTitleService);


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
        System.out.println("1.  Create client account");
        System.out.println("2.  Create specialist account");
        System.out.println("3.  Create advertisement");
        System.out.println("4.  Find specialist by profession");
        System.out.println("5.  Find client by search criteria");
        System.out.println("6.  Find advertisement by title");
        System.out.println("7.  Show all clients");
        System.out.println("8.  Show all specialists");
        System.out.println("9.  Delete client account");
        System.out.println("10.  Delete specialist account");
        System.out.println("11. Exit");
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
                addAdvertisement.execute();
                break;
            }
            case 4: {
                findSpecialistByProfession.execute();
                break;
            }

            case 5: {
                findClientBySearch.execute();
                break;
            }

            case 6: {
                findAdvertisementByTitle.execute();

                break;
            }
            case 7: {
                getAllClients.execute();

                break;
            }

            case 8: {
                getAllSpecialists.execute();

                break;
            }
            case 9: {
                deleteClient.execute();

                break;
            }
            case 10: {
                deleteSpecialist.execute();

                break;
            }
            case 11: {
                menuExit.execute();


            }
        }
    }
}
