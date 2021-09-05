package lv.javaguru.java2.services.Find;


import lv.javaguru.java2.database.Database;

public class FindSpecialistByProfessionService {
    private Database database;

    public FindSpecialistByProfessionService(Database database) {
        this.database = database;
    }

    public void execute(String profession) {
        database.findSpecialistByProfession(profession);

    }
}
