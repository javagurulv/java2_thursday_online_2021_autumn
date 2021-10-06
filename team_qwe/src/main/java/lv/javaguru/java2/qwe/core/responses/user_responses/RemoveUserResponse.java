package lv.javaguru.java2.qwe.core.responses.user_responses;

import lv.javaguru.java2.qwe.core.responses.CoreError;
import lv.javaguru.java2.qwe.core.responses.CoreResponse;

import java.util.List;

public class RemoveUserResponse extends CoreResponse {

    private boolean isRemoved;

    public RemoveUserResponse(List<CoreError> errors) {
        super(errors);
    }

    public RemoveUserResponse(boolean isRemoved) {
        this.isRemoved = isRemoved;
    }

    public boolean isRemoved() {
        return isRemoved;
    }

}