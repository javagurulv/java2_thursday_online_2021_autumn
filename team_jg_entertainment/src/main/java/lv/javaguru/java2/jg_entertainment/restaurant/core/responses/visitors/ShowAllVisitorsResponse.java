package lv.javaguru.java2.jg_entertainment.restaurant.core.responses.visitors;

import lv.javaguru.java2.jg_entertainment.restaurant.domain.Visitors;

import java.util.List;

public class ShowAllVisitorsResponse extends CoreResponse{
    private List<Visitors> newVisitor;


    public ShowAllVisitorsResponse(List<CoreError> errorsList, List<Visitors> newVisitor) {
        super(errorsList);
        this.newVisitor = newVisitor;
    }

    public ShowAllVisitorsResponse(List<Visitors> newVisitor) {
        this.newVisitor = newVisitor;
    }

    public List<Visitors> getNewVisitor() {
        return newVisitor;
    }
}
