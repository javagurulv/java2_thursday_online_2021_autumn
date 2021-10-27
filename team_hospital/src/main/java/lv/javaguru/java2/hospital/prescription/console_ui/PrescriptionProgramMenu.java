package lv.javaguru.java2.hospital.prescription.console_ui;

import lv.javaguru.java2.hospital.InputNumChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PrescriptionProgramMenu {

    private Map<Integer, PrescriptionUIAction> menuNumberToUIActionMap;
    private static final InputNumChecker inputNumChecker = new InputNumChecker();

    @Autowired
    public PrescriptionProgramMenu(List<PrescriptionUIAction> uiActions) {
        menuNumberToUIActionMap = new HashMap<>();
        menuNumberToUIActionMap.put(1, findUIAction(uiActions, AddPrescriptionUIAction.class));
        menuNumberToUIActionMap.put(2, findUIAction(uiActions, EditPrescriptionUIAction.class));
        menuNumberToUIActionMap.put(3, findUIAction(uiActions, ShowAllPrescriptionUIAction.class));
        menuNumberToUIActionMap.put(4, findUIAction(uiActions, DeletePrescriptionUIAction.class));
        menuNumberToUIActionMap.put(5, findUIAction(uiActions, ExitPrescriptionUIAction.class));
    }

    private PrescriptionUIAction findUIAction(List<PrescriptionUIAction> uiActions, Class uiActionClass) {
        return uiActions.stream()
                .filter(prescriptionUIAction -> prescriptionUIAction.getClass().equals(uiActionClass))
                .findFirst()
                .get();
    }

    public void printPrescriptionMenu() {
        System.out.println("1. Prescribe medication");
        System.out.println("2. Edit the prescription's information");
        System.out.println("3. Show all prescriptions");
        System.out.println("4. Delete prescription");
       // System.out.println("5. Search prescriptions");
        System.out.println("5. Exit");
    }

    public int getPrescriptionMenuNumberFromUser() {
        return inputNumChecker.execute(1, 5);
    }

    public void executeSelectedMenuItem(int selectedMenu) {
        menuNumberToUIActionMap.get(selectedMenu).execute();
    }
}
