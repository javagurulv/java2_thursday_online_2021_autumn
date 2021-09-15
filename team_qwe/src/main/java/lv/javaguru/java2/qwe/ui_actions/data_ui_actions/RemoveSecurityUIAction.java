package lv.javaguru.java2.qwe.ui_actions.data_ui_actions;

import lv.javaguru.java2.qwe.core.requests.data_requests.RemoveSecurityRequest;
import lv.javaguru.java2.qwe.core.responses.data_responses.RemoveSecurityResponse;
import lv.javaguru.java2.qwe.core.services.data_services.RemoveSecurityService;
import lv.javaguru.java2.qwe.ui_actions.UIAction;

import static lv.javaguru.java2.qwe.utils.UtilityMethods.*;

public class RemoveSecurityUIAction implements UIAction {

    private final RemoveSecurityService removeSecurityService;

    public RemoveSecurityUIAction(RemoveSecurityService removeSecurityService) {
        this.removeSecurityService = removeSecurityService;
    }

    @Override
    public void execute() {
        RemoveSecurityRequest request =
                new RemoveSecurityRequest(inputDialog("Enter name:"));
        RemoveSecurityResponse response =
                removeSecurityService.execute(request);
        String info = response.isRemoved() ? "Security " + request.getName() + " has been removed!" :
                "No security with such name!";
        messageDialog(info);
    }

}