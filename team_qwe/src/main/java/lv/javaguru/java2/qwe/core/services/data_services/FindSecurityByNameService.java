package lv.javaguru.java2.qwe.core.services.data_services;

import lv.javaguru.java2.qwe.Security;
import lv.javaguru.java2.qwe.core.database.Database;
import lv.javaguru.java2.qwe.core.requests.FindSecurityByNameRequest;
import lv.javaguru.java2.qwe.core.responses.FindSecurityByNameResponse;

import java.util.Optional;

public class FindSecurityByNameService {

    private final Database database;

    public FindSecurityByNameService(Database database) {
        this.database = database;
    }

    public FindSecurityByNameResponse execute(FindSecurityByNameRequest request) {
        Optional<Security> security = database.findSecurityByName(request.getName());
        return security.map(FindSecurityByNameResponse::new)
                .orElseGet(FindSecurityByNameResponse::new);
    }

}