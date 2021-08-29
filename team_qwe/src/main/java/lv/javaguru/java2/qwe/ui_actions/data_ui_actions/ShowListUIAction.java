package lv.javaguru.java2.qwe.ui_actions.data_ui_actions;

import lv.javaguru.java2.qwe.database.Database;
import lv.javaguru.java2.qwe.ui_actions.UIAction;

public class ShowListUIAction implements UIAction {

    private final Database database;

    public ShowListUIAction(Database database) {
        this.database = database;
    }

    @Override
    public void execute() {
        database.showListOfSecurities(database.getSecurityList());
    }

}