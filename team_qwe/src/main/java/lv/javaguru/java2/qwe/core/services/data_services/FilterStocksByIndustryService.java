package lv.javaguru.java2.qwe.core.services.data_services;

import lv.javaguru.java2.qwe.core.database.Database;
import lv.javaguru.java2.qwe.core.requests.FilterStockByIndustryRequest;
import lv.javaguru.java2.qwe.core.responses.FilterStockByIndustryResponse;

public class FilterStocksByIndustryService {

    private final Database database;

    public FilterStocksByIndustryService(Database database) {
        this.database = database;
    }

    public Database getDatabase() {
        return database;
    }

    public FilterStockByIndustryResponse execute(FilterStockByIndustryRequest request) {
        return new FilterStockByIndustryResponse(database.filterStocksByIndustry(request.getIndustry()));
    }

}