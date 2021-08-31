package lv.javaguru.java2.oddJobs.services;

import lv.javaguru.java2.oddJobs.Specialist;
import lv.javaguru.java2.oddJobs.database.Database;

public class AddSpecialistService {
    private Database database;

    public AddSpecialistService(Database database) {
        this.database = database;
    }

    public void execute(String specialistName, String specialistSurname, String profession) {
        Specialist specialist = new Specialist(specialistName, specialistSurname, profession);
        database.saveSpecialist(specialist);

    }
}
