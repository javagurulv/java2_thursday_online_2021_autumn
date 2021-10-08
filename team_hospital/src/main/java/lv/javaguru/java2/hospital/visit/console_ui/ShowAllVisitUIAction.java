package lv.javaguru.java2.hospital.visit.console_ui;

import lv.javaguru.java2.hospital.visit.core.requests.ShowAllVisitRequest;
import lv.javaguru.java2.hospital.visit.core.responses.ShowAllVisitResponse;
import lv.javaguru.java2.hospital.visit.core.services.ShowAllVisitService;

public class ShowAllVisitUIAction implements VisitUIAction {

    private ShowAllVisitService showAllPatientVisit;

    public ShowAllVisitUIAction(ShowAllVisitService showAllPatientVisit) {
        this.showAllPatientVisit = showAllPatientVisit;
    }

    @Override
    public void execute() {
        System.out.println("Patient visits list: ");
        ShowAllVisitRequest request = new ShowAllVisitRequest();
        ShowAllVisitResponse response = showAllPatientVisit.execute(request);
        System.out.println(response.getPatientVisits());
        System.out.println("Patient visits list end.");
    }
}