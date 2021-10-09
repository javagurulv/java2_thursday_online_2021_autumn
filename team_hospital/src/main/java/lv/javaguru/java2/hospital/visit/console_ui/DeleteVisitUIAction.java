package lv.javaguru.java2.hospital.visit.console_ui;

import lv.javaguru.java2.hospital.visit.core.requests.DeleteVisitRequest;
import lv.javaguru.java2.hospital.visit.core.responses.DeleteVisitResponse;
import lv.javaguru.java2.hospital.visit.core.services.DeleteVisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteVisitUIAction {

    @Autowired
    private DeleteVisitService visitService;

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
