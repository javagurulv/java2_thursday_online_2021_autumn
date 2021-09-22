package lv.javaguru.java2.hospital;

import lv.javaguru.java2.hospital.database.PatientDatabase;
import lv.javaguru.java2.hospital.database.PatientDatabaseImpl;
import lv.javaguru.java2.hospital.patient.console_ui.*;
import lv.javaguru.java2.hospital.patient.core.services.*;
import lv.javaguru.java2.hospital.patient.core.services.validators.*;
import java.util.HashMap;
import java.util.Map;

public class PatientApplicationContext {

    private final Map<Class, Object> beans = new HashMap<>();

    public PatientApplicationContext() {
        beans.put(PatientDatabase.class, new PatientDatabaseImpl());

        beans.put(AddPatientValidator.class, new AddPatientValidator());
        beans.put(DeletePatientValidator.class, new DeletePatientValidator(getBean(PatientDatabase.class)));
        beans.put(EditPatientValidator.class, new EditPatientValidator(getBean(PatientDatabase.class)));
        beans.put(FindPatientByIDValidator.class, new FindPatientByIDValidator(getBean(PatientDatabase.class)));
        beans.put(OrderingValidator.class, new OrderingValidator());
        beans.put(PagingValidator.class, new PagingValidator());
        beans.put(SearchPatientsRequestFieldValidator.class, new SearchPatientsRequestFieldValidator());
        beans.put(SearchPatientsValidator.class, new SearchPatientsValidator(
                getBean(SearchPatientsRequestFieldValidator.class),
                getBean(OrderingValidator.class), getBean(PagingValidator.class)));

        beans.put(AddPatientService.class, new AddPatientService(
                getBean(PatientDatabase.class), getBean(AddPatientValidator.class)));
        beans.put(DeletePatientService.class, new DeletePatientService(
                getBean(PatientDatabase.class), getBean(DeletePatientValidator.class)));
        beans.put(EditPatientService.class, new EditPatientService(
                getBean(PatientDatabase.class), getBean(EditPatientValidator.class)));
        beans.put(FindPatientByIdService.class, new FindPatientByIdService(
                getBean(PatientDatabase.class), getBean(FindPatientByIDValidator.class)));
        beans.put(SearchPatientsService.class, new SearchPatientsService(
                getBean(PatientDatabase.class), getBean(SearchPatientsValidator.class)));
        beans.put(ShowAllPatientsService.class, new ShowAllPatientsService(
                getBean(PatientDatabase.class)));

        beans.put(AddPatientUIAction.class, new AddPatientUIAction(getBean(AddPatientService.class)));
        beans.put(DeletePatientUIAction.class, new DeletePatientUIAction(getBean(DeletePatientService.class)));
        beans.put(EditPatientUIAction.class, new EditPatientUIAction(getBean(EditPatientService.class)));
        beans.put(FindPatientByIDUIAction.class, new FindPatientByIDUIAction(getBean(FindPatientByIdService.class)));
        beans.put(SearchPatientsUIAction.class, new SearchPatientsUIAction(getBean(SearchPatientsService.class)));
        beans.put(ShowAllPatientsUIAction.class, new ShowAllPatientsUIAction(getBean(ShowAllPatientsService.class)));
    }

    public <T extends Object> T getBean(Class c) {
        return (T) beans.get(c);
    }
}
