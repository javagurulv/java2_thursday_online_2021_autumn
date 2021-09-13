package lv.javaguru.java2.qwe.core.services.data_services;

import lv.javaguru.java2.qwe.core.database.Database;
import lv.javaguru.java2.qwe.core.requests.data_requests.RemoveSecurityRequest;
import lv.javaguru.java2.qwe.core.responses.data_responses.RemoveSecurityResponse;

public class RemoveSecurityService {

    private final Database database;

    public RemoveSecurityService(Database database) {
        this.database = database;
    }

    public RemoveSecurityResponse execute(RemoveSecurityRequest request) {
        return new RemoveSecurityResponse(database.removeSecurity(request.getName()));
    }

}