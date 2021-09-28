package lv.javaguru.java2.qwe.ui_actions.user_ui_actions;

import lv.javaguru.java2.qwe.core.requests.user_requests.GetAllUserListRequest;
import lv.javaguru.java2.qwe.core.responses.user_responses.GetAllUserListResponse;
import lv.javaguru.java2.qwe.core.services.user_services.GetAllUserListService;
import lv.javaguru.java2.qwe.dependency_injection.DIComponent;
import lv.javaguru.java2.qwe.dependency_injection.DIDependency;
import lv.javaguru.java2.qwe.ui_actions.UIAction;

@DIComponent
public class GetUserListUIAction implements UIAction {

    @DIDependency private GetAllUserListService showUserListService;

    @Override
    public void execute() {
        GetAllUserListRequest request = new GetAllUserListRequest();
        GetAllUserListResponse response = showUserListService.execute(request);
        System.out.println("LIST STARTS:");
        response.getList().forEach(System.out::println);
        System.out.println("LIST ENDS.");
    }

}