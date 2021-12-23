package lv.javaguru.java2.oddJobs.console_ui;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import lv.javaguru.java2.oddJobs.console_ui.add.AddAdvertisementUIAction;
import lv.javaguru.java2.oddJobs.console_ui.add.AddClientUIAction;
import lv.javaguru.java2.oddJobs.console_ui.add.AddSpecialistUIAction;
import lv.javaguru.java2.oddJobs.console_ui.exit.ExitMenuUIAction;
import lv.javaguru.java2.oddJobs.console_ui.find.FindAdvertisementUIAction;
import lv.javaguru.java2.oddJobs.console_ui.find.FindClientsUIAction;
import lv.javaguru.java2.oddJobs.console_ui.find.FindSpecialistUIAction;
import lv.javaguru.java2.oddJobs.console_ui.get.GetAllAdvertisementsUIAction;
import lv.javaguru.java2.oddJobs.console_ui.get.GetAllClientsUIAction;
import lv.javaguru.java2.oddJobs.console_ui.get.GetAllSpecialistUIAction;
import lv.javaguru.java2.oddJobs.console_ui.remove.RemoveClientUIAction;
import lv.javaguru.java2.oddJobs.console_ui.remove.RemoveSpecialistUIAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProgramMenu {
    private Map<Integer, UIAction> menuNumberToUIActionMap;

    @Autowired
    public ProgramMenu(List<UIAction> uiActions) {
        menuNumberToUIActionMap = new HashMap<>();
        menuNumberToUIActionMap.put(1, findUIAction(uiActions, AddClientUIAction.class));
        menuNumberToUIActionMap.put(2, findUIAction(uiActions, AddSpecialistUIAction.class));
        menuNumberToUIActionMap.put(3, findUIAction(uiActions, AddAdvertisementUIAction.class));
        menuNumberToUIActionMap.put(4, findUIAction(uiActions, FindSpecialistUIAction.class));
        menuNumberToUIActionMap.put(5, findUIAction(uiActions, FindClientsUIAction.class));
        menuNumberToUIActionMap.put(6, findUIAction(uiActions, FindAdvertisementUIAction.class));
        menuNumberToUIActionMap.put(7, findUIAction(uiActions, GetAllClientsUIAction.class));
        menuNumberToUIActionMap.put(8, findUIAction(uiActions, GetAllSpecialistUIAction.class));
        menuNumberToUIActionMap.put(9, findUIAction(uiActions, GetAllAdvertisementsUIAction.class));
        menuNumberToUIActionMap.put(10, findUIAction(uiActions, RemoveClientUIAction.class));
        menuNumberToUIActionMap.put(11, findUIAction(uiActions, RemoveSpecialistUIAction.class));
        menuNumberToUIActionMap.put(12, findUIAction(uiActions, ExitMenuUIAction.class));

    }

    private UIAction findUIAction(List<UIAction> uiActions, Class uiActionClass) {
        return uiActions.stream()
                .filter(uiAction -> uiAction.getClass().equals(uiActionClass))
                .findFirst()
                .get();
    }

    public void printProgramMenu() {
        System.out.println("Menu:");
        System.out.println("1.  Add client account");
        System.out.println("2.  Add specialist account");
        System.out.println("3.  Add advertisement");
        System.out.println("4.  Find specialist by search criteria");
        System.out.println("5.  Find client by search criteria");
        System.out.println("6.  Find advertisement by search criteria");
        System.out.println("7.  Show all clients");
        System.out.println("8.  Show all specialists");
        System.out.println("9.  Show all advertisements");
        System.out.println("10. Delete client account");
        System.out.println("11  Delete specialist account");
        System.out.println("12. Exit");
    }

    public int getUserChoice() {
        System.out.println("Enter a menu number to continue");
        Scanner scanner = new Scanner(System.in);
        return Integer.parseInt(scanner.nextLine());
    }

    public void executeSelectedMenuItem(int selectedMenu) {
        menuNumberToUIActionMap.get(selectedMenu).execute();
    }

}
