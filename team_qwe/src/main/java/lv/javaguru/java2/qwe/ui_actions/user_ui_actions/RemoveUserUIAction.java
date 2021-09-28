package lv.javaguru.java2.qwe.ui_actions.user_ui_actions;

import lv.javaguru.java2.qwe.core.requests.user_requests.RemoveUserRequest;
import lv.javaguru.java2.qwe.core.responses.user_responses.RemoveUserResponse;
import lv.javaguru.java2.qwe.core.services.user_services.RemoveUserService;
import lv.javaguru.java2.qwe.dependency_injection.DIComponent;
import lv.javaguru.java2.qwe.dependency_injection.DIDependency;
import lv.javaguru.java2.qwe.ui_actions.UIAction;

import static lv.javaguru.java2.qwe.utils.UtilityMethods.inputDialog;
import static lv.javaguru.java2.qwe.utils.UtilityMethods.messageDialog;

@DIComponent
public class RemoveUserUIAction implements UIAction {

    @DIDependency private RemoveUserService removeUserService;

    @Override
    public void execute() {
        RemoveUserRequest request = new RemoveUserRequest(inputDialog("Enter name"));
        RemoveUserResponse response =
                removeUserService.execute(request);
        String info = response.isRemoved() ? "User " + request.getName() + " has been removed!" :
                "No user with such name!";
        messageDialog(info);
    }

}