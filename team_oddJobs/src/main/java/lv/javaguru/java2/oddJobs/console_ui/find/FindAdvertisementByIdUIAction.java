package lv.javaguru.java2.oddJobs.console_ui.find;

import lv.javaguru.java2.oddJobs.console_ui.UIAction;
import lv.javaguru.java2.oddJobs.core.requests.find.FindAdvertisementByIdRequest;
import lv.javaguru.java2.oddJobs.core.responce.find.FindAdvertisementByIdResponse;
import lv.javaguru.java2.oddJobs.core.services.find.FindAdvertisementByIdService;
import lv.javaguru.java2.oddJobs.domain.Advertisement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class FindAdvertisementByIdUIAction implements UIAction {

    @Autowired
    private FindAdvertisementByIdService findAdvertisementByIdService;


    @Override
    public void execute() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter advertisement ID: ");
        long advId = in.nextLong();

        /*findAdvertisementByIdService.execute(advId);
        System.out.println();*/

        FindAdvertisementByIdRequest request = new FindAdvertisementByIdRequest(advId);
        FindAdvertisementByIdResponse response = findAdvertisementByIdService.execute(request);

        if (response.hasErrors()) {
            response.getErrors().forEach(coreError ->
                    System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage()));
        } else {
            response.getFoundAdvertisements().forEach(Advertisement::toString);
        }
        findAdvertisementByIdService.execute(advId);
        System.out.println();
    }
}
