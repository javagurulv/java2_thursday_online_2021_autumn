package lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_menu;


import lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_users.CheckMenuNumberFromConsole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ProgramMenuRestaurant {

        private Map<Integer, UIAction> menuNumberToUIActionMap;
        private CheckMenuNumberFromConsole checkNumberFromConsole = new CheckMenuNumberFromConsole();

        @Autowired
        public ProgramMenuRestaurant(List<UIAction> uiAction) {
            menuNumberToUIActionMap = new HashMap<>();
            menuNumberToUIActionMap.put(1, findUIAction(uiAction, AddMenuUIAction.class));
            menuNumberToUIActionMap.put(2, findUIAction(uiAction, RemoveMenuUIAction.class));
            menuNumberToUIActionMap.put(3, findUIAction(uiAction, GetAllMenusUIAction.class));
            menuNumberToUIActionMap.put(4, findUIAction(uiAction, ExitToMainProgramListUIAction.class));//return in main menu(RestaurantProgramList ->mainAction)
            menuNumberToUIActionMap.put(5, findUIAction(uiAction, ExitMenuUIAction.class));

        }

        private UIAction findUIAction(List<UIAction> uiActions, Class uiActionClass) {
            return uiActions.stream()
                    .filter(uiAction -> uiAction.getClass().equals(uiActionClass))
                    .findFirst()
                    .get();
        }

        public void printMenuInRestaurant() {
            System.out.println("Restaurant menu: ");
            System.out.println("1. Add menu to list: ");
            System.out.println("2. Delete menu from list: ");
            System.out.println("3. Show all menus in the list: "); ///,???
            System.out.println("4. Choose that return in MAIN MENU:");//return in main menu(RestaurantProgramList -> mainAction)
            System.out.println("5. Exit!");
            System.out.println("");
        }

        public int getMenuNumberFromUser() {
            return checkNumberFromConsole.getCorrectNumberMenu(1, 5);
        }

        public void executeSelectMenuItem(int selectMenu) {
            menuNumberToUIActionMap.get(selectMenu).execute();
        }
    }

