package lv.javaguru.java2.jg_entertainment.restaurant.core.responses.visitors;

import lv.javaguru.java2.jg_entertainment.Visitors;

import java.util.List;

public class ResponseShowAllVisitors extends CoreResponse{
    private List<Visitors> newVisitor;


    public ResponseShowAllVisitors(List<CoreError> errorsList, List<Visitors> newVisitor) {
        super(errorsList);
        this.newVisitor = newVisitor;
    }

    public ResponseShowAllVisitors(List<Visitors> newVisitor) {
        this.newVisitor = newVisitor;
    }

    public List<Visitors> getNewVisitor() {
        return newVisitor;
    }
}
