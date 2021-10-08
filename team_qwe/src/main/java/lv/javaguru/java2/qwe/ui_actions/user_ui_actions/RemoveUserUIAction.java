package lv.javaguru.java2.qwe.ui_actions.user_ui_actions;

import lv.javaguru.java2.qwe.core.requests.user_requests.RemoveUserRequest;
import lv.javaguru.java2.qwe.core.responses.user_responses.RemoveUserResponse;
import lv.javaguru.java2.qwe.core.services.user_services.RemoveUserService;
import lv.javaguru.java2.qwe.ui_actions.UIAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static lv.javaguru.java2.qwe.utils.UtilityMethods.*;

@Component
public class RemoveUserUIAction implements UIAction {

    @Autowired private RemoveUserService removeUserService;

    @Override
    public void execute() {
        RemoveUserRequest request = new RemoveUserRequest(inputDialog("Enter name"));
        RemoveUserResponse response =
                removeUserService.execute(request);
        printResponse(request, response);
    }

    private void printResponse(RemoveUserRequest request, RemoveUserResponse response) {
        if (response.hasErrors()) {
            messageDialog("FAILED TO REMOVE USER!\n" +
                    printErrorList(response));
        } else {
            messageDialog("User " + request.getName() + " has been removed!");
        }
    }

}