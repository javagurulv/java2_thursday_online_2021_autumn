package lv.javaguru.java2.console_ui.Find;

import lv.javaguru.java2.Advertisement;
import lv.javaguru.java2.Specialist;
import lv.javaguru.java2.console_ui.UIAction;
import lv.javaguru.java2.core.requests.Find.FindAdvertisementByIdRequest;
import lv.javaguru.java2.core.responce.Find.FindAdvertisementByIdResponse;
import lv.javaguru.java2.console_ui.UIAction;
import lv.javaguru.java2.dependency_injection.DIComponent;
import lv.javaguru.java2.dependency_injection.DIDependency;
import lv.javaguru.java2.services.Find.FindAdvertisementByIdService;

import java.util.Scanner;

@DIComponent
public class FindAdvertisementByIdUIAction implements UIAction {

    @DIDependency
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
