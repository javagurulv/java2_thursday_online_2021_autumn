package lv.javaguru.java2.qwe.ui_actions.user_ui_actions;

import lv.javaguru.java2.qwe.services.user_services.FindUserByNameService;
import lv.javaguru.java2.qwe.ui_actions.UIAction;

import static lv.javaguru.java2.qwe.utils.UtilityMethods.inputDialog;

public class FindUserByNameUIAction implements UIAction {

    private final FindUserByNameService findUserByNameService;

    public FindUserByNameUIAction(FindUserByNameService findUserByNameService) {
        this.findUserByNameService = findUserByNameService;
    }

    @Override
    public void execute() {
        System.out.println(findUserByNameService.getUserData().findUserByName(inputDialog("Enter name:")));
    }

}