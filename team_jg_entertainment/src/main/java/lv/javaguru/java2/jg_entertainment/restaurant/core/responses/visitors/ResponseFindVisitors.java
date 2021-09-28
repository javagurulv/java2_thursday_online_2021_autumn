package lv.javaguru.java2.jg_entertainment.restaurant.core.responses.visitors;

import lv.javaguru.java2.jg_entertainment.restaurant.domain.Visitors;

import java.util.List;

public class ResponseFindVisitors extends CoreResponse{

    private List<Visitors> visitors;

    public ResponseFindVisitors(List<CoreError> errorsList, List<Visitors> visitors) {
        super(errorsList);
        this.visitors = visitors;
    }

    public List<Visitors> getVisitors() {
        return visitors;
    }
}
