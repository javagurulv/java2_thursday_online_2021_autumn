package lv.javaguru.java2.services.Get;

import lv.javaguru.java2.Specialist;
import lv.javaguru.java2.core.requests.Get.GetAllSpecialistRequest;
import lv.javaguru.java2.core.responce.Get.GetAllSpecialistsResponse;
import lv.javaguru.java2.database.Database;
import lv.javaguru.java2.dependency_injection.DIComponent;
import lv.javaguru.java2.dependency_injection.DIDependency;

import java.util.List;
@DIComponent
public class GetAllSpecialistsService {

    @DIDependency
    private Database database;

    public GetAllSpecialistsResponse execute(GetAllSpecialistRequest request) {
        List<Specialist> specialists = database.getAllSpecialist();
        return new GetAllSpecialistsResponse(specialists);
    }
}

