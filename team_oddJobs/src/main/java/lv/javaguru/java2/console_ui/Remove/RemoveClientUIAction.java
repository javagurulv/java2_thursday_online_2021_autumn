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

        RemoveClientRequest request = new RemoveClientRequest(clientId, clientName, clientSurname);

        RemoveClientResponse removeClientResponse = deleteClientService.execute(request);


        System.out.println("Client deletion process" + removeClientResponse.toString());

    }


}
