package lv.javaguru.java2.core.responce.Add;

import lv.javaguru.java2.Client;
import lv.javaguru.java2.core.responce.CoreError;
import lv.javaguru.java2.core.responce.CoreResponse;

import java.util.List;

public class AddClientResponse extends CoreResponse {

    private Client client;

    public AddClientResponse(Client client) {
        this.client = client;
    }

    public AddClientResponse(List<CoreError> errors) {
        super(errors);
    }


    public Client getClient() {
        return client;
    }
}
