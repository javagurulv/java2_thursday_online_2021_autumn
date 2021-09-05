package lv.javaguru.java2.core.responce.Add;

import lv.javaguru.java2.Client;

public class AddClientResponse {

    private Client client;

    public AddClientResponse(Client client) {
        this.client = client;
    }

    public Client getClient() {
        return client;
    }
}
