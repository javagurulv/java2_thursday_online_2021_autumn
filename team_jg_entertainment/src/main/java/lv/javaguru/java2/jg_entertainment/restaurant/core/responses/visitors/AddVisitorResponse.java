package lv.javaguru.java2.jg_entertainment.restaurant.core.responses.visitors;

import lv.javaguru.java2.jg_entertainment.restaurant.domain.Visitors;

import java.util.List;

public class AddVisitorResponse extends CoreResponse {

    private Visitors newVisitor;

    public AddVisitorResponse(List<CoreError> errorsList) {
        super(errorsList);
    }

    public AddVisitorResponse(Visitors newVisitor){
        this.newVisitor = newVisitor;
    }

    public Visitors getNewVisitor() {
        return newVisitor;
    }
}
