package lv.javaguru.java2.oddJobs.console_ui.find;

import lv.javaguru.java2.oddJobs.core.requests.find.FindAdvertisementByTitleRequest;
import lv.javaguru.java2.oddJobs.core.responce.find.FindAdvertisementByTitleResponse;
import lv.javaguru.java2.oddJobs.core.services.find.FindAdvertisementByTitleService;
import lv.javaguru.java2.oddJobs.console_ui.UIAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class FindAdvertisementByTitleUIAction implements UIAction {

    @Autowired
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
