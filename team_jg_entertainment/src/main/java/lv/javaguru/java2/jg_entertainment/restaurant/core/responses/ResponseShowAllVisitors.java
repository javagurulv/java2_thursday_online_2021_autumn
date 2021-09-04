package lv.javaguru.java2.jg_entertainment.restaurant.core.responses;

import lv.javaguru.java2.jg_entertainment.restaurant.core.services.Visitors;

import java.util.List;

public class ResponseShowAllVisitors extends CoreResponse{
    private Visitors newVisitor;


    public ResponseShowAllVisitors(List<CoreError> errorsList) {
        super(errorsList);
    }

    public ResponseShowAllVisitors(Visitors newVisitor) {
        this.newVisitor = newVisitor;
    }

    public Visitors getNewVisitor() {
        return newVisitor;
    }
}
