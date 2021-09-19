package lv.javaguru.java2.qwe.core.services.data_services;

import lv.javaguru.java2.qwe.core.database.Database;
import lv.javaguru.java2.qwe.core.requests.data_requests.GetAllSecurityListRequest;
import lv.javaguru.java2.qwe.core.responses.data_responses.GetAllSecurityListResponse;

public class GetAllSecurityListService {

    private final Database database;

    public GetAllSecurityListService(Database database) {
        this.database = database;
    }

    public Database getDatabase() {
        return database;
    }

    public GetAllSecurityListResponse execute(GetAllSecurityListRequest request) {
        return new GetAllSecurityListResponse(database.getAllSecurityList());
    }

}