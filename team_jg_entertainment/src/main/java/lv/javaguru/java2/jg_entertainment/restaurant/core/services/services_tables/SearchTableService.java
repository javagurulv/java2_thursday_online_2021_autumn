package lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_tables;

import lv.javaguru.java2.jg_entertainment.restaurant.domain.Table;
import lv.javaguru.java2.jg_entertainment.restaurant.core.database.TableDatabase;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.tables.OrderingTable;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.tables.PagingTable;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.tables.SearchTableRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.tables.CoreError;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.tables.SearchTableResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators.ValidatorSearchRequestTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SearchTableService {

    @Value("${search.ordering.enabled}")
    private boolean orderingEnabled;

    @Value("${search.paging.enabled}")
    private boolean pagingEnabled;

    @Autowired
    private TableDatabase database;
    @Autowired private ValidatorSearchRequestTable validator;

    public SearchTableResponse execute(SearchTableRequest request) {

        List<CoreError> coreErrors = validator.validator(request);
        if (!coreErrors.isEmpty()) {
            return new SearchTableResponse(coreErrors, null);
        }
        List<Table> tableList = search(request);
        tableList = order(tableList, request.getOrdering());
        tableList = paging(tableList, request.getPaging());
        return new SearchTableResponse(null, tableList);
    }

    private List<Table> order(List<Table> tableList, OrderingTable ordering) {
        if (ordering != null) {
            Comparator<Table> comparator = ordering.getOrderBy().equals("title")
                    ? Comparator.comparing(Table::getTitle)
                    : Comparator.comparing(Table::getId);
            if (ordering.getOrderDirection().equals("DESCENDING")) {
                comparator = comparator.reversed();
            }
            return tableList.stream()
                    .sorted(comparator)
                    .collect(Collectors.toList());
        } else {
            return tableList;
        }
    }

    private List<Table> search(SearchTableRequest request) {
        List<Table> tables = new ArrayList<>();
        if (request.isTitleProvided()
                && !request.isIDProvided()) {
            tables = database.findByTitleTable(request.getTitleTable());
        }
        if (!request.isTitleProvided()
                && request.isIDProvided()) {
            tables = database.findTabletById(request.getIdTable());
        }
        if (request.isTitleProvided() && request.isIDProvided()) {
            tables = database.findByIdAndTitleTable(request.getIdTable(), request.getTitleTable());
        }
        return tables;
    }

    private List<Table> paging(List<Table> tables, PagingTable paging) {
        if (paging != null) {
            int skip = (paging.getPageNumber() - 1) * paging.getPageSize();
            return tables.stream()
                    .skip(skip)
                    .limit(paging.getPageSize())
                    .collect(Collectors.toList());
        } else {
            return tables;
        }
    }
}