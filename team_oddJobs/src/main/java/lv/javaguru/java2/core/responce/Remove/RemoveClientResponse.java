package lv.javaguru.java2.core.responce.Remove;

public class RemoveClientResponse {

    private boolean clientRemoved;

    public RemoveClientResponse(boolean clientRemoved) {
        this.clientRemoved = clientRemoved;
    }

    public boolean isClientRemoved() {
        return clientRemoved;
    }
}
