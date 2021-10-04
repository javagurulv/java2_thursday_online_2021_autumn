package lv.javaguru.java2.hospital.patient.console_ui;

import lv.javaguru.java2.hospital.dependency_injection.DIComponent;
import lv.javaguru.java2.hospital.dependency_injection.DIDependency;
import lv.javaguru.java2.hospital.patient.core.requests.Ordering;
import lv.javaguru.java2.hospital.patient.core.requests.Paging;
import lv.javaguru.java2.hospital.patient.core.requests.SearchPatientsRequest;
import lv.javaguru.java2.hospital.patient.core.responses.SearchPatientsResponse;
import lv.javaguru.java2.hospital.patient.core.services.SearchPatientsService;
import java.util.Locale;

@DIComponent
public class SearchPatientsUIAction implements PatientUIActions {

    @DIDependency private SearchPatientsService searchPatientsService;

    @Override
    public void execute() {
        GetUserInput getUserInput = new GetUserInput();
        String name = getUserInput.getUserStringInput("Enter patient name: ");
        String surname = getUserInput.getUserStringInput("Enter patient surname: ");
        String personalCode = getUserInput.getUserStringInput("Enter patient personal code: ");

        Ordering ordering = getOrdering();
        Paging paging = getPaging();

        SearchPatientsRequest request =
                new SearchPatientsRequest(name, surname, personalCode, ordering, paging);

        SearchPatientsResponse response = searchPatientsService.execute(request);

        if (response.hasErrors()) {
            response.getErrors().forEach(coreError
                    -> System.out.println("Error: " + coreError.getField() + " " + coreError.getDescription()));
        } else {
            System.out.println(response.getPatientList());
        }
    }

    private Ordering getOrdering() {
        GetUserInput getUserInput = new GetUserInput();
        String answer = getUserInput.getUserStringInput("Do you want to order list? "
                + "Enter 'yes' or 'no': ").toLowerCase(Locale.ROOT);

        Ordering ordering = null;

        if (answer.equals("yes")) {
            String orderBy = getUserInput.getUserStringInput("Enter orderBy (Name or Surname): ");
            String orderDirection = getUserInput.getUserStringInput("Enter orderDirection (Ascending||Descending): ");
            ordering = new Ordering(orderBy, orderDirection);
        }
        return ordering;
    }

    private Paging getPaging() {
        GetUserInput getUserInput = new GetUserInput();
        String answer = getUserInput.getUserStringInput("Do want to see list in pages? " +
                "Enter 'yes' or 'no': ").toLowerCase(Locale.ROOT);

        Paging paging = null;

        if (answer.equals("yes")) {
            int pageNumber = getUserInput.getUserNumericInput("Enter pageNumber: ");
            int pageSize = getUserInput.getUserNumericInput("Enter pageSize: ");
            paging = new Paging(pageNumber, pageSize);
        }
        return paging;
    }
}
