package lv.javaguru.java2.qwe.ui_actions.data_ui_actions;

import lv.javaguru.java2.qwe.services.data_services.AddSecurityService;
import lv.javaguru.java2.qwe.ui_actions.UIAction;

import static lv.javaguru.java2.qwe.utils.UtilityMethods.inputDialog;

public class AddSecurityUIAction implements UIAction {

    private final AddSecurityService addSecurityService;

    public AddSecurityUIAction(AddSecurityService addSecurityService) {
        this.addSecurityService = addSecurityService;
    }

    @Override
    public void execute() {
        addSecurityService.execute(inputDialog(
                "Choose security type:",
                "ADD SECURITY",
                new String[]{"Stock", "Bond"}
        ));
    }

}