package lv.javaguru.java2.jg_entertainment.restaurant.core.responses.visitors;

import lv.javaguru.java2.jg_entertainment.restaurant.domain.Visitor;

import java.util.List;

public class AddVisitorResponse extends CoreResponse {

    private Visitor newVisitor;

    public AddVisitorResponse(List<CoreError> errorsList) {
        super(errorsList);
    }

    public AddVisitorResponse(Visitor newVisitor){
        this.newVisitor = newVisitor;
    }

    public Visitor getNewVisitor() {
        return newVisitor;
    }
}
