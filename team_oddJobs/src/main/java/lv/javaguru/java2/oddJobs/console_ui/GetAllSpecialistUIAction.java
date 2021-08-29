package lv.javaguru.java2.oddJobs.console_ui;

import lv.javaguru.java2.oddJobs.Specialist;
import lv.javaguru.java2.oddJobs.services.GetAllSpecialistsService;

public class GetAllSpecialistUIAction implements UIAction {
    private GetAllSpecialistsService getAllSpecialistsService;

    public GetAllSpecialistUIAction(GetAllSpecialistsService getAllSpecialistsService) {
        this.getAllSpecialistsService = getAllSpecialistsService;
    }

    @Override
    public void execute() {
        System.out.println("Specialists list: ");
        for (Specialist specialist : getAllSpecialistsService.execute()) {
            System.out.println(specialist);
        }
    }
}
