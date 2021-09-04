package lv.javaguru.java2.jg_entertainment.restaurant.core.responses;

import lv.javaguru.java2.jg_entertainment.restaurant.core.services.Visitors;

import java.util.List;

public class ResponseDeleteVisitors extends CoreResponse{

    private Visitors newVisitor;

    public ResponseDeleteVisitors(List<CoreError> errorsList) {
        super(errorsList);
    }

    public ResponseDeleteVisitors(Visitors newVisitor) {
        this.newVisitor = newVisitor;
    }

    public Visitors getNewVisitor() {
        return newVisitor;
    }
}
