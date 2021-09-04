package lv.javaguru.java2.qwe.ui_actions.user_ui_actions;

import lv.javaguru.java2.qwe.request.AddUserRequest;
import lv.javaguru.java2.qwe.services.user_services.AddUserService;
import lv.javaguru.java2.qwe.ui_actions.UIAction;

import static lv.javaguru.java2.qwe.Type.*;
import static lv.javaguru.java2.qwe.Type.SUPER_RICH;
import static lv.javaguru.java2.qwe.utils.UtilityMethods.inputDialog;

public class AddUserUIAction implements UIAction {

    private final AddUserService addUserService;

    public AddUserUIAction(AddUserService addUserService) {
        this.addUserService = addUserService;
    }

    @Override
    public void execute() {
        String name = inputDialog("User name:");
        String age = inputDialog("User age:");
        String type = inputDialog("User type:", "TYPE", new String[]{
                String.valueOf(LOWER_MIDDLE),
                String.valueOf(MIDDLE),
                String.valueOf(UPPER_MIDDLE),
                String.valueOf(WEALTHY),
                String.valueOf(SUPER_RICH)
        });
        String initialInvestment = inputDialog("Initial investment:");
        AddUserRequest userRequest = new AddUserRequest(name, age, type, initialInvestment);
        addUserService.execute(userRequest);
    }

}