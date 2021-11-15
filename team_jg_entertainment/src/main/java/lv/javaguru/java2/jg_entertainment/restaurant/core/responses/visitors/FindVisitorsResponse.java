package lv.javaguru.java2.jg_entertainment.restaurant.core.responses.visitors;

import lv.javaguru.java2.jg_entertainment.restaurant.domain.Visitor;

import java.util.List;

public class FindVisitorsResponse extends CoreResponse{

    private List<Visitor> visitors;

    public FindVisitorsResponse(List<CoreError> errorsList, List<Visitor> visitors) {
        super(errorsList);
        this.visitors = visitors;
    }

    public List<Visitor> getVisitors() {
        return visitors;
    }
}
