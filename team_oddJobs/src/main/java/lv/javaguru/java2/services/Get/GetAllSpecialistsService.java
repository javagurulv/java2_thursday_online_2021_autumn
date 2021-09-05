package lv.javaguru.java2.services.Get;

import lv.javaguru.java2.Specialist;
import lv.javaguru.java2.database.Database;

import java.util.List;

public class GetAllSpecialistsService {

    private Database database;

    public GetAllSpecialistsService(Database database) {
        this.database = database;
    }

    public List<Specialist> execute() {
        return database.getAllSpecialist();
    }
}
