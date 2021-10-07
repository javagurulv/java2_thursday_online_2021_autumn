package lv.javaguru.java2.hospital.visit.console_ui;

import lv.javaguru.java2.hospital.visit.core.requests.Ordering;
import lv.javaguru.java2.hospital.visit.core.requests.Paging;
import lv.javaguru.java2.hospital.visit.core.requests.SearchVisitRequest;
import lv.javaguru.java2.hospital.visit.core.responses.SearchVisitResponse;
import lv.javaguru.java2.hospital.visit.core.services.SearchVisitService;

public class SearchVisitUIAction implements VisitUIAction {

    private SearchVisitService searchVisitService;

    public SearchVisitUIAction(SearchVisitService searchVisitService) {
        this.searchVisitService = searchVisitService;
    }

    @Override
    public void execute() {
        GetUserInput getUserInput = new GetUserInput();
        Long visitId = getUserInput.getUserLongInput("Please enter visit id: ");
        Long doctorId = getUserInput.getUserLongInput("Please enter doctor id: ");
        Long patientId = getUserInput.getUserLongInput("Please enter patient id: ");
        String dateInput = getUserInput.getUserStringInput("Please enter visit date in format dd/MM/yyyy HH:mm: ");

        String orderBy = getUserInput.getUserStringInput("Enter orderBy (name||surname||speciality): ");
        String orderDirection = getUserInput.getUserStringInput("Enter orderDirection (ASCENDING||DESCENDING): ");
        Ordering ordering = new Ordering(orderBy, orderDirection);

        Integer pageNumber = getUserInput.getUserNumericInput("Enter pageNumber: ");
        Integer pageSize = getUserInput.getUserNumericInput("Enter pageSize: ");
        Paging paging = new Paging(pageNumber, pageSize);

        SearchVisitRequest request = new SearchVisitRequest(visitId, doctorId, patientId, dateInput, ordering, paging);
        SearchVisitResponse response = searchVisitService.execute(request);

        if (response.hasErrors()) {
            response.getErrors().forEach(coreError -> System.out.println("Error: " + coreError.getField() + " " + coreError.getDescription()));
        } else {
            System.out.println(response.getVisits());
        }
    }
}
