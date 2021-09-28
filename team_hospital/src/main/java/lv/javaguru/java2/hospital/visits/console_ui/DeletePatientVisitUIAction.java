package lv.javaguru.java2.hospital.visits.console_ui;

import lv.javaguru.java2.hospital.visits.core.request.DeletePatientVisitRequest;
import lv.javaguru.java2.hospital.visits.core.responses.DeletePatientVisitResponse;
import lv.javaguru.java2.hospital.visits.core.services.DeletePatientVisitService;

public class DeletePatientVisitUIAction {
    private final DeletePatientVisitService visitService;

    public DeletePatientVisitUIAction(DeletePatientVisitService visitService) {
        this.visitService = visitService;
    }

    public void execute() {
        GetUserInput getUserInput = new GetUserInput();
        Long ID = getUserInput.getUserLongInput("Please enter visit ID to delete: ");
        DeletePatientVisitRequest request = new DeletePatientVisitRequest(ID);
        DeletePatientVisitResponse response = visitService.execute(request);

        if (response.isTrue()) {
            System.out.println("Visit is deleted!");
        } else {
            System.out.println("Visit not found!");
        }
    }
}
