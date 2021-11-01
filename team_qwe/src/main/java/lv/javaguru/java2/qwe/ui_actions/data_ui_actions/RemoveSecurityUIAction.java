package lv.javaguru.java2.qwe.ui_actions.data_ui_actions;

import lv.javaguru.java2.qwe.core.requests.data_requests.RemoveSecurityRequest;
import lv.javaguru.java2.qwe.core.responses.data_responses.RemoveSecurityResponse;
import lv.javaguru.java2.qwe.core.services.data_services.RemoveSecurityService;
import lv.javaguru.java2.qwe.ui_actions.UIAction;
import lv.javaguru.java2.qwe.utils.UtilityMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RemoveSecurityUIAction implements UIAction {

    @Autowired private RemoveSecurityService removeSecurityService;
    @Autowired private UtilityMethods utils;

    @Override
    public void execute() {
        RemoveSecurityRequest request =
                new RemoveSecurityRequest(utils.inputDialog("Enter ticker:"));
        RemoveSecurityResponse response =
                removeSecurityService.execute(request);
        String info = response.isRemoved() ? "Security " + request.getTicker() + " has been removed!" :
                "No security with such ticker!";
        utils.messageDialog(info);
    }

}