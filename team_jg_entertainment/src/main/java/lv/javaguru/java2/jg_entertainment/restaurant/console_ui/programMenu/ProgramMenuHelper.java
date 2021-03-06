package lv.javaguru.java2.jg_entertainment.restaurant.console_ui.programMenu;

import lv.javaguru.java2.jg_entertainment.restaurant.EnteredNumChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ProgramMenuHelper {

    private Map<Integer, Menu> menuNumberToTypeOfMenuMap;
    private static final EnteredNumChecker enteredNumChecker = new EnteredNumChecker();

    @Autowired
    public ProgramMenuHelper(List<Menu> menus) {
        menuNumberToTypeOfMenuMap = new HashMap<>();
        menuNumberToTypeOfMenuMap.put(1, findMenu(menus, UserMenu.class));
        menuNumberToTypeOfMenuMap.put(2, findMenu(menus, TableMenu.class));
        menuNumberToTypeOfMenuMap.put(3, findMenu(menus, MenuMenu.class));
        menuNumberToTypeOfMenuMap.put(4, findMenu(menus, ReservationMenu.class));
        menuNumberToTypeOfMenuMap.put(5, findMenu(menus, ExitMenu.class));
    }

    private Menu findMenu(List<Menu> menus, Class menusClass) {
        return menus.stream()
                .filter(menu -> menu.getClass().equals(menusClass))
                .findFirst()
                .get();
    }

    public void printMainMenu() {
        System.out.println();
        System.out.println("Hello ! MENU: ");
        System.out.println("1--> choose action with USERS: ");
        System.out.println("2--> choose action with TABLES: ");
        System.out.println("3--> choose action with restaurant MENU: ");
        System.out.println("4--> choose action with RESERVATION: ");
        System.out.println("5--> EXIT! ");
        System.out.println();
    }

    public int getMenuNumberFromUser() {
        System.out.println("Enter menu item number to execute: ");
        return enteredNumChecker.execute(1, 5);
    }

    public void executeSelectedMenuItem(int selectedMenu, ApplicationContext applicationContext) {
        menuNumberToTypeOfMenuMap.get(selectedMenu).execute(applicationContext);
    }
}