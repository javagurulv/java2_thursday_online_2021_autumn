package lv.javaguru.java2.qwe.ui_actions.data_ui_actions;

import lv.javaguru.java2.qwe.database.Database;
import lv.javaguru.java2.qwe.ui_actions.UIAction;

import static lv.javaguru.java2.qwe.utils.UtilityMethods.inputDialog;

public class FindSecurityByNameUIAction implements UIAction {

    private final Database database;

    public FindSecurityByNameUIAction(Database database) {
        this.database = database;
    }

    @Override
    public void execute() {
        System.out.println(database.findSecurityByName(
                inputDialog("Enter name:")
        ) + "\n");
    }

}