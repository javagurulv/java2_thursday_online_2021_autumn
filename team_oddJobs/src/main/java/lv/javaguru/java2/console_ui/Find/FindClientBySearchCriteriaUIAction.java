package lv.javaguru.java2.console_ui.Find;

import lv.javaguru.java2.console_ui.Exit.ExitMenuUIAction;
import lv.javaguru.java2.console_ui.UIAction;
import lv.javaguru.java2.services.Find.FindClientByIdService;
import lv.javaguru.java2.services.Find.FindClientByNameService;
import lv.javaguru.java2.services.Find.FindClientBySurnameService;

import java.util.Scanner;

public class FindClientBySearchCriteriaUIAction implements UIAction {


private FindClientByIdService findClientByIdService;
private FindClientByNameService findClientByNameService;
private FindClientBySurnameService findClientBySurnameService;
private ExitMenuUIAction exitUIAction;

    public FindClientBySearchCriteriaUIAction(FindClientByIdService findClientByIdService, FindClientByNameService findClientByNameService, FindClientBySurnameService findClientBySurnameService) {
        this.findClientByIdService = findClientByIdService;
        this.findClientByNameService = findClientByNameService;
        this.findClientBySurnameService = findClientBySurnameService;
    }

    @Override
    public void execute() {

        while (true) {
            printSubMenu();
            int menuNumber = GetUserChoice();
            RunSelectedMenuNumber(menuNumber);
        }

    }

    public void printSubMenu() {

        System.out.println("Please select a search criteria:");
        System.out.println("1. Find client by Id");
        System.out.println("2. Find client by Name");
        System.out.println("3. Find client by Surname");
        System.out.println("4. Exit menu");

    }

    private int GetUserChoice() {
        System.out.println("Enter a menu number to continued");
        Scanner scanner = new Scanner(System.in);
        return Integer.parseInt(scanner.nextLine());
    }

    private void RunSelectedMenuNumber(int selectedMenu) {
        Scanner scanner = new Scanner(System.in);


        switch (selectedMenu) {
            case 1: {
                System.out.println("Provide ID to search");
                long userInput = scanner.nextLong();
                findClientByIdService.execute(userInput);
                break;

            }
            case 2: {
                System.out.println("Provide name to search");
                String userInput = scanner.nextLine();
                findClientByNameService.execute(userInput);
                break;
            }
            case 3: {
                System.out.println("Provide surname to search");
                String userInput = scanner.nextLine();
                findClientBySurnameService.execute(userInput);
                break;
            }

            case 4: {
                exitUIAction.execute();

            }

        }
    }
}
