package lv.javaguru.java2.console_ui.Get;

import lv.javaguru.java2.Client;
import lv.javaguru.java2.console_ui.UIAction;
import lv.javaguru.java2.dependency_injection.DIComponent;
import lv.javaguru.java2.dependency_injection.DIDependency;
import lv.javaguru.java2.services.Get.GetAllClientsService;

@DIComponent
public class GetAllClientsUIAction implements UIAction {


    @DIDependency
    private GetAllClientsService getAllClientsService;


    @Override
    public void execute() {
        System.out.println("Clients list");

        for (Client client : getAllClientsService.execute()) {
            System.out.println(client);
        }
    }
}
