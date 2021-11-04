package lv.javaguru.java2.hospital.visit.console_ui;

import lv.javaguru.java2.hospital.visit.core.requests.SearchVisitRequest;
import lv.javaguru.java2.hospital.visit.core.requests.VisitOrdering;
import lv.javaguru.java2.hospital.visit.core.requests.VisitPaging;
import lv.javaguru.java2.hospital.visit.core.responses.SearchVisitResponse;
import lv.javaguru.java2.hospital.visit.core.services.search_visit_service.SearchVisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class SearchVisitUIAction implements VisitUIAction {

    @Autowired
    private SearchVisitService searchVisitService;

    @Override
    public void execute() {
        GetUserInput getUserInput = new GetUserInput();
        String visitId = getUserInput.getUserStringInput("Please enter visit id: ");
        String doctorId = getUserInput.getUserStringInput("Please enter doctor id: ");
        String patientId = getUserInput.getUserStringInput("Please enter patient id: ");
        String dateInput = getUserInput.getUserStringInput("Please enter visit date in format dd-MM-yyyy HH:mm: ");

        VisitOrdering visitOrdering = getOrdering();
        VisitPaging visitPaging = visitPaging();

        SearchVisitRequest request = new SearchVisitRequest(visitId, doctorId, patientId, dateInput, visitOrdering, visitPaging);
        SearchVisitResponse response = searchVisitService.execute(request);

        if (response.hasErrors()) {
            response.getErrors().forEach(coreError -> System.out.println("Error: " + coreError.getField() + " " + coreError.getDescription()));
        } else {
            System.out.println(response.getVisits());
        }
    }

    private VisitOrdering getOrdering() {
        GetUserInput getUserInput = new GetUserInput();
        String answer = getUserInput.getUserStringInput("Do you want to order list? "
                + "Enter 'yes' or 'no': ").toLowerCase(Locale.ROOT);

        VisitOrdering visitOrdering = null;

        if (answer.equals("yes")) {
            String orderBy = getUserInput.getUserStringInput("Enter orderBy (ID or Date): ");
            String orderDirection = getUserInput.getUserStringInput("Enter orderDirection (Ascending||Descending): ");
            visitOrdering = new VisitOrdering(orderBy, orderDirection);
        }
        return visitOrdering;
    }

    private VisitPaging visitPaging() {
        GetUserInput getUserInput = new GetUserInput();
        String answer = getUserInput.getUserStringInput("Do want to see list in pages? " +
                "Enter 'yes' or 'no': ").toLowerCase(Locale.ROOT);

        VisitPaging visitPaging = null;

        if (answer.equals("yes")) {
            int pageNumber = getUserInput.getUserNumericInput("Enter pageNumber: ");
            int pageSize = getUserInput.getUserNumericInput("Enter pageSize: ");
            visitPaging = new VisitPaging(pageNumber, pageSize);
        }
        return visitPaging;
    }
}
