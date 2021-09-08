package lv.javaguru.java2.console_ui.Add;

import lv.javaguru.java2.console_ui.UIAction;
import lv.javaguru.java2.core.requests.Add.AddClientRequest;
import lv.javaguru.java2.core.responce.Add.AddClientResponse;
import lv.javaguru.java2.services.Add.AddClientService;

import java.util.Scanner;

public class AddClientUIAction implements UIAction {

    private AddClientService addClientService;


    public AddClientUIAction(AddClientService addClientService) {
        this.addClientService = addClientService;
    }


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
