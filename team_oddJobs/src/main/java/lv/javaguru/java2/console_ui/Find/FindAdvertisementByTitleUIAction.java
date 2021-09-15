package lv.javaguru.java2.console_ui.Find;

import lv.javaguru.java2.Advertisement;
import lv.javaguru.java2.Specialist;
import lv.javaguru.java2.console_ui.UIAction;
import lv.javaguru.java2.core.requests.Find.FindAdvertisementByTitleRequest;
import lv.javaguru.java2.core.responce.Find.FindAdvertisementByTitleResponse;
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
        System.out.println("Enter advertisement title: ");
        String advTitle = scanner.nextLine();

        FindAdvertisementByTitleRequest request = new FindAdvertisementByTitleRequest(advTitle);
        FindAdvertisementByTitleResponse response = findAdvertisementByTitleService.execute(request);

        if (response.hasErrors()) {
            response.getErrors().forEach(coreError ->
                    System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage()));
        } else {
            System.out.println(response.getAdvertisements());
        }

    }
}
