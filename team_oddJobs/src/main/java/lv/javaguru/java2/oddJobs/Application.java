package lv.javaguru.java2.oddJobs;


import lv.javaguru.java2.oddJobs.config.ApplicationConfiguration;
import lv.javaguru.java2.oddJobs.console_ui.add.AddAdvertismentUIAction;
import lv.javaguru.java2.oddJobs.console_ui.add.AddClientUIAction;
import lv.javaguru.java2.oddJobs.console_ui.add.AddSpecialistUIAction;
import lv.javaguru.java2.oddJobs.console_ui.exit.ExitMenuUIAction;
import lv.javaguru.java2.oddJobs.console_ui.find.FindAdvertisementByIdUIAction;
import lv.javaguru.java2.oddJobs.console_ui.find.FindAdvertisementByTitleUIAction;
import lv.javaguru.java2.oddJobs.console_ui.find.FindClientsUIAction;
import lv.javaguru.java2.oddJobs.console_ui.find.FindSpecialistUIAction;
import lv.javaguru.java2.oddJobs.console_ui.get.GetAllClientsUIAction;
import lv.javaguru.java2.oddJobs.console_ui.get.GetAllSpecialistUIAction;
import lv.javaguru.java2.oddJobs.console_ui.remove.RemoveClientUIAction;
import lv.javaguru.java2.oddJobs.console_ui.remove.RemoveSpecialistUIAction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


import java.util.Scanner;

public class Application {

    private static ApplicationContext applicationContext =
            new AnnotationConfigApplicationContext(ApplicationConfiguration.class);


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
               AddAdvertismentUIAction uiAction = applicationContext.getBean(AddAdvertismentUIAction.class);
                uiAction.execute();
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
                FindAdvertisementByTitleUIAction uiAction = applicationContext.getBean(FindAdvertisementByTitleUIAction.class);
                uiAction.execute();
                break;
            }

            case 7: {
                FindAdvertisementByIdUIAction uiAction = applicationContext.getBean(FindAdvertisementByIdUIAction.class);
                uiAction.execute();
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
