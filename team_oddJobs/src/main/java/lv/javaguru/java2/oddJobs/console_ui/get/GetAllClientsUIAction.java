package lv.javaguru.java2.oddJobs.console_ui.get;

import lv.javaguru.java2.oddJobs.core.requests.get.GetAllClientsRequest;
import lv.javaguru.java2.oddJobs.core.response.get.GetAllClientsResponse;
import lv.javaguru.java2.oddJobs.core.services.get.GetAllClientsService;
import lv.javaguru.java2.oddJobs.console_ui.UIAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetAllClientsUIAction implements UIAction {


    @Autowired
    private GetAllClientsService getAllClientsService;


    @Override
    public void execute() {
        System.out.println("Clients list: ");
        GetAllClientsRequest request = new GetAllClientsRequest();
        GetAllClientsResponse response = getAllClientsService.execute(request);
        response.getClients().forEach(System.out::println);
        System.out.println("Client list end.");

    }
}
