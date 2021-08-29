package lv.javaguru.java2.qwe.ui_actions.data_ui_actions;

import lv.javaguru.java2.qwe.database.Database;
import lv.javaguru.java2.qwe.ui_actions.UIAction;

import static lv.javaguru.java2.qwe.utils.UtilityMethods.inputDialog;

public class AddSecurityUIAction implements UIAction {

    private final Database database;

    public AddSecurityUIAction(Database database) {
        this.database = database;
    }

    @Override
    public void execute() {
        database.addSecurity(inputDialog(
                "Choose security type:",
                "ADD SECURITY",
                new String[]{"Stock", "Bond"}
        ));
    }

}