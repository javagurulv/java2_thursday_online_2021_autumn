package lv.javaguru.java2.qwe.ui_actions.user_ui_actions;

import lv.javaguru.java2.qwe.UserData;
import lv.javaguru.java2.qwe.ui_actions.UIAction;

import static lv.javaguru.java2.qwe.utils.UtilityMethods.inputDialog;

public class RemoveUserUIAction implements UIAction {

    private final UserData userData;

    public RemoveUserUIAction(UserData userData) {
        this.userData = userData;
    }

    @Override
    public void execute() {
        userData.removeUser(inputDialog("Enter name:"));
    }

}