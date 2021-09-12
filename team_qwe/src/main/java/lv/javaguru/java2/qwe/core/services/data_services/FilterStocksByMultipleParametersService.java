package lv.javaguru.java2.qwe.core.services.data_services;

import lv.javaguru.java2.qwe.Security;
import lv.javaguru.java2.qwe.core.database.Database;
import lv.javaguru.java2.qwe.core.requests.FilterStockByMultipleParametersRequest;

import java.util.List;

public class FilterStocksByMultipleParametersService {

    private final Database database;

    public FilterStocksByMultipleParametersService(Database database) {
        this.database = database;
    }

    public Database getDatabase() {
        return database;
    }

    public List<Security> execute(FilterStockByMultipleParametersRequest request) {
        return database.filterStocksByMultipleParameters(database.getSecurityList(), request, 0);
    }

/*    private List<Security> filterNext(List<Security> list, AddMultiFilterRequest request, int i) {
        List<Security> nextList = list;
        nextList = nextList.stream()
                .filter(security -> security.getClass().getSimpleName().equals("Stock"))
                .filter(request.getList().get(i))
                .collect(Collectors.toList());
        i++;
        if (i == request.getList().size()) {
            return nextList;
        }
        return filterNext(nextList, request, i);
    }*/

}