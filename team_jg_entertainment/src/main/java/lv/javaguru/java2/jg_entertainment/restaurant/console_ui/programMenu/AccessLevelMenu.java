package lv.javaguru.java2.jg_entertainment.restaurant.console_ui.programMenu;

import lv.javaguru.java2.jg_entertainment.restaurant.EnteredNumChecker;
import lv.javaguru.java2.jg_entertainment.restaurant.console_ui.programMenu.user.AccessLevelForUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AccessLevelMenu {

    private Map<Integer, Menu> menuNumberToTypeOfMenuMap;
    private static final EnteredNumChecker enteredNumChecker = new EnteredNumChecker();

    @Autowired
    public AccessLevelMenu(List<Menu> menus) {
        menuNumberToTypeOfMenuMap = new HashMap<>();
        menuNumberToTypeOfMenuMap.put(1, findMenu(menus, AccessLevelForPersonal.class));
        menuNumberToTypeOfMenuMap.put(2, findMenu(menus, AccessLevelForUser.class));

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
        System.out.println("1--> choose action if you are PERSONAL: ");
        System.out.println("2--> choose action if you are USER(visitor): ");
        System.out.println("3--> EXIT! ");
        System.out.println();
    }

    public int getMenuNumberFromUser() {
        System.out.println("Enter menu item number to execute: ");
        return enteredNumChecker.execute(1, 3);
    }

    public void executeSelectedMenuItem(int selectedMenu, ApplicationContext applicationContext) {
        menuNumberToTypeOfMenuMap.get(selectedMenu).execute(applicationContext);
    }
}

