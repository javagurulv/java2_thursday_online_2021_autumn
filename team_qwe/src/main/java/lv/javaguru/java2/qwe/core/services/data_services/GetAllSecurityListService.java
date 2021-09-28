package lv.javaguru.java2.qwe.core.services.data_services;

import lv.javaguru.java2.qwe.core.database.Database;
import lv.javaguru.java2.qwe.core.requests.data_requests.GetAllSecurityListRequest;
import lv.javaguru.java2.qwe.core.responses.data_responses.GetAllSecurityListResponse;
import lv.javaguru.java2.qwe.dependency_injection.DIComponent;
import lv.javaguru.java2.qwe.dependency_injection.DIDependency;

@DIComponent
public class GetAllSecurityListService {

    @DIDependency private Database database;

    public Database getDatabase() {
        return database;
    }

    public GetAllSecurityListResponse execute(GetAllSecurityListRequest request) {
        return new GetAllSecurityListResponse(database.getAllSecurityList());
    }

}