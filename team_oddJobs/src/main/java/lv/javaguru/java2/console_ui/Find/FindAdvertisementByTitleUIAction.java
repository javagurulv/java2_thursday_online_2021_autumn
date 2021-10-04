package lv.javaguru.java2.console_ui.Find;

import lv.javaguru.java2.console_ui.UIAction;
import lv.javaguru.java2.core.requests.Find.FindAdvertisementByTitleRequest;
import lv.javaguru.java2.core.responce.Find.FindAdvertisementByTitleResponse;
import lv.javaguru.java2.dependency_injection.DIComponent;
import lv.javaguru.java2.dependency_injection.DIDependency;
import lv.javaguru.java2.services.Find.FindAdvertisementByTitleService;

import java.util.Scanner;

@DIComponent
public class FindAdvertisementByTitleUIAction implements UIAction {

    @DIDependency
    private FindAdvertisementByTitleService findAdvertisementByTitleService;

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter advertisment title: ");
        String advTitle = scanner.nextLine();

        FindAdvertisementByTitleRequest request = new FindAdvertisementByTitleRequest(advTitle);
        FindAdvertisementByTitleResponse response = findAdvertisementByTitleService.execute(request);
    }
}
