package lv.javaguru.java2.jg_entertainment.restaurant.dependency_injection;

import lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_menu.*;
import lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_reservation.UIActionAddReservation;
import lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_tables.*;
import lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_visitors.*;
import lv.javaguru.java2.jg_entertainment.restaurant.core.database.*;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.service_reservation.ServiceAddReservation;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_menu.AddMenuService;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_menu.GetAllMenusService;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_menu.RemoveMenuService;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_menu.SearchMenusService;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_tables.AddTableService;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_tables.GetAllTablesService;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_tables.RemoveTableService;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_tables.SearchTableService;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_visitors.ServiceSearchVisitors;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_visitors.ServiceAddAllVisitors;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_visitors.ServiceDeleteVisitors;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_visitors.ServiceShowListVisitors;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.validator_reservation.ValidatorAddReservation;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators.*;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.validatorsMenus.AddMenuValidator;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.validatorsMenus.SearchMenusRequestValidator;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.validatorsVisitors.*;

import java.util.HashMap;
import java.util.Map;

public class ApplicationContextMenu {

    private final Map<Class, Object> beans = new HashMap<>();

    public ApplicationContextMenu() {

        beans.put(DatabaseMenu.class, new ImplDatabaseMenu());//menu
//menu
        beans.put(AddMenuValidator.class, new AddMenuValidator());
        beans.put(SearchMenusRequestValidator.class, new SearchMenusRequestValidator());

        beans.put(AddMenuService.class, new AddMenuService(getBean(DatabaseMenu.class), getBean(AddMenuValidator.class)));
        beans.put(RemoveMenuService.class, new RemoveMenuService(getBean(DatabaseMenu.class)));
        beans.put(GetAllMenusService.class, new GetAllMenusService(getBean(DatabaseMenu.class)));
        beans.put(SearchMenusService.class, new SearchMenusService(
                getBean(DatabaseMenu.class),
                getBean(SearchMenusRequestValidator.class)));

        beans.put(AddMenuUIAction.class, new AddMenuUIAction(
                getBean(AddMenuService.class)));
        beans.put(RemoveMenuUIAction.class, new RemoveMenuUIAction(
                getBean(RemoveMenuService.class)));
        beans.put(SearchMenusUIAction.class, new SearchMenusUIAction(
                getBean(SearchMenusService.class)));
        beans.put(GetAllMenusUIAction.class, new GetAllMenusUIAction(
                getBean(GetAllMenusService.class)));
        beans.put(ExitUIAction.class, new ExitUIAction());
    }

    public <T extends Object> T getBean(Class c) {
        return (T) beans.get(c);
    }
}