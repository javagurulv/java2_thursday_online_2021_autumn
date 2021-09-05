package lv.javaguru.java2.console_ui.Find;

import lv.javaguru.java2.console_ui.UIAction;
import lv.javaguru.java2.services.Find.FindClientByIdService;

import java.util.Scanner;

public class FindClientByIdUIAction implements UIAction {

    private FindClientByIdService findByIdSearchCriteriaService;

    public FindClientByIdUIAction(FindClientByIdService findByIdSearchCriteriaService) {
        this.findByIdSearchCriteriaService = findByIdSearchCriteriaService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter ID");
        Long id = scanner.nextLong();

        findByIdSearchCriteriaService.execute(id);
        System.out.println();
    }
}
