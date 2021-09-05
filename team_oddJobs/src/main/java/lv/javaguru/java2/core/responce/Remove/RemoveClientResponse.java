package lv.javaguru.java2.core.responce.Remove;

import lv.javaguru.java2.core.responce.CoreError;
import lv.javaguru.java2.core.responce.CoreResponse;

import java.util.List;

public class RemoveClientResponse extends CoreResponse {

    private boolean clientRemoved;

    public RemoveClientResponse(boolean clientRemoved) {
        this.clientRemoved = clientRemoved;
    }

    public RemoveClientResponse(List<CoreError> errors){
        super(errors);
    }

    public boolean isClientRemoved() {
        return clientRemoved;
    }
}
