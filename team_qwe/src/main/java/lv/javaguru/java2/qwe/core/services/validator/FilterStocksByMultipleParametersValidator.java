package lv.javaguru.java2.qwe.core.services.validator;

import lv.javaguru.java2.qwe.core.requests.data_requests.*;
import lv.javaguru.java2.qwe.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FilterStocksByMultipleParametersValidator {

    public List<CoreError> validate(FilterStocksByMultipleParametersRequest request) {
        List<CoreError> errorList = new ArrayList<>();
        List<CoreRequest> requestList = request.getRequestList();
        for (int i = 0; i < request.getRequestList().size(); i++) {
            switch (requestList.get(i).getClass().getSimpleName()) {
                case "FilterStocksByAnyDoubleParameterRequest" ->
                        errorList.addAll(new FilterStocksByAnyDoubleParameterValidator().validate(requestList.get(i)));
                case "FilterStocksByIndustryRequest" ->
                        errorList.addAll(new FilterStockByIndustryValidator().validate(requestList.get(i)));
                case "PagingRequest" ->
                        errorList.addAll(new PagingRequestValidator().validate(requestList.get(i)));
                default ->
                        errorList.addAll(new OrderingRequestValidator().validate(requestList.get(i)));
            }
        }
        return errorList;
    }

}