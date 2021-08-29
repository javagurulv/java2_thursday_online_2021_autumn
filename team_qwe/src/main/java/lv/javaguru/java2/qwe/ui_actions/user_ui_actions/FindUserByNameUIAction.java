package lv.javaguru.java2.qwe.ui_actions.user_ui_actions;

import lv.javaguru.java2.qwe.UserData;
import lv.javaguru.java2.qwe.ui_actions.UIAction;

import static lv.javaguru.java2.qwe.utils.UtilityMethods.inputDialog;

public class FindUserByNameUIAction implements UIAction {

    private final UserData userData;

    public FindUserByNameUIAction(UserData userData) {
        this.userData = userData;
    }

    @Override
    public void execute() {
        System.out.println(userData.findUserByName(inputDialog("Enter name:")));
    }

}