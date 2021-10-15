package lv.javaguru.java2.hospital.patient.console_ui;

import lv.javaguru.java2.hospital.patient.core.requests.PatientOrdering;
import lv.javaguru.java2.hospital.patient.core.requests.PatientPaging;
import lv.javaguru.java2.hospital.patient.core.requests.SearchPatientsRequest;
import lv.javaguru.java2.hospital.patient.core.responses.SearchPatientsResponse;
import lv.javaguru.java2.hospital.patient.core.services.search_patient_service.SearchPatientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class SearchPatientsUIAction implements PatientUIActions {

    @Autowired private SearchPatientsService searchPatientsService;

    @Override
    public void execute() {
        GetUserInput getUserInput = new GetUserInput();
        String name = getUserInput.getUserStringInput("Enter patient name: ");
        String surname = getUserInput.getUserStringInput("Enter patient surname: ");
        String personalCode = getUserInput.getUserStringInput("Enter patient personal code: ");

        PatientOrdering patientOrdering = getOrdering();
        PatientPaging patientPaging = getPaging();

        SearchPatientsRequest request =
                new SearchPatientsRequest(name, surname, personalCode, patientOrdering, patientPaging);

        SearchPatientsResponse response = searchPatientsService.execute(request);

        if (response.hasErrors()) {
            response.getErrors().forEach(coreError
                    -> System.out.println("Error: " + coreError.getField() + " " + coreError.getDescription()));
        } else {
            System.out.println(response.getPatientList());
        }
    }

    private PatientOrdering getOrdering() {
        GetUserInput getUserInput = new GetUserInput();
        String answer = getUserInput.getUserStringInput("Do you want to order list? "
                + "Enter 'yes' or 'no': ").toLowerCase(Locale.ROOT);

        PatientOrdering patientOrdering = null;

        if (answer.equals("yes")) {
            String orderBy = getUserInput.getUserStringInput("Enter orderBy (Name or Surname): ");
            String orderDirection = getUserInput.getUserStringInput("Enter orderDirection (Ascending||Descending): ");
            patientOrdering = new PatientOrdering(orderBy, orderDirection);
        }
        return patientOrdering;
    }

    private PatientPaging getPaging() {
        GetUserInput getUserInput = new GetUserInput();
        String answer = getUserInput.getUserStringInput("Do want to see list in pages? " +
                "Enter 'yes' or 'no': ").toLowerCase(Locale.ROOT);

        PatientPaging patientPaging = null;

        if (answer.equals("yes")) {
            int pageNumber = getUserInput.getUserNumericInput("Enter pageNumber: ");
            int pageSize = getUserInput.getUserNumericInput("Enter pageSize: ");
            patientPaging = new PatientPaging(pageNumber, pageSize);
        }
        return patientPaging;
    }
}
