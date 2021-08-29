package lv.javaguru.java2.oddJobs.services;

import lv.javaguru.java2.oddJobs.Specialist;
import lv.javaguru.java2.oddJobs.database.Database;

import java.util.List;

public class GetAllSpecialistsService {

    private Database database;

    public GetAllSpecialistsService(Database database){this.database=database;}

    public List<Specialist> execute(){
       return database.getAllSpecialist();
    }
}
