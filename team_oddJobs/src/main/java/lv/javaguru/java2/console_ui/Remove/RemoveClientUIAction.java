package lv.javaguru.java2.console_ui.Remove;


import lv.javaguru.java2.console_ui.UIAction;
import lv.javaguru.java2.core.requests.Remove.RemoveClientRequest;
import lv.javaguru.java2.core.responce.Remove.RemoveClientResponse;
import lv.javaguru.java2.services.Remove.RemoveClientService;

import java.util.Scanner;

public class RemoveClientUIAction implements UIAction {
    private RemoveClientService deleteClientService;

    public RemoveClientUIAction(RemoveClientService deleteClientService) {
        this.deleteClientService = deleteClientService;
    }

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

        RemoveClientRequest request = new RemoveClientRequest(clientName,clientSurname,clientId);
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
