package lv.javaguru.java2.console_ui.Find;

import lv.javaguru.java2.console_ui.UIAction;
import lv.javaguru.java2.services.Find.FindClientBySurnameService;

import java.util.Scanner;

public class FindClientBySurnameUIAction implements UIAction {

    FindClientBySurnameService findClientBySurnameService;

    public FindClientBySurnameUIAction(FindClientBySurnameService findClientBySurnameService) {
        this.findClientBySurnameService = findClientBySurnameService;
    }

    @Override
    public void execute() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter surname");
        String surName = scanner.nextLine();
        findClientBySurnameService.execute(surName);

    }
}
