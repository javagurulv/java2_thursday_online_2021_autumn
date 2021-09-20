package lv.javaguru.java2.jg_entertainment.restaurant.core.responses.visitors;

import lv.javaguru.java2.jg_entertainment.Visitors;

import java.util.List;

public class ResponseAddVisitor extends CoreResponse {

    private Visitors newVisitor;

    public ResponseAddVisitor(List<CoreError> errorsList) {
        super(errorsList);
    }

    public ResponseAddVisitor(Visitors newVisitor){
        this.newVisitor = newVisitor;
    }

    public Visitors getNewVisitor() {
        return newVisitor;
    }
}
