package lv.javaguru.java2.jg_entertainment.restaurant.console_ui;

import lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_menu.MenuListApplication;
import lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_reservation.ReservationListApplication;
import lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_tables.TableListApplication;
import lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_visitors.CheckMenuNumberFromConsole;
import lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_visitors.UIAction;
import lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_visitors.VisitorListApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class RestaurantProgramList {

    private Map<Integer, UIAction> menuNumberToUIActionMap;
    private CheckMenuNumberFromConsole checkNumber = new CheckMenuNumberFromConsole();

    @Autowired
    public RestaurantProgramList(List<UIAction> uiActions) {
        menuNumberToUIActionMap = new HashMap<>();
        menuNumberToUIActionMap.put(1, findUIAction(uiActions, VisitorListApplication.class));
        menuNumberToUIActionMap.put(2, findUIAction(uiActions, MenuListApplication.class));
        menuNumberToUIActionMap.put(3, findUIAction(uiActions, TableListApplication.class));
        menuNumberToUIActionMap.put(4, findUIAction(uiActions, ReservationListApplication.class));
        menuNumberToUIActionMap.put(5, findUIAction(uiActions, ExitProgram.class));
    }


    private UIAction findUIAction(List<UIAction> uiActions, Class uiActionClass) {
        return uiActions.stream()
                .filter(uiAction -> uiAction.getClass().equals(uiActionClass))
                .findFirst()
                .get();
    }

   public void printProgramMenu() {
        System.out.println();
        System.out.println("Hello ! MENU: ");
        System.out.println("1--> choose action with VISITORS: ");
        System.out.println("2--> choose action with restaurant MENU: ");
        System.out.println("3--> choose action with TABLES: ");
        System.out.println("4--> choose action with RESERVATION: ");
        System.out.println("5--> EXIT!");
        System.out.println();
    }

    public int checkNumberOfMenu() {
        System.out.println("Start by choosing the program menu!");
        return checkNumber.getCorrectNumberMenu(1, 5);
    }

    public void executeSelectMenuItem(int selectMenu, ApplicationContext applicationContext) {
        menuNumberToUIActionMap.get(selectMenu).execute(applicationContext);
    }
}
