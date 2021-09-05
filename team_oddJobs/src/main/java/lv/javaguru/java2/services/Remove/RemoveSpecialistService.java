package lv.javaguru.java2.services.Remove;


import lv.javaguru.java2.core.requests.Remove.RemoveSpecialistRequest;
import lv.javaguru.java2.core.responce.Remove.RemoveSpecialistResponse;
import lv.javaguru.java2.database.Database;

public class RemoveSpecialistService {
    private Database database;

    public RemoveSpecialistService(Database database) {
        this.database = database;
    }

    public RemoveSpecialistResponse execute(RemoveSpecialistRequest request) {
        boolean isSpecialistRemoved = database.removeSpecialist(request.getSpecialistId(), request.getSpecialistName(), request.getSpecialistSurname());
        return new RemoveSpecialistResponse(isSpecialistRemoved);
    }
}
