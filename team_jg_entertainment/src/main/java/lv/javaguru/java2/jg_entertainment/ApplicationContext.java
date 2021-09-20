package lv.javaguru.java2.jg_entertainment;

import lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_menu.AddMenuUIAction;
import lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_menu.ExitUIAction;
import lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_menu.GetAllMenusUIAction;
import lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_menu.RemoveMenuUIAction;
import lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_tables.AddTableUIAction;
import lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_tables.ExitTableUIAction;
import lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_tables.GetAllTablesUIAction;
import lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_tables.RemoveTableUIAction;
import lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_visitors.*;
import lv.javaguru.java2.jg_entertainment.restaurant.core.database.*;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_menu.AddMenuService;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_menu.GetAllMenusService;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_menu.RemoveMenuService;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_tables.AddTableService;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_tables.GetAllTablesService;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_tables.RemoveTableService;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_visitors.SearchVisitorsService;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_visitors.ServiceAddAllVisitors;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_visitors.ServiceDeleteVisitors;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_visitors.ServiceShowListVisitors;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.validatorsVisitors.SearchVisitorsRequestValidator;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.validatorsVisitors.ValidatorAddVisitor;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.validatorsVisitors.ValidatorDeleteVisitor;

import java.util.HashMap;
import java.util.Map;

public class ApplicationContext {

    private Map<Class, Object> beans = new HashMap<>();

    public ApplicationContext() {
        beans.put(DatabaseVisitors.class, new ImplDatabaseVisitors());//visitors
        beans.put(DatabaseMenu.class, new ImplDatabaseMenu());//menu
        beans.put(TableDatabase.class, new ImplDatabaseTable());//table
//visitors
        beans.put(ValidatorAddVisitor.class, new ValidatorAddVisitor());
        beans.put(ValidatorDeleteVisitor.class, new ValidatorDeleteVisitor());
        beans.put(SearchVisitorsRequestValidator.class, new SearchVisitorsRequestValidator());

        beans.put(ServiceAddAllVisitors.class, new ServiceAddAllVisitors(
                getBean(DatabaseVisitors.class),
                getBean(ValidatorAddVisitor.class)));
        beans.put(ServiceDeleteVisitors.class, new ServiceDeleteVisitors(
                getBean(DatabaseVisitors.class),
                getBean(ValidatorDeleteVisitor.class)));
        beans.put(ServiceShowListVisitors.class, new ServiceShowListVisitors(
                getBean(DatabaseVisitors.class)));
        beans.put(SearchVisitorsService.class, new SearchVisitorsService(
                getBean(DatabaseVisitors.class),
                getBean(SearchVisitorsRequestValidator.class)));

        beans.put(UIActionAddVisitors.class, new UIActionAddVisitors(getBean(ServiceAddAllVisitors.class)));
        beans.put(UIActionDeleteVisitors.class, new UIActionDeleteVisitors(getBean(ServiceDeleteVisitors.class)));
        beans.put(UIActionShowListWithAllVisitors.class, new UIActionShowListWithAllVisitors(getBean(ServiceShowListVisitors.class)));
        beans.put(SearchVisitorsUIAction.class, new SearchVisitorsUIAction(getBean(SearchVisitorsService.class)));
        beans.put(UIActionExit.class, new UIActionExit());
//menu
        beans.put(AddMenuService.class, new AddMenuService(getBean(DatabaseMenu.class)));
        beans.put(RemoveMenuService.class, new RemoveMenuService(getBean(DatabaseMenu.class)));
        beans.put(GetAllMenusService.class, new GetAllMenusService(getBean(DatabaseMenu.class)));

        beans.put(AddMenuUIAction.class, new AddMenuUIAction(getBean(AddMenuService.class)));
        beans.put(RemoveMenuUIAction.class, new RemoveMenuUIAction(getBean(RemoveMenuService.class)));
        beans.put(GetAllMenusUIAction.class, new GetAllMenusUIAction(getBean(GetAllMenusService.class)));
        beans.put(ExitUIAction.class, new ExitUIAction());
//tables
        beans.put(AddTableService.class, new AddTableService(getBean(TableDatabase.class)));
        beans.put(RemoveTableService.class, new RemoveTableService(getBean(TableDatabase.class)));
        beans.put(GetAllTablesService.class, new GetAllTablesService(getBean(TableDatabase.class)));

        beans.put(AddTableUIAction.class, new AddTableUIAction(getBean(AddTableService.class)));
        beans.put(RemoveTableUIAction.class, new RemoveTableUIAction(getBean(RemoveTableService.class)));
        beans.put(GetAllTablesUIAction.class, new GetAllTablesUIAction(getBean(GetAllTablesService.class)));
        beans.put(ExitTableUIAction.class, new ExitTableUIAction());
    }

    public <T extends Object> T getBean(Class c) {
        return (T) beans.get(c);
    }
}
