package lv.javaguru.java2.qwe.ui_actions.user_ui_actions;

import lv.javaguru.java2.qwe.core.requests.user_requests.GetUserTradesRequest;
import lv.javaguru.java2.qwe.core.responses.user_responses.GetUserTradesResponse;
import lv.javaguru.java2.qwe.core.services.user_services.GetUserTradesService;
import lv.javaguru.java2.qwe.ui_actions.UIAction;
import lv.javaguru.java2.qwe.core.utils.UtilityMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetUserTradesUIAction implements UIAction {

    @Autowired private UtilityMethods utils;
    @Autowired private GetUserTradesService service;

    @Override
    public void execute() {
        GetUserTradesRequest request = new GetUserTradesRequest(utils.inputDialog(
                "Choose user:",
                "SHOW PORTFOLIO",
                utils.convertToStringArray(service.getUserData())
        ));
        GetUserTradesResponse response = service.execute(request);
        printResponse(response);
    }

    public void printResponse(GetUserTradesResponse response) {
        if (!response.hasErrors()) {
            String userName = response.getUser().getName() + "(ID: " + response.getUser().getId() + ")";
            System.out.println("==============" + userName + "===============");
            response.getTrades().forEach(System.out::println);
            System.out.print("\n");
        } else {
            utils.messageDialog("WRONG INPUT!\n" +
                    utils.printErrorList(response));
        }
    }

}