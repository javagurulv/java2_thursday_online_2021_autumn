package lv.javaguru.java2.oddJobs.console_ui.get;

import lv.javaguru.java2.oddJobs.core.services.get.GetAllClientsService;
import lv.javaguru.java2.oddJobs.domain.Client;
import lv.javaguru.java2.oddJobs.console_ui.UIAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetAllClientsUIAction implements UIAction {


    @Autowired
    private GetAllClientsService getAllClientsService;


    @Override
    public void execute() {
        System.out.println("Clients list");

        for (Client client : getAllClientsService.execute()) {
            System.out.println(client);
        }
    }
}
