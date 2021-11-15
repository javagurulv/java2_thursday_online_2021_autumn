package lv.javaguru.java2.jg_entertainment.restaurant.core.responses.visitors;

import lv.javaguru.java2.jg_entertainment.restaurant.domain.Visitor;

import java.util.List;

public class ShowAllVisitorsResponse extends CoreResponse{
    private List<Visitor> newVisitor;


    public ShowAllVisitorsResponse(List<CoreError> errorsList, List<Visitor> newVisitor) {
        super(errorsList);
        this.newVisitor = newVisitor;
    }

    public ShowAllVisitorsResponse(List<Visitor> newVisitor) {
        this.newVisitor = newVisitor;
    }

    public List<Visitor> getNewVisitor() {
        return newVisitor;
    }
}
