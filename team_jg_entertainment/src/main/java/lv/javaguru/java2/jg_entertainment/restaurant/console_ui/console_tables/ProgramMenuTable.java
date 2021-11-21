package lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_tables;

import lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_visitors.CheckMenuNumberFromConsole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ProgramMenuTable {

    private Map<Integer, UIAction> menuNumberToUIActionMap;
    private CheckMenuNumberFromConsole checkNumberFromConsole = new CheckMenuNumberFromConsole();

    @Autowired
    public ProgramMenuTable(List<UIAction> uiActions) {
        menuNumberToUIActionMap = new HashMap<>();
        menuNumberToUIActionMap.put(1, findUIAction(uiActions, AddTableUIAction.class));
        menuNumberToUIActionMap.put(2, findUIAction(uiActions, RemoveTableUIAction.class));
        menuNumberToUIActionMap.put(3, findUIAction(uiActions, SearchTableUIAction.class));
        menuNumberToUIActionMap.put(4, findUIAction(uiActions, GetAllTablesUIAction.class));
        menuNumberToUIActionMap.put(5, findUIAction(uiActions, ExitProgramTableListUIAction.class));
        menuNumberToUIActionMap.put(6, findUIAction(uiActions, ExitTableUIAction.class));
    }

    private UIAction findUIAction(List<UIAction> uiActions, Class uiActionClass) {
        return uiActions.stream()
                .filter(uiAction -> uiAction.getClass().equals(uiActionClass))
                .findFirst()
                .get();
    }

    public void printTableMenu() {
        System.out.println();
        System.out.println("Tables menu: ");
        System.out.println("1. Add table to list: ");
        System.out.println("2. Delete table from list: ");
        System.out.println("3. Search table information from list: ");
        System.out.println("4. Show all tables in the list: ");
        System.out.println("5. Choose that return in MAIN MENU:");
        System.out.println("6. Exit! ");
        System.out.println();
    }

    public int getMenuNumberFromUser() {
        return checkNumberFromConsole.getCorrectNumberMenu(1, 6);
    }

    public void executeSelectMenuItem(int selectMenu) {
        menuNumberToUIActionMap.get(selectMenu).execute();
    }
}
