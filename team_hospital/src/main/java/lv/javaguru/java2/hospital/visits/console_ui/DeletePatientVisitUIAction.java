package lv.javaguru.java2.hospital.visits.console_ui;

import lv.javaguru.java2.hospital.visits.core.request.DeletePatientVisitRequest;
import lv.javaguru.java2.hospital.visits.core.responses.DeletePatientVisitResponse;
import lv.javaguru.java2.hospital.visits.core.services.DeletePatientVisitService;

import java.util.Scanner;

public class DeletePatientVisitUIAction {
    private final DeletePatientVisitService visitService;

    public DeletePatientVisitUIAction(DeletePatientVisitService visitService) {
        this.visitService = visitService;
    }

    public void execute() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter visit ID to delete: ");
        String ID = scanner.nextLine();

        DeletePatientVisitRequest request = new DeletePatientVisitRequest(ID);
        DeletePatientVisitResponse response = visitService.execute(request);

        if (response.isTrueOrNot()) {
            System.out.println("Visit is deleted!");
        } else {
            System.out.println("Visit not found!");
        }
    }
}
