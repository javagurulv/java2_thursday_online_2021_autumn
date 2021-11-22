package lv.javaguru.java2.oddJobs.console_ui.add;

import lv.javaguru.java2.oddJobs.core.requests.add.AddAdvertisementRequest;
import lv.javaguru.java2.oddJobs.core.responce.add.AddAdvertisementResponse;
import lv.javaguru.java2.oddJobs.core.services.add.AddAdvertisementService;
import lv.javaguru.java2.oddJobs.console_ui.UIAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class AddAdvertisementUIAction implements UIAction {

    @Autowired
    private AddAdvertisementService addAdvertisementService;


    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter advertisement title");
        String advTitle = scanner.nextLine();
        System.out.println("Enter advertisement description");
        String advDescription = scanner.nextLine();

        AddAdvertisementRequest addAdvertisementRequest = new AddAdvertisementRequest(advTitle, advDescription);
        AddAdvertisementResponse addAdvertisementResponse = addAdvertisementService.execute(addAdvertisementRequest);

        System.out.println("New advertisement id is " + addAdvertisementResponse.getAdvertisement().getAdvId());
        System.out.println("Advertisement added!");
    }
}
