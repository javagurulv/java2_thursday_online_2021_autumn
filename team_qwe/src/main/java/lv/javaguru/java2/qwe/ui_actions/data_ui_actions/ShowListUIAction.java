package lv.javaguru.java2.qwe.ui_actions.data_ui_actions;

import lv.javaguru.java2.qwe.core.requests.ShowListRequest;
import lv.javaguru.java2.qwe.core.responses.ShowListResponse;
import lv.javaguru.java2.qwe.core.services.data_services.ShowListService;
import lv.javaguru.java2.qwe.ui_actions.UIAction;

public class ShowListUIAction implements UIAction {

    private final ShowListService showListService;

    public ShowListUIAction(ShowListService showListService) {
        this.showListService = showListService;
    }

    @Override
    public void execute() {
        ShowListRequest request = new ShowListRequest();
        ShowListResponse response = showListService.execute(request);
        System.out.println("LIST STARTS:");
        response.getList().forEach(System.out::println);
        System.out.println("LIST ENDS.");
    }

}