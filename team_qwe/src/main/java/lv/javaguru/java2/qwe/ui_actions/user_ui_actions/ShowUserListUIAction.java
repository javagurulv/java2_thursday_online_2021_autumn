package lv.javaguru.java2.qwe.ui_actions.user_ui_actions;

import lv.javaguru.java2.qwe.core.requests.user_requests.ShowUserListRequest;
import lv.javaguru.java2.qwe.core.responses.user_responses.ShowUserListResponse;
import lv.javaguru.java2.qwe.core.services.user_services.ShowUserListService;
import lv.javaguru.java2.qwe.ui_actions.UIAction;

public class ShowUserListUIAction implements UIAction {

    private final ShowUserListService showUserListService;

    public ShowUserListUIAction(ShowUserListService showUserListService) {
        this.showUserListService = showUserListService;
    }

    @Override
    public void execute() {
        ShowUserListRequest request = new ShowUserListRequest();
        ShowUserListResponse response = showUserListService.execute(request);
        System.out.println("LIST STARTS:");
        response.getList().forEach(System.out::println);
        System.out.println("LIST ENDS.");
    }

}