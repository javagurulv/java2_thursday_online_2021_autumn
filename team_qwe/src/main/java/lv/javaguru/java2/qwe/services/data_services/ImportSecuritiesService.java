package lv.javaguru.java2.qwe.services.data_services;

import lv.javaguru.java2.qwe.database.Database;

import java.io.IOException;

public class ImportSecuritiesService {

    private final Database database;

    public ImportSecuritiesService(Database database) {
        this.database = database;
    }

    public void execute(String path) throws IOException {
        database.importSecurities(path);
    }

}