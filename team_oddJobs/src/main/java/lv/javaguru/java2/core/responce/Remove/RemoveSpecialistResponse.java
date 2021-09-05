package lv.javaguru.java2.core.responce.Remove;

public class RemoveSpecialistResponse {
    private boolean specialistRemoved;

    public RemoveSpecialistResponse(boolean specialistRemoved) {
        this.specialistRemoved = specialistRemoved;
    }

    public boolean isSpecialistRemoved() {
        return specialistRemoved;
    }
}
