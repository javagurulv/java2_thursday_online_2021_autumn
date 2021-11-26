package lv.javaguru.java2.qwe.ui_actions.user_ui_actions;

import lv.javaguru.java2.qwe.core.requests.user_requests.GenerateUserPortfolioRequest;
import lv.javaguru.java2.qwe.core.responses.user_responses.GenerateUserPortfolioResponse;
import lv.javaguru.java2.qwe.core.services.user_services.GenerateUserPortfolioService;
import lv.javaguru.java2.qwe.ui_actions.UIAction;
import lv.javaguru.java2.qwe.core.utils.UtilityMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GenerateUserPortfolioUIAction implements UIAction {

    @Autowired private GenerateUserPortfolioService generatePortfolioService;
    @Autowired private UtilityMethods utils;

    @Override
    public void execute() {
        GenerateUserPortfolioRequest request =
                new GenerateUserPortfolioRequest(utils.inputDialog(
                        "Choose user:",
                        "GENERATE PORTFOLIO",
                        utils.convertToStringArray(generatePortfolioService.getUserData())
                ));
        GenerateUserPortfolioResponse response =
                generatePortfolioService.execute(request);
        printResponse(response);
    }

    public void printResponse(GenerateUserPortfolioResponse response) {
        if (!response.hasErrors()) {
            String userName = response.getUser().getName() + "(ID: " + response.getUser().getId() + ")";
            utils.messageDialog("Portfolio has been generated for " + userName);
            System.out.println("==============" + userName + "===============");
            response.getPortfolio().forEach(System.out::println);
            System.out.println("CASH: " + response.getUser().getCash());
            System.out.println("\n");
        } else {
            utils.messageDialog("WRONG INPUT!\n" +
                    utils.printErrorList(response));
        }
    }

}