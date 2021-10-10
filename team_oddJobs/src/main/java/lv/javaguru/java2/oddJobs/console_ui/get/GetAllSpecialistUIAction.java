package lv.javaguru.java2.oddJobs.console_ui.get;

import lv.javaguru.java2.oddJobs.core.requests.get.GetAllSpecialistRequest;
import lv.javaguru.java2.oddJobs.core.responce.get.GetAllSpecialistsResponse;
import lv.javaguru.java2.oddJobs.core.services.get.GetAllSpecialistsService;
import lv.javaguru.java2.oddJobs.console_ui.UIAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetAllSpecialistUIAction implements UIAction {

    @Autowired
    private GetAllSpecialistsService getAllSpecialistsService;


    @Override
    public void execute() {
        System.out.println("Specialists list: ");
        GetAllSpecialistRequest request = new GetAllSpecialistRequest();
        GetAllSpecialistsResponse response = getAllSpecialistsService.execute(request);
        response.getSpecialists().forEach(System.out::println);
        System.out.println("Specialist list end.");

    }
}

