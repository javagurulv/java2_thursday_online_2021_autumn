package lv.javaguru.java2.qwe.core.responses.data_responses;

import lv.javaguru.java2.qwe.core.responses.CoreResponse;

public class RemoveSecurityResponse extends CoreResponse {

    private final boolean isRemoved;

    public RemoveSecurityResponse(boolean isRemoved) {
        this.isRemoved = isRemoved;
    }

    public boolean isRemoved() {
        return isRemoved;
    }

}