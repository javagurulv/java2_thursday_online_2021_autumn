package lv.javaguru.java2.qwe.core.services.validator;

import lv.javaguru.java2.qwe.core.requests.data_requests.*;
import lv.javaguru.java2.qwe.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import static java.util.Map.entry;

@Component
public class FilterStocksByMultipleParametersValidator {

    private final Map<Predicate<FilterStocksByMultipleParametersRequest>, CoreError> validator = Map.ofEntries(
            entry(request -> request.getRequestList().isEmpty(),
                    new CoreError("Choose parameter", "at least one parameter is required!")),
            entry(request -> request.getMarketPriceTarget() < 0,
                    new CoreError("Market price target", "cannot be negative!")),
            entry(request -> request.getDividendTarget() < 0,
                    new CoreError("Dividend target", "cannot be negative!")),
            entry(request -> request.getRiskWeightTarget() < 0,
                    new CoreError("Risk weight target", "cannot be negative!")),
            entry(request -> request.getIndustryTarget() != null && request.getIndustryTarget().isEmpty(),
                    new CoreError("Industry target", "must not be empty!")),
            entry(request -> request.getOrderBy().isEmpty() && !request.getOrderDirection().isEmpty(),
                    new CoreError("Ordering", "both fields must be empty or filled!")),
            entry(request -> !request.getOrderBy().isEmpty() && request.getOrderDirection().isEmpty(),
                    new CoreError("Ordering", "both fields must be empty or filled!")),
            entry(request -> request.getPageNumber() == 0 && request.getPageSize() != 0,
                    new CoreError("Paging", "both fields must be empty or filled!")),
            entry(request -> request.getPageNumber() != 0 && request.getPageSize() == 0,
                    new CoreError("Paging", "both fields must be empty or filled!")),
            entry(request -> request.getPageNumber() < 0 || request.getPageSize() < 0,
                    new CoreError("Paging", "cannot be negative!"))
    );

    public List<CoreError> validate(FilterStocksByMultipleParametersRequest request) {
        List<CoreError> errorList = new ArrayList<>();
        List<CoreRequest> requestList = request.getRequestList();
        for (int i = 0; i < request.getRequestList().size(); i++) {
            if (requestList.get(i).getClass().getSimpleName().equals("FilterStocksByAnyDoubleParameterRequest")) {
                errorList.addAll(new FilterStocksByAnyDoubleParameterValidator().validate(requestList.get(i)));
            }
            else if (requestList.get(i).getClass().getSimpleName().equals("FilterStocksByIndustryRequest")) {
                errorList.addAll(new FilterStockByIndustryValidator().validate(requestList.get(i)));
            }
            else {
                errorList.addAll(new OrderingRequestValidator().validate(requestList.get(i)));
            }
        }
        return errorList;
    }

}