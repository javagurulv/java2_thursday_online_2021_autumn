package lv.javaguru.java2.qwe.core.services.data_services;

import lv.javaguru.java2.qwe.Security;
import lv.javaguru.java2.qwe.core.database.Database;
import lv.javaguru.java2.qwe.core.responses.ShowListResponse;

import java.util.List;

public class ShowListService {

    private final Database database;

    public ShowListService(Database database) {
        this.database = database;
    }

    public Database getDatabase() {
        return database;
    }

    public ShowListResponse execute() {
        return new ShowListResponse(database.showListOfSecurities());
    }

}