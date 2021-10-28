package lv.javaguru.java2.hospital.prescription.console_ui;

import lv.javaguru.java2.hospital.prescription.core.requests.SearchPrescriptionRequest;
import lv.javaguru.java2.hospital.prescription.core.services.SearchPrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SearchPrescriptionUIAction implements PrescriptionUIAction{

    @Autowired private SearchPrescriptionService service;

    @Override
    public void execute() {
        GetUserInput getUserInput = new GetUserInput();
        Long prescriptionId = getUserInput.getUserLongInput("Enter prescription id: ");
        Long doctorId = getUserInput.getUserLongInput("Enter doctor id: ");
        Long patientId = getUserInput.getUserLongInput("Enter patient id: ");

        SearchPrescriptionRequest request = new SearchPrescriptionRequest(prescriptionId, doctorId, patientId);


    }
}
