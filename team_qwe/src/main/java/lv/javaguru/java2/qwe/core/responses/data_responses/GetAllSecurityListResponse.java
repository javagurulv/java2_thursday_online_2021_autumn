package lv.javaguru.java2.qwe.core.responses.data_responses;

import lv.javaguru.java2.qwe.Security;

import java.util.List;

public class GetAllSecurityListResponse {

    private final List<Security> list;

    public GetAllSecurityListResponse(List<Security> list) {
        this.list = list;
    }

    public List<Security> getList() {
        return list;
    }

}