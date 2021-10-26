package lv.javaguru.java2.hospital.progmenu.console_ui;

import lv.javaguru.java2.hospital.InputNumChecker;
import lv.javaguru.java2.hospital.progmenu.menus.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ProgramMenuHelper {

    private Map<Integer, Menu> menuNumberToTypeOfMenuMap;
    private static final InputNumChecker inputNumChecker = new InputNumChecker();

    @Autowired
    public ProgramMenuHelper(List<Menu> menus) {
        menuNumberToTypeOfMenuMap = new HashMap<>();
        menuNumberToTypeOfMenuMap.put(1, findMenu(menus, PatientMenu.class));
        menuNumberToTypeOfMenuMap.put(2, findMenu(menus, DoctorMenu.class));
        menuNumberToTypeOfMenuMap.put(3, findMenu(menus, VisitMenu.class));
        menuNumberToTypeOfMenuMap.put(4, findMenu(menus, PrescriptionMenu.class));
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
        System.out.println("Press 1 to see patient actions.");
        System.out.println("Press 2 to see doctor actions.");
        System.out.println("Press 3 to see visit actions.");
        System.out.println("Press 4 to see prescription menu.");
        System.out.println("Press 5 for Exit.");
    }

    public int getMenuNumberFromUser() {
        System.out.println("Enter menu item number to execute: ");
        return inputNumChecker.execute(1, 5);
    }

    public void executeSelectedMenuItem(int selectedMenu, ApplicationContext applicationContext) {
        menuNumberToTypeOfMenuMap.get(selectedMenu).execute(applicationContext);
    }
}
