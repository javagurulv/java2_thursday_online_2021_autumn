package lv.javaguru.java2.jg_entertainment.restaurant.core.responses.visitors;

import java.util.List;

public class ResponseDeleteVisitors extends CoreResponse{

    private boolean newVisitor;

    public ResponseDeleteVisitors(List<CoreError> errorsList) {
        super(errorsList);
    }

    public ResponseDeleteVisitors(boolean newVisitor) {
        this.newVisitor = newVisitor;
    }

    public boolean ifIdVisitorDelete() {
        return newVisitor;
    }
}
