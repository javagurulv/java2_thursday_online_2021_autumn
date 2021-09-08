package lv.javaguru.java2.qwe.core.services.data_services;

import lv.javaguru.java2.qwe.Security;
import lv.javaguru.java2.qwe.core.database.Database;

import java.util.List;

public class FilterStocksByIndustryService {

    private final Database database;

    public FilterStocksByIndustryService(Database database) {
        this.database = database;
    }

    public Database getDatabase() {
        return database;
    }

    public List<Security> execute(String industry) {
        return database.filterStocksByIndustry(industry);
    }

}