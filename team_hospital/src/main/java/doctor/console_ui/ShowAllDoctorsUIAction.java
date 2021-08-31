package doctor.console_ui;

import doctor.services.ShowAllDoctorsService;

public class ShowAllDoctorsUIAction implements DoctorUIActions {

    private final ShowAllDoctorsService showAllDoctors;

    public ShowAllDoctorsUIAction(ShowAllDoctorsService showAllDoctors) {
        this.showAllDoctors = showAllDoctors;
    }

    @Override
    public void execute() {
        System.out.println(showAllDoctors.execute());
    }
}
