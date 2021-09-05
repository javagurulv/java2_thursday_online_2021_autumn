package lv.javaguru.java2.services.Add;


import lv.javaguru.java2.Specialist;
import lv.javaguru.java2.core.requests.Add.AddSpecialistRequest;
import lv.javaguru.java2.core.responce.Add.AddSpecialistResponse;
import lv.javaguru.java2.database.Database;

public class AddSpecialistService {
    private Database database;

    public AddSpecialistService(Database database) {
        this.database = database;
    }

    public AddSpecialistResponse execute(AddSpecialistRequest request) {
        Specialist specialist = new Specialist(request.getName(),request.getSurname(),request.getProfession());
        database.addSpecialist(specialist);
        return new AddSpecialistResponse(specialist);

    }

    @Override
    public String toString() {
        return "AddSpecialistService{" +
                "database=" + database +
                '}';
    }
}
