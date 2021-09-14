package lv.javaguru.java2.console_ui.Find;

import lv.javaguru.java2.console_ui.UIAction;
import lv.javaguru.java2.services.Find.FindAdvertisementByIdService;

import java.util.Scanner;

public class FindAdvertisementByIdUIAction implements UIAction {

    private FindAdvertisementByIdService findAdvertisementByIdService;

    public FindAdvertisementByIdUIAction(FindAdvertisementByIdService findAdvertisementByIdService) {
        this.findAdvertisementByIdService = findAdvertisementByIdService;
    }

    @Override
    public void execute() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter advertisement ID: ");
        long advId = in.nextLong();

        findAdvertisementByIdService.execute(advId);
        System.out.println();
    }
}
