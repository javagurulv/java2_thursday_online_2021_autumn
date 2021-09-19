package lv.javaguru.java2.qwe.core.responses.data_responses;

import lv.javaguru.java2.qwe.Security;
import lv.javaguru.java2.qwe.core.responses.CoreResponse;

import java.util.List;

public class GetAllSecurityListResponse extends CoreResponse {

    private final List<Security> list;

    public GetAllSecurityListResponse(List<Security> list) {
        this.list = list;
    }

    public List<Security> getList() {
        return list;
    }

}