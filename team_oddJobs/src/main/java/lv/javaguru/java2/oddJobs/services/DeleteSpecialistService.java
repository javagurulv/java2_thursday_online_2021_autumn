package lv.javaguru.java2.oddJobs.services;

import lv.javaguru.java2.oddJobs.database.Database;


public class DeleteSpecialistService {
    private Database database;

    public DeleteSpecialistService(Database database) {
        this.database = database;
    }

    public void execute(Long specialistId) {
        database.deleteSpecialistById(specialistId);
    }
}
