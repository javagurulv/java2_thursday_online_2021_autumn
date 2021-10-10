package lv.javaguru.java2.oddJobs.console_ui.add;

import lv.javaguru.java2.oddJobs.core.requests.add.AddAdvertismentRequest;
import lv.javaguru.java2.oddJobs.core.responce.add.AddAdvertismentResponce;
import lv.javaguru.java2.oddJobs.core.services.add.AddAdvertismentService;
import lv.javaguru.java2.oddJobs.console_ui.UIAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class AddAdvertismentUIAction implements UIAction {

    @Autowired
    private AddAdvertismentService addAdvertismentService;


    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter advertisement title");
        String advTitle = scanner.nextLine();
        System.out.println("Enter advertisement description");
        String advDescription = scanner.nextLine();

        AddAdvertismentRequest addAdvertismentRequest = new AddAdvertismentRequest(advTitle, advDescription);
        AddAdvertismentResponce addAdvertismentResponce = addAdvertismentService.execute(addAdvertismentRequest);

        System.out.println("New advertisement id is " + addAdvertismentResponce.getAdvertisement().getAdvId());
        System.out.println("Advertisement added!");
    }
}
