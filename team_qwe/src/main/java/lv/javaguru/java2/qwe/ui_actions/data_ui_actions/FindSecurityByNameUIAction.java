package lv.javaguru.java2.qwe.ui_actions.data_ui_actions;

import lv.javaguru.java2.qwe.core.requests.data_requests.FindSecurityByTickerOrNameRequest;
import lv.javaguru.java2.qwe.core.responses.data_responses.FindSecurityByTickerOrNameResponse;
import lv.javaguru.java2.qwe.core.services.data_services.FindSecurityByTickerOrNameService;
import lv.javaguru.java2.qwe.ui_actions.UIAction;
import lv.javaguru.java2.qwe.core.utils.UtilityMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FindSecurityByNameUIAction implements UIAction {

    @Autowired private FindSecurityByTickerOrNameService findSecurityByNameService;
    @Autowired private UtilityMethods utils;

    @Override
    public void execute() {
        FindSecurityByTickerOrNameRequest request =
                new FindSecurityByTickerOrNameRequest(utils.inputDialog("Enter name:"));
        FindSecurityByTickerOrNameResponse response =
                findSecurityByNameService.execute(request);
        printResponse(response);
    }

    private void printResponse(FindSecurityByTickerOrNameResponse response) {
        if (response.getSecurity() == null && !response.hasErrors()) {
            utils.messageDialog("There is no security with such name!");
        }
        else if (response.hasErrors()) {
            utils.messageDialog("WRONG INPUT!\n" +
                    utils.printErrorList(response));
        }
        else {
            utils.messageDialog(response.getSecurity().toString());
            System.out.println(response.getSecurity() + "\n");
        }
    }

}