package lv.javaguru.java2.qwe.ui_actions.user_ui_actions;

import lv.javaguru.java2.qwe.core.requests.user_requests.FindUserByNameRequest;
import lv.javaguru.java2.qwe.core.responses.user_responses.FindUserByNameResponse;
import lv.javaguru.java2.qwe.core.services.user_services.FindUserByNameService;
import lv.javaguru.java2.qwe.ui_actions.UIAction;
import lv.javaguru.java2.qwe.core.utils.UtilityMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FindUserByNameUIAction implements UIAction {

    @Autowired private FindUserByNameService findUserByNameService;
    @Autowired private UtilityMethods utils;

    @Override
    public void execute() {
        FindUserByNameRequest request = new FindUserByNameRequest(utils.inputDialog("Enter name:"));
        FindUserByNameResponse response = findUserByNameService.execute(request);
        printResponse(response);
    }

    private void printResponse(FindUserByNameResponse response) {
        if (response.getUser() == null && !response.hasErrors()) {
            utils.messageDialog("There is no user with such name!");
        } else if (response.hasErrors()) {
            utils.messageDialog("WRONG INPUT!\n" +
                    utils.printErrorList(response));
        } else {
            utils.messageDialog(response.getUser().toString());
            System.out.println(response.getUser() + "\n");
        }
    }

}