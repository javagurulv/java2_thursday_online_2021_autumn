package lv.javaguru.java2.qwe.core.services.data_services;

import lv.javaguru.java2.qwe.Security;
import lv.javaguru.java2.qwe.core.database.Database;

import java.util.List;

public class FilterStocksByAnyDoubleParameterService {

    private final Database database;

    public FilterStocksByAnyDoubleParameterService(Database database) {
        this.database = database;
    }

    public Database getDatabase() {
        return database;
    }

    public List<Security> execute(String parameter, String operator, double target) {
        return database.filterStocksByAnyDoubleParameter(parameter, operator, target);
    }

}