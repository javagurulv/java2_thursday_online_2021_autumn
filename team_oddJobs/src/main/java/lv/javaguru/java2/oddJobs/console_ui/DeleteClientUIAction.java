package lv.javaguru.java2.oddJobs.console_ui;

import lv.javaguru.java2.oddJobs.services.DeleteClientService;

import java.util.Scanner;

public class DeleteClientUIAction implements UIAction {
    private DeleteClientService deleteClientService;

    public DeleteClientUIAction(DeleteClientService deleteClientService) {
        this.deleteClientService = deleteClientService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter client ID");
        scanner.nextLine();
        Long clientId = Long.parseLong(scanner.nextLine());
        deleteClientService.execute(clientId);
        System.out.println("Account successful deleted");

    }


}
