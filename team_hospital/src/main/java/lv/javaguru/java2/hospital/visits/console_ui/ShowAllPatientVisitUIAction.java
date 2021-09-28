package lv.javaguru.java2.hospital.visits.console_ui;

import lv.javaguru.java2.hospital.visits.core.request.ShowAllPatientVisitRequest;
import lv.javaguru.java2.hospital.visits.core.responses.ShowAllPatientVisitResponse;
import lv.javaguru.java2.hospital.visits.core.services.ShowAllPatientVisitService;

public class ShowAllPatientVisitUIAction implements PatientVisitUIAction {

    private ShowAllPatientVisitService showAllPatientVisit;

    public ShowAllPatientVisitUIAction(ShowAllPatientVisitService showAllPatientVisit) {
        this.showAllPatientVisit = showAllPatientVisit;
    }

    @Override
    public void execute() {
        System.out.println("Patient visits list: ");
        ShowAllPatientVisitRequest request = new ShowAllPatientVisitRequest();
        ShowAllPatientVisitResponse response = showAllPatientVisit.execute(request);
        System.out.println(response.getPatientVisits());
        System.out.println("Patient visits list end.");
    }
}
