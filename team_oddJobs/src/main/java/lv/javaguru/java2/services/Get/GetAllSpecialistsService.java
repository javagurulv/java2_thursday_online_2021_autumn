package lv.javaguru.java2.services.Get;

import lv.javaguru.java2.Specialist;
import lv.javaguru.java2.core.requests.Get.GetAllSpecialistRequest;
import lv.javaguru.java2.core.responce.Get.GetAllSpecialistsResponse;
import lv.javaguru.java2.database.Database;

import java.util.List;

public class GetAllSpecialistsService {

    private Database database;

    public GetAllSpecialistsService(Database database) {
        this.database = database;
    }

    public GetAllSpecialistsResponse execute(GetAllSpecialistRequest request) {
        List<Specialist> specialists = database.getAllSpecialist();
        return new GetAllSpecialistsResponse(specialists);
    }
}

