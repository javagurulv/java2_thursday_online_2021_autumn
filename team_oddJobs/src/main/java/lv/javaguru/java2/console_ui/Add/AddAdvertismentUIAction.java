package lv.javaguru.java2.console_ui.Add;

import lv.javaguru.java2.console_ui.UIAction;
import lv.javaguru.java2.core.requests.Add.AddAdvertismentRequest;
import lv.javaguru.java2.core.responce.Add.AddAdvertismentResponce;
import lv.javaguru.java2.dependency_injection.DIComponent;
import lv.javaguru.java2.dependency_injection.DIDependency;
import lv.javaguru.java2.services.Add.AddAdvertismentService;

import java.util.Scanner;

@DIComponent
public class AddAdvertismentUIAction implements UIAction {

    @DIDependency
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
