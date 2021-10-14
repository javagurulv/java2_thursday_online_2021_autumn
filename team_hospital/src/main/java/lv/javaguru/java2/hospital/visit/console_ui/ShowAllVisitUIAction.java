package lv.javaguru.java2.hospital.visit.console_ui;

import lv.javaguru.java2.hospital.visit.core.requests.ShowAllVisitRequest;
import lv.javaguru.java2.hospital.visit.core.responses.ShowAllVisitResponse;
import lv.javaguru.java2.hospital.visit.core.services.ShowAllVisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShowAllVisitUIAction implements VisitUIAction {

    @Autowired private ShowAllVisitService showAllPatientVisit;

    @Override
    public void execute() {
        System.out.println("Patient visits list: ");
        ShowAllVisitRequest request = new ShowAllVisitRequest();
        ShowAllVisitResponse response = showAllPatientVisit.execute(request);
        System.out.println(response.getPatientVisits());
        System.out.println("Patient visits list end.");
    }
}
