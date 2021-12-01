package lv.javaguru.java2.oddJobs.console_ui.remove;

import lv.javaguru.java2.oddJobs.console_ui.UIAction;
import lv.javaguru.java2.oddJobs.core.requests.remove.RemoveAdvertismentRequest;
import lv.javaguru.java2.oddJobs.core.responce.remove.RemoveAdvertisementResponse;
import lv.javaguru.java2.oddJobs.core.services.remove.RemoveAdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class RemoveAdvertismentUIAction implements UIAction {

    @Autowired
    private RemoveAdvertisementService deleteAdvertismentService;


    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Advertisment ID:");
        long advertismentId = Long.parseLong(scanner.nextLine());

        System.out.println("Enter advertisment title (case sensitive).");
        String advertismentTitle = scanner.nextLine();


        RemoveAdvertismentRequest request = new RemoveAdvertismentRequest(advertismentTitle, advertismentId);
        RemoveAdvertisementResponse removeAdvertisementResponse = deleteAdvertismentService.execute(request);

        if (removeAdvertisementResponse.hasErrors()) {
            removeAdvertisementResponse.getErrors().forEach(coreError ->
                    System.out.println("Error" + coreError.getField() + " " + coreError.getMessage()));
        } else {
            if (removeAdvertisementResponse.isAdvertisementRemoved()) {
                System.out.println("Your advertisement was deleted from board.");
            } else {
                System.out.println("Your advertisement was not deleted from board.");
            }
        }

    }
}
