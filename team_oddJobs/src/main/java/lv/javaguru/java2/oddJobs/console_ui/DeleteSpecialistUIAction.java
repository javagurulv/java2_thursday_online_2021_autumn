package lv.javaguru.java2.oddJobs.console_ui;


import lv.javaguru.java2.oddJobs.services.DeleteSpecialistService;

import java.util.Scanner;

public class DeleteSpecialistUIAction implements UIAction {
    private DeleteSpecialistService deleteSpecialistService;

    public DeleteSpecialistUIAction(DeleteSpecialistService deleteSpecialistService){this.deleteSpecialistService=deleteSpecialistService;}
    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter specialist ID");
        Long specialistId = Long.parseLong(scanner.nextLine());
        deleteSpecialistService.execute(specialistId);
        System.out.println("Account successful deleted");
    }
}
