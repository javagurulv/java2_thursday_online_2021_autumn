package lv.javaguru.java2.qwe.ui_actions.user_ui_actions;

import lv.javaguru.java2.qwe.core.services.user_services.ShowUserListService;
import lv.javaguru.java2.qwe.ui_actions.UIAction;

public class ShowUserListUIAction implements UIAction {

    private final ShowUserListService showUserListService;

    public ShowUserListUIAction(ShowUserListService showUserListService) {
        this.showUserListService = showUserListService;
    }

    @Override
    public void execute() {
        showUserListService.getUserData().showListOfUsers(showUserListService.getUserData().getUserList());
    }

}