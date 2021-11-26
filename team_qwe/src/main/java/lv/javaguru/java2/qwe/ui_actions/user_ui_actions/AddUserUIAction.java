package lv.javaguru.java2.qwe.ui_actions.user_ui_actions;

import lv.javaguru.java2.qwe.core.requests.user_requests.AddUserRequest;
import lv.javaguru.java2.qwe.core.responses.user_responses.AddUserResponse;
import lv.javaguru.java2.qwe.core.services.user_services.AddUserService;
import lv.javaguru.java2.qwe.ui_actions.UIAction;
import lv.javaguru.java2.qwe.core.utils.UtilityMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static lv.javaguru.java2.qwe.core.domain.Type.*;
import static lv.javaguru.java2.qwe.core.domain.Type.SUPER_RICH;

@Component
public class AddUserUIAction implements UIAction {

    @Autowired private AddUserService addUserService;
    @Autowired private UtilityMethods utils;

    @Override
    public void execute() {
        String name = utils.inputDialog("User name:");
        String age = utils.inputDialog("User age:");
        String type = utils.inputDialog("User type:", "TYPE", new String[]{
                String.valueOf(LOWER_MIDDLE),
                String.valueOf(MIDDLE),
                String.valueOf(UPPER_MIDDLE),
                String.valueOf(WEALTHY),
                String.valueOf(SUPER_RICH)
        });
        String initialInvestment = utils.inputDialog("Initial investment:");
        AddUserRequest userRequest = new AddUserRequest(name, age, type, initialInvestment);
        AddUserResponse userResponse = addUserService.execute(userRequest);
        printResponse(userResponse);
    }

    private void printResponse(AddUserResponse response) {
        if (response.hasErrors()) {
            utils.messageDialog("FAILED TO ADD USER!\n" +
                    utils.printErrorList(response));
        } else {
            utils.messageDialog("User " + response.getNewUser().getName() +
                    "(ID: " + response.getNewUser().getId() + ")" + " has been added!");
        }
    }

}