package lv.javaguru.java2.oddJobs.console_ui.add;

import lv.javaguru.java2.oddJobs.core.requests.add.AddClientRequest;
import lv.javaguru.java2.oddJobs.core.responce.add.AddClientResponse;
import lv.javaguru.java2.oddJobs.core.services.add.AddClientService;
import lv.javaguru.java2.oddJobs.console_ui.UIAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class AddClientUIAction implements UIAction {

    @Autowired
    private AddClientService addClientService;


    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your name");
        String clientName = scanner.nextLine();

        System.out.println("Enter your surname");
        String clientSurname = scanner.nextLine();


        AddClientRequest addClientRequest = new AddClientRequest(clientName, clientSurname);
        AddClientResponse addClientResponse = addClientService.execute(addClientRequest);

        if (addClientResponse.hasErrors()) {
            addClientResponse.getErrors().forEach(coreError ->
                    System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage())
            );
        } else {
            System.out.println("New client id was: " + addClientResponse.getClient().getClientId());
            System.out.println("Your client account was added to list.");
        }
    }
}
