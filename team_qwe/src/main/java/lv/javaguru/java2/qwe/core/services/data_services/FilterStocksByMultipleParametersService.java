package lv.javaguru.java2.qwe.core.services.data_services;

import lv.javaguru.java2.qwe.core.domain.Security;
import lv.javaguru.java2.qwe.core.database.Database;
import lv.javaguru.java2.qwe.core.requests.data_requests.*;
import lv.javaguru.java2.qwe.core.responses.CoreError;
import lv.javaguru.java2.qwe.core.responses.data_responses.FilterStocksByMultipleParametersResponse;
import lv.javaguru.java2.qwe.core.services.validator.FilterStocksByMultipleParametersValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FilterStocksByMultipleParametersService {

    @Autowired private Database database;
    @Autowired private FilterStocksByMultipleParametersValidator validator;

    public Database getDatabase() {
        return database;
    }

    public FilterStocksByMultipleParametersResponse execute(FilterStocksByMultipleParametersRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (errors.isEmpty()) {
            return new FilterStocksByMultipleParametersResponse(
                    database.filterStocksByMultipleParameters(constructQuery(request)));
        }
        List<Security> emptyList = new ArrayList<>();
        return new FilterStocksByMultipleParametersResponse(errors, emptyList);
    }

    static private String constructQuery(FilterStocksByMultipleParametersRequest request) {

        StringBuilder query = new StringBuilder("SELECT * FROM stocks");
        List<CoreRequest> requestList = request.getRequestList();

        if (requestList.size() > 0) {
            query.append("\n  WHERE");
            for (int i = 0; i < requestList.size(); i++) {
                if (i != 0 && !requestList.get(i).getClass().getSimpleName().equals("OrderingRequest")) {
                    query.append("  AND");
                }
                if (requestList.get(i).getClass().getSimpleName().equals("OrderingRequest")) {
                    query.append(" ORDER BY ").append(((OrderingRequest) requestList.get(i)).getOrderBy())
                            .append(" ").append(((OrderingRequest) requestList.get(i)).getOrderDirection());
                }
                if (requestList.get(i).getClass().getSimpleName().equals("FilterStocksByIndustryRequest")) {
                    query.append(" industry = ").append("'")
                            .append(((FilterStocksByIndustryRequest) requestList.get(i)).getIndustry())
                            .append("'").append("\n");
                }
                if (requestList.get(i).getClass().getSimpleName().equals("FilterStocksByAnyDoubleParameterRequest")) {
                    query.append(" ").append(((FilterStocksByAnyDoubleParameterRequest) requestList.get(i)).getParameter())
                            .append(" ").append(((FilterStocksByAnyDoubleParameterRequest) requestList.get(i)).getOperator())
                            .append(" ").append(((FilterStocksByAnyDoubleParameterRequest) requestList.get(i)).getTargetAmount()).append("\n");
                }
            }
        }
        System.out.println("====================================\n" + query + "\n====================================");
        return query.toString();
    }

}