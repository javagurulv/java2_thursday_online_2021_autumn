package lv.javaguru.java2.jg_entertainment.restaurant.console_ui.programmenu;

import lv.javaguru.java2.jg_entertainment.restaurant.EnteredNumChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProgramMenuHelper {

    private Map<Integer, Menu> menuNumberToTypeOfMenuMap;
    private static final EnteredNumChecker enteredNumChecker = new EnteredNumChecker();

    @Autowired
    public ProgramMenuHelper(List<Menu> menus) {
        menuNumberToTypeOfMenuMap = new HashMap<>();
        menuNumberToTypeOfMenuMap.put(1, findMenu(menus, VisitorMenu.class));
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
        System.out.println("Program menu: ");
        System.out.println("Press 1 to see visitor actions.");
        System.out.println("Press 2 to see table actions.");
        System.out.println("Press 3 to see menu actions.");
        System.out.println("Press 4 to see reservation menu.");
        System.out.println("Press 5 for Exit.");
    }

    public int getMenuNumberFromUser() {
        System.out.println("Enter menu item number to execute: ");
        return enteredNumChecker.execute(1, 5);
    }

    public void executeSelectedMenuItem(int selectedMenu, ApplicationContext applicationContext) {
        menuNumberToTypeOfMenuMap.get(selectedMenu).execute(applicationContext);
    }
}
