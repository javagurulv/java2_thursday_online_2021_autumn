package lv.javaguru.java2.qwe.core.responses.user_responses;

public class RemoveUserResponse {

    private final boolean isRemoved;

    public RemoveUserResponse(boolean isRemoved) {
        this.isRemoved = isRemoved;
    }

    public boolean isRemoved() {
        return isRemoved;
    }

}