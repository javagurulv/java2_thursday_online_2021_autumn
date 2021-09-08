package lv.javaguru.java2.qwe.ui_actions.data_ui_actions;

import lv.javaguru.java2.qwe.core.services.data_services.RemoveSecurityService;
import lv.javaguru.java2.qwe.ui_actions.UIAction;

import static lv.javaguru.java2.qwe.utils.UtilityMethods.inputDialog;

public class RemoveSecurityUIAction implements UIAction {

    private final RemoveSecurityService removeSecurityService;

    public RemoveSecurityUIAction(RemoveSecurityService removeSecurityService) {
        this.removeSecurityService = removeSecurityService;
    }

    @Override
    public void execute() {
        removeSecurityService.execute(inputDialog("Enter name:"));
    }

}