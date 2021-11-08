package lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_visitors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ProgramVisitorList {

    private Map<Integer, VisitorUIAction> menuNumberToUIActionMap;
    private CheckMenuNumberFromConsole checkNumberFromConsole = new CheckMenuNumberFromConsole();

    @Autowired
    public ProgramVisitorList(List<VisitorUIAction> uiActions) {
        menuNumberToUIActionMap = new HashMap<>();
        menuNumberToUIActionMap.put(1, findUIAction(uiActions, AddVisitorsUIAction.class));
        menuNumberToUIActionMap.put(2, findUIAction(uiActions, DeleteVisitorsUIAction.class));
        menuNumberToUIActionMap.put(3, findUIAction(uiActions, ShowListWithAllVisitorsUIAction.class));
        menuNumberToUIActionMap.put(4, findUIAction(uiActions, SearchVisitorsUIAction.class));
        menuNumberToUIActionMap.put(5, findUIAction(uiActions, ExitProgramListVisitorUIAction.class));
        menuNumberToUIActionMap.put(6, findUIAction(uiActions, ExitUIAction.class));
    }

    private VisitorUIAction findUIAction(List<VisitorUIAction> uiActions, Class uiActionClass) {
        return uiActions.stream()
                .filter(visitorUIAction -> visitorUIAction.getClass().equals(uiActionClass))
                .findFirst()
                .get();
    }

    public void print() {
        System.out.println();
        System.out.println("Hello !");
        System.out.println("Visitors program menu, press number what do you want to do! ");
        System.out.println("1. Add information about visitor to list of restaurant-> ");
        System.out.println("2. Delete visitor - Enter ID that delete from list of restaurant-> ");
        System.out.println("3. Show all visitor's in base of restaurant->");
        System.out.println("4. Search visitor's in catalogue->");
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
