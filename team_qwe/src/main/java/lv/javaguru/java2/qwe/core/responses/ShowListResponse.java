package lv.javaguru.java2.qwe.core.responses;

import lv.javaguru.java2.qwe.Security;

import java.util.List;

public class ShowListResponse {

    private final List<Security> list;

    public ShowListResponse(List<Security> list) {
        this.list = list;
    }

    public List<Security> getList() {
        return list;
    }

}