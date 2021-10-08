package lv.javaguru.java2.qwe.ui_actions.user_ui_actions;

import lv.javaguru.java2.qwe.core.requests.user_requests.GenerateUserPortfolioRequest;
import lv.javaguru.java2.qwe.core.responses.user_responses.GenerateUserPortfolioResponse;
import lv.javaguru.java2.qwe.core.services.user_services.GenerateUserPortfolioService;
import lv.javaguru.java2.qwe.ui_actions.UIAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static lv.javaguru.java2.qwe.utils.UtilityMethods.*;
import static lv.javaguru.java2.qwe.utils.UtilityMethods.printErrorList;

@Component
public class GenerateUserPortfolioUIAction implements UIAction {

    @Autowired private GenerateUserPortfolioService generatePortfolioService;
    private String userName;

    @Override
    public void execute() {
        GenerateUserPortfolioRequest request =
                new GenerateUserPortfolioRequest(inputDialog(
                        "Choose user:",
                        "GENERATE PORTFOLIO",
                        convertToStringArray(generatePortfolioService.getUserData())
                ));
        userName = request.getUserName();
        GenerateUserPortfolioResponse response =
                generatePortfolioService.execute(request);
        printResponse(response);
    }

    public void printResponse(GenerateUserPortfolioResponse response) {
        if (!response.hasErrors()) {
            messageDialog("Portfolio has been generated for " + userName);
            System.out.println("==============" + userName + "===============");
            response.getPortfolio().forEach(System.out::println);
            System.out.println("\n");
        } else {
            messageDialog("WRONG INPUT!\n" +
                    printErrorList(response));
        }
    }

}