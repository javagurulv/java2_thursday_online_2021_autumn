package lv.javaguru.java2.qwe.ui_actions.data_ui_actions;

import lv.javaguru.java2.qwe.core.requests.data_requests.FindSecurityByNameRequest;
import lv.javaguru.java2.qwe.core.responses.data_responses.FindSecurityByNameResponse;
import lv.javaguru.java2.qwe.core.services.data_services.FindSecurityByNameService;
import lv.javaguru.java2.qwe.ui_actions.UIAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static lv.javaguru.java2.qwe.utils.UtilityMethods.*;

@Component
public class FindSecurityByNameUIAction implements UIAction {

    @Autowired private FindSecurityByNameService findSecurityByNameService;

    @Override
    public void execute() {
        FindSecurityByNameRequest request =
                new FindSecurityByNameRequest(inputDialog("Enter name:"));
        FindSecurityByNameResponse response =
                findSecurityByNameService.execute(request);
        printResponse(response);
    }

    private void printResponse(FindSecurityByNameResponse response) {
        if (response.getSecurity() == null && !response.hasErrors()) {
            messageDialog("There is no security with such name!");
        }
        else if (response.hasErrors()) {
            messageDialog("WRONG INPUT!\n" +
                    printErrorList(response));
        }
        else {
            messageDialog(response.getSecurity().toString());
            System.out.println(response.getSecurity() + "\n");
        }
    }

}