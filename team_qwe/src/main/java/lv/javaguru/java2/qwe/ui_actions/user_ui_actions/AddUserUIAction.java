package lv.javaguru.java2.qwe.ui_actions.user_ui_actions;

import lv.javaguru.java2.qwe.services.user_services.AddUserService;
import lv.javaguru.java2.qwe.ui_actions.UIAction;

public class AddUserUIAction implements UIAction {

    private final AddUserService addUserService;

    public AddUserUIAction(AddUserService addUserService) {
        this.addUserService = addUserService;
    }

    @Override
    public void execute() {
        addUserService.getUserData().addUser();
    }

}