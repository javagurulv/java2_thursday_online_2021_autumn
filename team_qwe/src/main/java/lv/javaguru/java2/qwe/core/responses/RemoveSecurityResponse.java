package lv.javaguru.java2.qwe.core.responses;

public class RemoveSecurityResponse {

    private final boolean isRemoved;

    public RemoveSecurityResponse(boolean isRemoved) {
        this.isRemoved = isRemoved;
    }

    public boolean isRemoved() {
        return isRemoved;
    }

}