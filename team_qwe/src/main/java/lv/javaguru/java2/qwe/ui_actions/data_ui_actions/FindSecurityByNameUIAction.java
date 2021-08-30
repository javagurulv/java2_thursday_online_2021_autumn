package lv.javaguru.java2.qwe.ui_actions.data_ui_actions;

import lv.javaguru.java2.qwe.services.data_services.FindSecurityByNameService;
import lv.javaguru.java2.qwe.ui_actions.UIAction;

import static lv.javaguru.java2.qwe.utils.UtilityMethods.inputDialog;

public class FindSecurityByNameUIAction implements UIAction {

    private final FindSecurityByNameService findSecurityByNameService;

    public FindSecurityByNameUIAction(FindSecurityByNameService findSecurityByNameService) {
        this.findSecurityByNameService = findSecurityByNameService;
    }

    @Override
    public void execute() {
        System.out.println(findSecurityByNameService.execute(
                inputDialog("Enter name:")
        ) + "\n");
    }

}