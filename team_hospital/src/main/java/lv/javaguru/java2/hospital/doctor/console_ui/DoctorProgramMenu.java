package lv.javaguru.java2.hospital.doctor.console_ui;

import lv.javaguru.java2.hospital.InputNumChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class DoctorProgramMenu {

    private Map<Integer, DoctorUIAction> menuNumberToUIActionMap;
    private static final InputNumChecker inputNumChecker = new InputNumChecker();

    @Autowired
    public DoctorProgramMenu(List<DoctorUIAction> uiActions) {
        menuNumberToUIActionMap = new HashMap<>();
        menuNumberToUIActionMap.put(1, findUIAction(uiActions, AddDoctorUIAction.class));
        menuNumberToUIActionMap.put(2, findUIAction(uiActions, ShowAllDoctorsUIAction.class));
        menuNumberToUIActionMap.put(3, findUIAction(uiActions, DeleteDoctorUIAction.class));
        menuNumberToUIActionMap.put(4, findUIAction(uiActions, EditDoctorUIAction.class));
        menuNumberToUIActionMap.put(5, findUIAction(uiActions, SearchDoctorsUIAction.class));
        menuNumberToUIActionMap.put(6, findUIAction(uiActions, ShowDoctorVisitsUIAction.class));
        menuNumberToUIActionMap.put(7, findUIAction(uiActions, ExitDoctorUIAction.class));
    }

    private DoctorUIAction findUIAction(List<DoctorUIAction> uiActions, Class uiActionClass) {
        return uiActions.stream()
                .filter(doctorUIAction -> doctorUIAction.getClass().equals(uiActionClass))
                .findFirst()
                .get();
    }


    public void printDoctorMenu() {
        System.out.println("1. Add a new doctor");
        System.out.println("2. Show all doctors");
        System.out.println("3. Delete the doctor by ID");
        System.out.println("4. Edit the doctor's information");
        System.out.println("5. Search doctors");
        System.out.println("6. Show doctor's visits");
        System.out.println("7. Exit");
    }

    public int getDoctorMenuNumberFromUser() {
        return inputNumChecker.execute(1, 7);
    }

    public void executeSelectedMenuItem(int selectedMenu) {
        menuNumberToUIActionMap.get(selectedMenu).execute();
    }
}
