package lv.javaguru.java2.jg_entertainment.restaurant.core.responses.visitors;

import lv.javaguru.java2.jg_entertainment.restaurant.domain.Visitor;

import java.util.List;

public class SearchVisitorsResponse extends CoreResponse{

    private List<Visitor> visitors;

    public SearchVisitorsResponse(List<Visitor> visitors, List<CoreError> errorsList) {
        super(errorsList);
        this.visitors = visitors;
    }

    public List<Visitor> getVisitors() {
        return visitors;
    }
}
