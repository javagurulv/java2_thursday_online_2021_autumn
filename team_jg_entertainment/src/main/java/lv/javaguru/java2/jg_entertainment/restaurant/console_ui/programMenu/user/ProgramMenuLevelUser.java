package lv.javaguru.java2.jg_entertainment.restaurant.console_ui.programMenu.user;

import lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_users.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ProgramMenuLevelUser {
    private Map<Integer, UserUIAction> menuNumberToUIActionMap;
    private CheckMenuNumberFromConsole checkNumberFromConsole = new CheckMenuNumberFromConsole();

    @Autowired
    public ProgramMenuLevelUser(List<UserUIAction> uiActions) {
        menuNumberToUIActionMap = new HashMap<>();
        menuNumberToUIActionMap.put(1, findUIAction(uiActions, AddUsersUIAction.class));
        menuNumberToUIActionMap.put(2, findUIAction(uiActions, DeleteUsersUIAction.class));
        menuNumberToUIActionMap.put(3, findUIAction(uiActions, ExitProgramListUserUIAction.class));
        menuNumberToUIActionMap.put(4, findUIAction(uiActions, ExitUIAction.class));
    }

    private UserUIAction findUIAction(List<UserUIAction> uiActions, Class uiActionClass) {
        return uiActions.stream()
                .filter(userUIAction -> userUIAction.getClass().equals(uiActionClass))
                .findFirst()
                .get();
    }

    public void print() {
        System.out.println();
        System.out.println("Hello !");
        System.out.println("Users program menu, press number what do you want to do! ");
        System.out.println("1. ADD information about user to list of restaurant-> ");
        System.out.println("2. DELETE user - Enter ID that delete from list of restaurant-> ");
        System.out.println("3. Choose that return in MAIN MENU:");
        System.out.println("4. Exit! ");
        System.out.println();
    }

    public int getMenuNumberFromUser() {
        return checkNumberFromConsole.getCorrectNumberMenu(1, 4);
    }

    public void executeSelectMenuItem(int selectMenu) {
        menuNumberToUIActionMap.get(selectMenu).execute();
    }
}
