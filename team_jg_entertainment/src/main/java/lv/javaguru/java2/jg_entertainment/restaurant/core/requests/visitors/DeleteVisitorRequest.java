package lv.javaguru.java2.jg_entertainment.restaurant.core.requests.visitors;

public class DeleteVisitorRequest {

    private Long idVisitor;
    private String nameVisitors;

    public DeleteVisitorRequest(Long idVisitor, String nameVisitors) {
        this.idVisitor = idVisitor;
        this.nameVisitors = nameVisitors;
    }

    public Long getIdVisitor() {
        return idVisitor;
    }

    public String getNameVisitors() {
        return nameVisitors;
    }
}
