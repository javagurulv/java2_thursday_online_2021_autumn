package lv.javaguru.java2.qwe.ui_actions.data_ui_actions;

import lv.javaguru.java2.qwe.core.requests.data_requests.GetAllSecurityListRequest;
import lv.javaguru.java2.qwe.core.responses.data_responses.GetAllSecurityListResponse;
import lv.javaguru.java2.qwe.core.services.data_services.GetAllSecurityListService;
import lv.javaguru.java2.qwe.dependency_injection.DIComponent;
import lv.javaguru.java2.qwe.dependency_injection.DIDependency;
import lv.javaguru.java2.qwe.ui_actions.UIAction;

@DIComponent
public class GetAllSecurityListUIAction implements UIAction {

    @DIDependency private GetAllSecurityListService listService;

    @Override
    public void execute() {
        GetAllSecurityListRequest request = new GetAllSecurityListRequest();
        GetAllSecurityListResponse response = listService.execute(request);
        System.out.println("LIST STARTS:");
        response.getList().forEach(System.out::println);
        System.out.println("LIST ENDS.");
    }

}