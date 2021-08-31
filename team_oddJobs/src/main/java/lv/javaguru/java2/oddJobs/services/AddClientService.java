package lv.javaguru.java2.oddJobs.services;

import lv.javaguru.java2.oddJobs.Client;
import lv.javaguru.java2.oddJobs.database.Database;

public class AddClientService {
    private Database database;

   public AddClientService (Database database){this.database=database;}

   public void execute (String clientName, String clientSurname){
Client client = new Client(clientName,clientSurname);
database.saveClient(client);

   }
}
