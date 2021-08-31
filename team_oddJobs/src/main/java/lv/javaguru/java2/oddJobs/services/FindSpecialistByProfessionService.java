package lv.javaguru.java2.oddJobs.services;

import lv.javaguru.java2.oddJobs.database.Database;


public class FindSpecialistByProfessionService {
   private Database database;

   public FindSpecialistByProfessionService(Database database){this.database=database;}

   public void execute(String profession){
       database.findSpecialistByProfession(profession);

   }
}
