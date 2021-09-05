package lv.javaguru.java2.services.Remove;


import lv.javaguru.java2.core.requests.Remove.RemoveClientRequest;
import lv.javaguru.java2.core.responce.Remove.RemoveClientResponse;
import lv.javaguru.java2.database.Database;

public class RemoveClientService {
    private Database database;

    public RemoveClientService(Database database) {
        this.database = database;
    }

    public RemoveClientResponse execute(RemoveClientRequest request) {
        boolean isClientRemoved = database.removeClient(request.getClientId(), request.getClientName(), request.getClientSurname());
        return new RemoveClientResponse(isClientRemoved);
    }
}
