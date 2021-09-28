package lv.javaguru.java2.jg_entertainment.restaurant.core.responses.visitors;

import lv.javaguru.java2.jg_entertainment.restaurant.domain.Visitors;

import java.util.List;

public class ResponseSearchVisitors extends CoreResponse{

    private List<Visitors> visitors;

    public ResponseSearchVisitors(List<Visitors> visitors, List<CoreError> errorsList) {
        super(errorsList);
        this.visitors = visitors;
    }

    public List<Visitors> getVisitors() {
        return visitors;
    }
}
