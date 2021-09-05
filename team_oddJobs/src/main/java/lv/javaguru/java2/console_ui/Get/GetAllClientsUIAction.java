package lv.javaguru.java2.console_ui.Get;

import lv.javaguru.java2.Client;
import lv.javaguru.java2.console_ui.UIAction;
import lv.javaguru.java2.services.Get.GetAllClientsService;

public class GetAllClientsUIAction implements UIAction {


    private GetAllClientsService getAllClientsService;

    public GetAllClientsUIAction(GetAllClientsService getAllClientsService) {
        this.getAllClientsService = getAllClientsService;
    }

    @Override
    public void execute() {
        System.out.println("Clients list");

        for (Client client : getAllClientsService.execute()) {
            System.out.println(client);
        }
    }
}
