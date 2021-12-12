package lv.javaguru.java2.oddJobs.console_ui.remove;


import lv.javaguru.java2.oddJobs.core.requests.remove.RemoveClientRequest;
import lv.javaguru.java2.oddJobs.core.response.remove.RemoveClientResponse;
import lv.javaguru.java2.oddJobs.core.services.remove.RemoveClientService;
import lv.javaguru.java2.oddJobs.console_ui.UIAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class RemoveClientUIAction implements UIAction {
    @Autowired
    private RemoveClientService deleteClientService;

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter client ID.");
        long clientId = Long.parseLong(scanner.nextLine());

        System.out.println("Enter client name (case sensitive).");
        String clientName = scanner.nextLine();

        System.out.println("Enter client surname (case sensitive).");
        String clientSurname = scanner.nextLine();

//        deleteClientService.execute(clientId);

        RemoveClientRequest request = new RemoveClientRequest(clientName, clientSurname, clientId);
        RemoveClientResponse removeClientResponse = deleteClientService.execute(request);

        if (removeClientResponse.hasErrors()) {
            removeClientResponse.getErrors().forEach(coreError ->
                    System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage())
            );
        } else {
            if (removeClientResponse.isClientRemoved()) {
                System.out.println("Your client account was deleted from list.");
            } else {
                System.out.println("Your client account was not deleted from list.");
            }
        }


        //System.out.println("Client deletion process" + removeClientResponse.toString());

    }


}
