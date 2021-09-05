package lv.javaguru.java2.console_ui.Find;

import lv.javaguru.java2.console_ui.UIAction;
import lv.javaguru.java2.services.Find.FindClientByNameService;

import java.util.Scanner;

public class FindClientByNameUIAction implements UIAction {

    private FindClientByNameService findClientByNameService;

    public FindClientByNameUIAction(FindClientByNameService findClientByNameService) {
        this.findClientByNameService = findClientByNameService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter name");
        String name = scanner.nextLine();
        findClientByNameService.execute(name);
    }
}
