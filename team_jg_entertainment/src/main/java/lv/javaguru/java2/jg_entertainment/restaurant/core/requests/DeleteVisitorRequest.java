package lv.javaguru.java2.jg_entertainment.restaurant.core.requests;

public class DeleteVisitorRequest {

    private Long idVisitor;
    private String name;

    public DeleteVisitorRequest(String name, Long idVisitor) {
        this.name = name;
        this.idVisitor = idVisitor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getIdVisitor() {
        return idVisitor;
    }

    public void setIdVisitor(Long idVisitor) {
        this.idVisitor = idVisitor;
    }
}
