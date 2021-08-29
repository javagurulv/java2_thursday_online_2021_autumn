package lv.javaguru.java2.qwe.ui_actions.data_ui_actions;

import lv.javaguru.java2.qwe.database.Database;
import lv.javaguru.java2.qwe.ui_actions.UIAction;

import static lv.javaguru.java2.qwe.utils.UtilityMethods.inputDialog;

public class RemoveSecurityUIAction implements UIAction {

    private final Database database;

    public RemoveSecurityUIAction(Database database) {
        this.database = database;
    }

    @Override
    public void execute() {
        database.removeSecurity(inputDialog("Enter name:"));
    }

}