package lv.javaguru.java2.hospital.visit.console_ui;
import lv.javaguru.java2.hospital.InputNumChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class VisitProgramMenu {

    private final Map<Integer, VisitUIAction> menuNumberToUIActionMap;
    private static final InputNumChecker inputNumChecker = new InputNumChecker();

    @Autowired
    public VisitProgramMenu(List<VisitUIAction> uiAction) {
        menuNumberToUIActionMap = new HashMap<>();
        menuNumberToUIActionMap.put(1, findVisitUIAction(uiAction, AddVisitUIAction.class));
        menuNumberToUIActionMap.put(2, findVisitUIAction(uiAction, DeleteVisitUIAction.class));
        menuNumberToUIActionMap.put(3, findVisitUIAction(uiAction, ShowAllVisitUIAction.class));
        menuNumberToUIActionMap.put(4, findVisitUIAction(uiAction, EditVisitUIAction.class));
        menuNumberToUIActionMap.put(5, findVisitUIAction(uiAction, SearchVisitUIAction.class));
        menuNumberToUIActionMap.put(6, findVisitUIAction(uiAction, ExitVisitUIAction.class));
    }

    private VisitUIAction findVisitUIAction(List<VisitUIAction> visitUIActions, Class uiActionClass) {
        return visitUIActions.stream()
                .filter(visitUIAction -> visitUIAction.getClass().equals(uiActionClass))
                .findFirst()
                .get();
    }

    public void printMenuPatientVisit() {
        System.out.println("1. Add a patient visit.");
        System.out.println("2. Delete patient visit.");
        System.out.println("3. Show all patient visits.");
        System.out.println("4. Edit patient visits.");
        System.out.println("5. Search patient visits.");
        System.out.println("6. Exit.");
    }

    public int getVisitMenuNumberFromUser() {
        return inputNumChecker.execute(1, 6);
    }

    public void executeSelectedMenuItem(int selectedMenu) {
        menuNumberToUIActionMap.get(selectedMenu).execute();
    }
}
