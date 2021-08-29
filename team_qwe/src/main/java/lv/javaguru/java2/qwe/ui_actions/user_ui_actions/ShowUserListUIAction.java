package lv.javaguru.java2.qwe.ui_actions.user_ui_actions;

import lv.javaguru.java2.qwe.database.UserData;
import lv.javaguru.java2.qwe.ui_actions.UIAction;

public class ShowUserListUIAction implements UIAction {

    private final UserData userData;

    public ShowUserListUIAction(UserData userData) {
        this.userData = userData;
    }

    @Override
    public void execute() {
        userData.showListOfUsers(userData.getUserList());
    }

}