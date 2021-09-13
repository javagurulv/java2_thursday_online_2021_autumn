package lv.javaguru.java2.qwe.ui_actions.user_ui_actions;

import lv.javaguru.java2.qwe.core.requests.user_requests.FindUserByNameRequest;
import lv.javaguru.java2.qwe.core.responses.user_responses.FindUserByNameResponse;
import lv.javaguru.java2.qwe.core.services.user_services.FindUserByNameService;
import lv.javaguru.java2.qwe.ui_actions.UIAction;

import static lv.javaguru.java2.qwe.utils.UtilityMethods.*;

public class FindUserByNameUIAction implements UIAction {

    private final FindUserByNameService findUserByNameService;

    public FindUserByNameUIAction(FindUserByNameService findUserByNameService) {
        this.findUserByNameService = findUserByNameService;
    }

    @Override
    public void execute() {
        FindUserByNameRequest request = new FindUserByNameRequest(inputDialog("Enter name:"));
        FindUserByNameResponse response = findUserByNameService.execute(request);
        printResponse(response);
    }

    private void printResponse(FindUserByNameResponse response) {
        if (response.getUser() == null && !response.hasErrors()) {
            messageDialog("There is no user with such name!");
        } else if (response.hasErrors()) {
            messageDialog("WRONG INPUT!\n" +
                    printErrorList(response));
        } else {
            messageDialog(response.getUser().toString());
            System.out.println(response.getUser() + "\n");
        }
    }

}