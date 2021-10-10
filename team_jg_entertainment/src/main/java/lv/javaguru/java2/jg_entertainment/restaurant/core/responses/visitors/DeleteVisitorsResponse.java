package lv.javaguru.java2.jg_entertainment.restaurant.core.responses.visitors;

import java.util.List;

public class DeleteVisitorsResponse extends CoreResponse{

    private boolean newVisitor;

    public DeleteVisitorsResponse(List<CoreError> errorsList) {
        super(errorsList);
    }

    public DeleteVisitorsResponse(boolean newVisitor) {
        this.newVisitor = newVisitor;
    }

    public boolean ifIdVisitorDelete() {
        return newVisitor;
    }
}
