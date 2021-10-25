package lv.javaguru.java2.hospital.patient.console_ui;
import lv.javaguru.java2.hospital.InputNumChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PatientProgramMenu {

    private final Map<Integer, PatientUIActions> menuNumberToUIActionMap;
    private static final InputNumChecker inputNumChecker = new InputNumChecker();

    @Autowired
    public PatientProgramMenu(List<PatientUIActions> uiAction) {
        menuNumberToUIActionMap = new HashMap<>();
        menuNumberToUIActionMap.put(1, findPatientUIAction(uiAction, AddPatientUIAction.class));
        menuNumberToUIActionMap.put(2, findPatientUIAction(uiAction, ShowAllPatientsUIAction.class));
        menuNumberToUIActionMap.put(3, findPatientUIAction(uiAction, FindPatientByIDUIAction.class));
        menuNumberToUIActionMap.put(4, findPatientUIAction(uiAction, DeletePatientUIAction.class));
        menuNumberToUIActionMap.put(5, findPatientUIAction(uiAction, EditPatientUIAction.class));
        menuNumberToUIActionMap.put(6, findPatientUIAction(uiAction, SearchPatientsUIAction.class));
        menuNumberToUIActionMap.put(7, findPatientUIAction(uiAction, ExitMenuUIAction.class));
    }

    private PatientUIActions findPatientUIAction(List<PatientUIActions> patientUIActions, Class uiActionClass){
        return patientUIActions.stream()
                .filter(patientUIAction -> patientUIAction.getClass().equals(uiActionClass))
                .findFirst()
                .get();
    }

    public void printPatientMenu() {
        System.out.println("1. Add a new patient");
        System.out.println("2. Show all patients");
        System.out.println("3. Find patient by ID");
        System.out.println("4. Delete the by ID");
        System.out.println("5. Edit the patient's information");
        System.out.println("6. Patient search");
        System.out.println("7. Exit");
    }

    public int getPatientMenuNumberFromUser() {
        return inputNumChecker.execute(1, 7);
    }

    public void executeSelectedMenuItem(int selectedMenu) {
        menuNumberToUIActionMap.get(selectedMenu).execute();
    }
}