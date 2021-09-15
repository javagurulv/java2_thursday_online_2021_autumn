package lv.javaguru.java2.qwe.ui_actions.data_ui_actions;

import lv.javaguru.java2.qwe.core.requests.data_requests.GetAllSecurityListRequest;
import lv.javaguru.java2.qwe.core.responses.data_responses.GetAllSecurityListResponse;
import lv.javaguru.java2.qwe.core.services.data_services.GetAllSecurityListService;
import lv.javaguru.java2.qwe.ui_actions.UIAction;

public class GetAllSecurityListUIAction implements UIAction {

    private final GetAllSecurityListService showListService;

    public GetAllSecurityListUIAction(GetAllSecurityListService showListService) {
        this.showListService = showListService;
    }

    @Override
    public void execute() {
        GetAllSecurityListRequest request = new GetAllSecurityListRequest();
        GetAllSecurityListResponse response = showListService.execute(request);
        System.out.println("LIST STARTS:");
        response.getList().forEach(System.out::println);
        System.out.println("LIST ENDS.");
    }

}