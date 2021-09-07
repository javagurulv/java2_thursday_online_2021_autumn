package lv.javaguru.java2.console_ui.Find;

import lv.javaguru.java2.console_ui.UIAction;
import lv.javaguru.java2.services.Find.FindAdvertisementByTitleService;

import java.util.Scanner;

public class FindAdvertisementByTitleUIAction implements UIAction {

    private FindAdvertisementByTitleService findAdvertisementByTitleService;

    public FindAdvertisementByTitleUIAction(FindAdvertisementByTitleService findAdvertisementByTitleService) {
        this.findAdvertisementByTitleService = findAdvertisementByTitleService;
    }


    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter advertisment title: ");
        String advTitle = scanner.nextLine();

        findAdvertisementByTitleService.execute(advTitle);
        System.out.println();
    }
}
