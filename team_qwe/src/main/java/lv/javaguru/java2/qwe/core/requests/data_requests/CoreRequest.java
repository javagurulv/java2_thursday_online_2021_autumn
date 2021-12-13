package lv.javaguru.java2.qwe.core.requests.data_requests;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = FilterStocksByIndustryRequest.class, name = "industry_request"),
        @JsonSubTypes.Type(value = FilterStocksByAnyDoubleParameterRequest.class, name = "double_request")
})
public abstract class CoreRequest {
}