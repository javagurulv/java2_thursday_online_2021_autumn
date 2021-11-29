package lv.javaguru.java2.hospital.visit.core.requests;

public class DeleteVisitRequest {
    private String ID;

    public DeleteVisitRequest(String ID) {
        this.ID = ID;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
