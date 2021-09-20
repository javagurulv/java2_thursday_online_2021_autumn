package lv.javaguru.java2;


import lv.javaguru.java2.console_ui.Add.AddAdvertismentUIAction;
import lv.javaguru.java2.console_ui.Add.AddClientUIAction;
import lv.javaguru.java2.console_ui.Add.AddSpecialistUIAction;
import lv.javaguru.java2.console_ui.Exit.ExitMenuUIAction;
import lv.javaguru.java2.console_ui.Find.*;
import lv.javaguru.java2.console_ui.Get.GetAllClientsUIAction;
import lv.javaguru.java2.console_ui.Get.GetAllSpecialistUIAction;
import lv.javaguru.java2.console_ui.Remove.RemoveAdvertismentUIAction;
import lv.javaguru.java2.console_ui.Remove.RemoveClientUIAction;
import lv.javaguru.java2.console_ui.Remove.RemoveSpecialistUIAction;
import lv.javaguru.java2.console_ui.UIAction;
import lv.javaguru.java2.core.validations.*;
import lv.javaguru.java2.database.Database;
import lv.javaguru.java2.database.InMemoryDatabaseImpl;
import lv.javaguru.java2.services.Add.AddAdvertismentService;
import lv.javaguru.java2.services.Find.*;
import lv.javaguru.java2.services.Remove.RemoveAdvertismentService;


import java.util.Scanner;

public class Application {

    private static ApplicationContext applicationContext = new ApplicationContext();
    private static Database database = new InMemoryDatabaseImpl();

    private static AddAdvertismentService addAdvertisementService = new AddAdvertismentService(database);
    private static UIAction addAdvertisement = new AddAdvertismentUIAction(addAdvertisementService);

    private static RemoveAdvertismentValidator removeAdvertismentValidator = new RemoveAdvertismentValidator();
    private static RemoveAdvertismentService deleteAdvertismentService = new RemoveAdvertismentService(database, removeAdvertismentValidator);
    private static UIAction deleteAdvertisment = new RemoveAdvertismentUIAction(deleteAdvertismentService);

    private static FindAdvertisementByTitleValidator findAdvertisementByTitleValidator = new FindAdvertisementByTitleValidator();
    private static FindAdvertisementByTitleService findAdvertisementByTitleService = new FindAdvertisementByTitleService(database, findAdvertisementByTitleValidator);
    private static UIAction findAdvertisementByTitle = new FindAdvertisementByTitleUIAction(findAdvertisementByTitleService);

    private static FindAdvertisementByIdValidator findAdvertisementByIdValidator = new FindAdvertisementByIdValidator();
    private static FindAdvertisementByIdService findAdvertisementByIdService = new FindAdvertisementByIdService(database, findAdvertisementByIdValidator);
    private static UIAction findAdvertisementById = new FindAdvertisementByIdUIAction(findAdvertisementByIdService);


    public static void main(String[] args) {

        while (true) {
            printProgramMenu();
            int menuNumber = GetUserChoice();
            RunSelectedMenuNumber(menuNumber);
        }
    }


    private static int GetUserChoice() {
        System.out.println("Enter a menu number to continue");
        Scanner scanner = new Scanner(System.in);
        return Integer.parseInt(scanner.nextLine());
    }

    private static void printProgramMenu() {
        System.out.println("Menu:");
        System.out.println("1.  Create client account");
        System.out.println("2.  Create specialist account");
        System.out.println("3.  Create advertisement");
        System.out.println("4.  Find specialist by search criteria");
        System.out.println("5.  Find client by search criteria");
        System.out.println("6.  Find advertisement by title");
        System.out.println("7.  Find advertisement by ID");
        System.out.println("8.  Show all clients");
        System.out.println("9.  Show all specialists");
        System.out.println("10. Delete client account");
        System.out.println("11. Delete specialist account");
        System.out.println("12. Exit");
    }


    private static void RunSelectedMenuNumber(int selectedMenu) {
        switch (selectedMenu) {
            case 1: {
                AddClientUIAction uiAction = applicationContext.getBean(AddClientUIAction.class);
                uiAction.execute();
                break;

            }
            case 2: {
                AddSpecialistUIAction uiAction = applicationContext.getBean(AddSpecialistUIAction.class);
                uiAction.execute();
                break;
            }

            case 3: {
                addAdvertisement.execute();

                break;
            }

            case 4: {
                FindSpecialistUIAction uiAction = applicationContext.getBean(FindSpecialistUIAction.class);
                uiAction.execute();
                break;
            }

            case 5: {
                FindClientsUIAction uiAction = applicationContext.getBean(FindClientsUIAction.class);
                uiAction.execute();
                break;
            }

            case 6: {
                findAdvertisementByTitle.execute();

                break;
            }

            case 7: {
                findAdvertisementById.execute();

                break;
            }

            case 8: {
                GetAllClientsUIAction uiAction = applicationContext.getBean(GetAllClientsUIAction.class);
                uiAction.execute();
                break;
            }

            case 9: {
                GetAllSpecialistUIAction uiAction = applicationContext.getBean(GetAllSpecialistUIAction.class);
                uiAction.execute();
                break;
            }
            case 10: {
                RemoveClientUIAction uiAction = applicationContext.getBean(RemoveClientUIAction.class);
                uiAction.execute();
                break;
            }
            case 11: {
                RemoveSpecialistUIAction uiAction = applicationContext.getBean((RemoveSpecialistUIAction.class));
                uiAction.execute();
                break;
            }
            case 12: {
                ExitMenuUIAction uiAction = applicationContext.getBean(ExitMenuUIAction.class);
                uiAction.execute();


            }
        }
    }
}
