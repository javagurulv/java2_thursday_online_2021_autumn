package lv.javaguru.java2.qwe.core.services.data_services;

import lv.javaguru.java2.qwe.core.database.Database;
import lv.javaguru.java2.qwe.core.requests.data_requests.GetAllSecurityListRequest;
import lv.javaguru.java2.qwe.core.responses.data_responses.GetAllSecurityListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@Transactional
public class GetAllSecurityListService {

    @Autowired private Database database;

    public Database getDatabase() {
        return database;
    }

    public GetAllSecurityListResponse execute(GetAllSecurityListRequest request) {
        return new GetAllSecurityListResponse(database.getAllSecurityList());
    }

}