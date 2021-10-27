package lv.javaguru.java2.hospital.prescription.console_ui;

import lv.javaguru.java2.hospital.prescription.core.requests.ShowAllPrescriptionRequest;
import lv.javaguru.java2.hospital.prescription.core.responses.ShowAllPrescriptionResponse;
import lv.javaguru.java2.hospital.prescription.core.services.ShowAllPrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShowAllPrescriptionUIAction implements PrescriptionUIAction{

    @Autowired private ShowAllPrescriptionService service;

    @Override
    public void execute() {
        System.out.println("Prescription list: ");
        ShowAllPrescriptionRequest request = new ShowAllPrescriptionRequest();
        ShowAllPrescriptionResponse response = service.execute(request);
        System.out.println(response.getPrescriptions());
        System.out.println("Prescription list end.");
    }
}
