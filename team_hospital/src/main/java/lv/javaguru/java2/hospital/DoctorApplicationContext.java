package lv.javaguru.java2.hospital;

import lv.javaguru.java2.hospital.database.DoctorDatabase;
import lv.javaguru.java2.hospital.database.DoctorDatabaseImpl;
import lv.javaguru.java2.hospital.doctor.console_ui.*;
import lv.javaguru.java2.hospital.doctor.core.services.*;
import lv.javaguru.java2.hospital.doctor.core.services.validators.*;

import java.util.HashMap;
import java.util.Map;

public class DoctorApplicationContext {

    private Map<Class, Object> beans = new HashMap<>();

    public DoctorApplicationContext() {
        beans.put(DoctorDatabase.class, new DoctorDatabaseImpl());

        beans.put(AddDoctorRequestValidator.class, new AddDoctorRequestValidator());
        beans.put(DeleteDoctorRequestValidator.class, new DeleteDoctorRequestValidator());
        beans.put(EditDoctorRequestValidator.class, new EditDoctorRequestValidator());
        beans.put(SearchDoctorsRequestFieldValidator.class, new SearchDoctorsRequestFieldValidator());
        beans.put(OrderingValidator.class, new OrderingValidator());
        beans.put(PagingValidator.class, new PagingValidator());
        beans.put(SearchDoctorsRequestValidator.class, new SearchDoctorsRequestValidator(
                getBean(SearchDoctorsRequestFieldValidator.class),
                getBean(OrderingValidator.class),
                getBean(PagingValidator.class)));

        beans.put(AddDoctorService.class, new AddDoctorService(
                getBean(DoctorDatabase.class),
                getBean(AddDoctorRequestValidator.class)));
        beans.put(ShowAllDoctorsService.class, new ShowAllDoctorsService(getBean(DoctorDatabase.class)));
        beans.put(DeleteDoctorService.class, new DeleteDoctorService(
                getBean(DoctorDatabase.class),
                getBean(DeleteDoctorRequestValidator.class)));
        beans.put(EditDoctorService.class, new EditDoctorService(
                getBean(DoctorDatabase.class),
                getBean(EditDoctorRequestValidator.class)));
        beans.put(SearchDoctorsService.class, new SearchDoctorsService(
                getBean(DoctorDatabase.class),
                getBean(SearchDoctorsRequestValidator.class)));
        beans.put(DoctorExistsService.class, new DoctorExistsService(
                getBean(DoctorDatabase.class)));

        beans.put(AddDoctorUIAction.class, new AddDoctorUIAction(getBean(AddDoctorService.class)));
        beans.put(ShowAllDoctorsUIAction.class, new ShowAllDoctorsUIAction(getBean(ShowAllDoctorsService.class)));
        beans.put(DeleteDoctorUIAction.class, new DeleteDoctorUIAction(
                getBean(DeleteDoctorService.class),
                getBean(DoctorExistsService.class)));
        beans.put(EditDoctorUIAction.class, new EditDoctorUIAction(
                getBean(EditDoctorService.class),
                getBean(DoctorExistsService.class)));
        beans.put(SearchDoctorsUIAction.class, new SearchDoctorsUIAction(getBean(SearchDoctorsService.class)));
        beans.put(ExitUIAction.class, new ExitUIAction());
    }

    public <T extends Object> T getBean(Class c) {
        return (T) beans.get(c);
    }
}
