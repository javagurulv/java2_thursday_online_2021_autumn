package lv.javaguru.java2.qwe.core.services.data_services;

import lv.javaguru.java2.qwe.core.database.Database;
import lv.javaguru.java2.qwe.core.requests.ShowListRequest;
import lv.javaguru.java2.qwe.core.responses.ShowListResponse;

public class ShowListService {

    private final Database database;

    public ShowListService(Database database) {
        this.database = database;
    }

    public Database getDatabase() {
        return database;
    }

    public ShowListResponse execute(ShowListRequest request) {
        return new ShowListResponse(database.showListOfSecurities());
    }

}