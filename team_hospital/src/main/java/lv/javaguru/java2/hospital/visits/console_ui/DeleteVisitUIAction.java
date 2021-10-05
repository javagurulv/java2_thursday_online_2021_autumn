package lv.javaguru.java2.hospital.visits.console_ui;

import lv.javaguru.java2.hospital.visits.core.request.DeleteVisitRequest;
import lv.javaguru.java2.hospital.visits.core.responses.DeleteVisitResponse;
import lv.javaguru.java2.hospital.visits.core.services.DeleteVisitService;

public class DeleteVisitUIAction {
    private final DeleteVisitService visitService;

    public DeleteVisitUIAction(DeleteVisitService visitService) {
        this.visitService = visitService;
    }

    public void execute() {
        GetUserInput getUserInput = new GetUserInput();
        Long ID = getUserInput.getUserLongInput("Please enter visit ID to delete: ");
        DeleteVisitRequest request = new DeleteVisitRequest(ID);
        DeleteVisitResponse response = visitService.execute(request);

        if (response.isTrue()) {
            System.out.println("Visit is deleted!");
        } else {
            System.out.println("Visit not found!");
        }
    }
}
