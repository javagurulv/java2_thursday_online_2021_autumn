package lv.javaguru.java2.oddJobs.console_ui;

import lv.javaguru.java2.oddJobs.services.AddClientService;

import java.util.Scanner;

public class AddClientUIAction implements UIAction {
    private AddClientService addClientService;
    public AddClientUIAction(AddClientService addClientService){this.addClientService=addClientService;}


    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your name");
        String clientName = scanner.nextLine();
        System.out.println("Enter your surname");
        String clientSurname = scanner.nextLine();
        addClientService.execute(clientName,clientSurname);
        System.out.println("Registration OK");

    }
}
