package lv.javaguru.java2.jg_entertainment.restaurant.core.responses.visitors;

import lv.javaguru.java2.jg_entertainment.restaurant.domain.Visitors;

import java.util.List;

public class SearchVisitorsResponse extends CoreResponse{

    private List<Visitors> visitors;

    public SearchVisitorsResponse(List<Visitors> visitors, List<CoreError> errorsList) {
        super(errorsList);
        this.visitors = visitors;
    }

    public List<Visitors> getVisitors() {
        return visitors;
    }
}
