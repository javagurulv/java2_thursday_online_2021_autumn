package lv.javaguru.java2.qwe.ui_actions.data_ui_actions;

import lv.javaguru.java2.qwe.services.data_services.ShowListService;
import lv.javaguru.java2.qwe.ui_actions.UIAction;

public class ShowListUIAction implements UIAction {

    private final ShowListService showListService;

    public ShowListUIAction(ShowListService showListService) {
        this.showListService = showListService;
    }

    @Override
    public void execute() {
        showListService.execute(showListService.getDatabase().getSecurityList());
    }

}