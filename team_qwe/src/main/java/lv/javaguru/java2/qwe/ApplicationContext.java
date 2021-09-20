package lv.javaguru.java2.qwe;

import lv.javaguru.java2.qwe.core.database.Database;
import lv.javaguru.java2.qwe.core.database.DatabaseImpl;
import lv.javaguru.java2.qwe.core.database.UserData;
import lv.javaguru.java2.qwe.core.database.UserDataImpl;
import lv.javaguru.java2.qwe.core.services.data_services.*;
import lv.javaguru.java2.qwe.core.services.user_services.*;
import lv.javaguru.java2.qwe.core.services.validator.*;
import lv.javaguru.java2.qwe.ui_actions.ChooseDataMenuUIAction;
import lv.javaguru.java2.qwe.ui_actions.ChooseUserMenuUIAction;
import lv.javaguru.java2.qwe.ui_actions.data_ui_actions.*;
import lv.javaguru.java2.qwe.ui_actions.user_ui_actions.*;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ApplicationContext {

    private final Map<Class, Object> beans = new HashMap<>();

    public ApplicationContext() {
        beans.put(Database.class, new DatabaseImpl(new File("")));
        beans.put(UserData.class, new UserDataImpl(getBean(Database.class)));

        beans.put(AddBondValidator.class, new AddBondValidator(getBean(Database.class)));
        beans.put(AddStockValidator.class, new AddStockValidator(getBean(Database.class)));
        beans.put(AddUserValidator.class, new AddUserValidator(getBean(UserData.class)));
        beans.put(FilterStocksByMultipleParametersValidator.class, new FilterStocksByMultipleParametersValidator());
        beans.put(FindSecurityByNameValidator.class, new FindSecurityByNameValidator());
        beans.put(FindUserByNameValidator.class, new FindUserByNameValidator());
        beans.put(GenerateUserPortfolioValidator.class, new GenerateUserPortfolioValidator());
        beans.put(GetUserInvestmentsByEachIndustryValidator.class, new GetUserInvestmentsByEachIndustryValidator());
        beans.put(GetUserPortfolioGroupedByIndustryValidator.class, new GetUserPortfolioGroupedByIndustryValidator());
        beans.put(GetUserPortfolioSummaryValidator.class, new GetUserPortfolioSummaryValidator());
        beans.put(GetUserPortfolioValidator.class, new GetUserPortfolioValidator());

        beans.put(AddBondService.class, new AddBondService(
                getBean(Database.class),
                getBean(AddBondValidator.class)));
        beans.put(AddStockService.class, new AddStockService(
                getBean(Database.class),
                getBean(AddStockValidator.class)));
        beans.put(FilterStocksByMultipleParametersService.class, new FilterStocksByMultipleParametersService(
                getBean(Database.class),
                getBean(FilterStocksByMultipleParametersValidator.class)));
        beans.put(FindSecurityByNameService.class, new FindSecurityByNameService(
                getBean(Database.class),
                getBean(FindSecurityByNameValidator.class)));
        beans.put(GetAllSecurityListService.class, new GetAllSecurityListService(
                getBean(Database.class)));
        beans.put(ImportSecuritiesService.class, new ImportSecuritiesService(
                getBean(Database.class),
                getBean(AddStockValidator.class),
                getBean(AddBondValidator.class)));
        beans.put(RemoveSecurityService.class, new RemoveSecurityService(
                getBean(Database.class)));

        beans.put(AddUserService.class, new AddUserService(
                getBean(UserData.class),
                getBean(AddUserValidator.class)));
        beans.put(FindUserByNameService.class, new FindUserByNameService(
                getBean(UserData.class),
                getBean(FindUserByNameValidator.class)));
        beans.put(GenerateUserPortfolioService.class, new GenerateUserPortfolioService(
                getBean(UserData.class),
                getBean(GenerateUserPortfolioValidator.class)));
        beans.put(GetAllUserListService.class, new GetAllUserListService(
                getBean(UserData.class)));
        beans.put(GetUserInvestmentsByEachIndustryService.class, new GetUserInvestmentsByEachIndustryService(
                getBean(UserData.class),
                getBean(GetUserInvestmentsByEachIndustryValidator.class)));
        beans.put(GetUserPortfolioGroupedByIndustryService.class, new GetUserPortfolioGroupedByIndustryService(
                getBean(UserData.class),
                getBean(GetUserPortfolioGroupedByIndustryValidator.class)));
        beans.put(GetUserPortfolioService.class, new GetUserPortfolioService(
                getBean(UserData.class),
                getBean(GetUserPortfolioValidator.class)));
        beans.put(GetUserPortfolioSummaryService.class, new GetUserPortfolioSummaryService(
                getBean(UserData.class),
                getBean(GetUserPortfolioSummaryValidator.class)));
        beans.put(RemoveUserService.class, new RemoveUserService(
                getBean(UserData.class)));

        beans.put(ChooseDataMenuUIAction.class, new ChooseDataMenuUIAction());
        beans.put(ChooseUserMenuUIAction.class, new ChooseUserMenuUIAction());

        beans.put(AddBondUIAction.class, new AddBondUIAction(
                getBean(AddBondService.class)));
        beans.put(AddStockUIAction.class, new AddStockUIAction(
                getBean(AddStockService.class)));
        beans.put(FilterStocksByMultipleParametersUIAction.class, new FilterStocksByMultipleParametersUIAction(
                getBean(FilterStocksByMultipleParametersService.class)));
        beans.put(FindSecurityByNameUIAction.class, new FindSecurityByNameUIAction(
                getBean(FindSecurityByNameService.class)));
        beans.put(GetAllSecurityListUIAction.class, new GetAllSecurityListUIAction(
                getBean(GetAllSecurityListService.class)));
        beans.put(ImportDataFromFileUIAction.class, new ImportDataFromFileUIAction(
                getBean(ImportSecuritiesService.class)));
        beans.put(RemoveSecurityUIAction.class, new RemoveSecurityUIAction(
                getBean(RemoveSecurityService.class)));

        beans.put(AddUserUIAction.class, new AddUserUIAction(
                getBean(AddUserService.class)));
        beans.put(FindUserByNameUIAction.class, new FindUserByNameUIAction(
                getBean(FindUserByNameService.class)));
        beans.put(GenerateUserPortfolioUIAction.class, new GenerateUserPortfolioUIAction(
                getBean(GenerateUserPortfolioService.class)));
        beans.put(GetUserInvestmentsByEachIndustryUIAction.class, new GetUserInvestmentsByEachIndustryUIAction(
                getBean(GetUserInvestmentsByEachIndustryService.class)));
        beans.put(GetUserListUIAction.class, new GetUserListUIAction(
                getBean(GetAllUserListService.class)));
        beans.put(GetUserPortfolioGroupedByIndustryUIAction.class, new GetUserPortfolioGroupedByIndustryUIAction(
                getBean(GetUserPortfolioGroupedByIndustryService.class)));
        beans.put(GetUserPortfolioSummaryUIAction.class, new GetUserPortfolioSummaryUIAction(
                getBean(GetUserPortfolioSummaryService.class)));
        beans.put(GetUserPortfolioUIAction.class, new GetUserPortfolioUIAction(
                getBean(GetUserPortfolioService.class)));
        beans.put(RemoveUserUIAction.class, new RemoveUserUIAction(
                getBean(RemoveUserService.class)));
    }

    public <T extends Object> T getBean(Class c) {
        return (T) beans.get(c);
    }

}