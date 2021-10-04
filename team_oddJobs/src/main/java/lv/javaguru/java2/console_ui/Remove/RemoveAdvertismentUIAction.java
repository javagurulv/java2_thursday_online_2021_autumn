package lv.javaguru.java2.console_ui.Remove;

import lv.javaguru.java2.console_ui.UIAction;
import lv.javaguru.java2.core.requests.Remove.RemoveAdvertismentRequest;
import lv.javaguru.java2.core.responce.Remove.RemoveAdvertismentResponse;
import lv.javaguru.java2.dependency_injection.DIComponent;
import lv.javaguru.java2.dependency_injection.DIDependency;
import lv.javaguru.java2.services.Remove.RemoveAdvertismentService;

import java.util.Scanner;

@DIComponent
public class RemoveAdvertismentUIAction implements UIAction {

    @DIDependency
    private RemoveAdvertismentService deleteAdvertismentService;


    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Advertisment ID:");
        long advertismentId = Long.parseLong(scanner.nextLine());

        System.out.println("Enter advertisment title (case sensitive).");
        String advertismentTitle = scanner.nextLine();


        RemoveAdvertismentRequest request = new RemoveAdvertismentRequest(advertismentTitle, advertismentId);
        RemoveAdvertismentResponse removeAdvertismentResponse = deleteAdvertismentService.execute(request);

        if (removeAdvertismentResponse.hasErrors()) {
            removeAdvertismentResponse.getErrors().forEach(coreError ->
                    System.out.println("Error" + coreError.getField() + " " + coreError.getMessage()));
        } else {
            if (removeAdvertismentResponse.isAdvertismentRemoved()) {
                System.out.println("Your advertisement was deleted from board.");
            } else {
                System.out.println("Your advertisement was not deleted from board.");
            }
        }

    }
}
