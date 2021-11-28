package lv.javaguru.java2.oddJobs.core.services.add;

import lv.javaguru.java2.oddJobs.core.domain.Client;
import org.mockito.ArgumentMatcher;

public class ClientMatcher implements ArgumentMatcher<Client> {

    String name;
    String surname;

    public ClientMatcher(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    @Override
    public boolean matches(Client client) {
        return client.getClientName().equals(name) && client.getClientSurname().equals(surname);
    }
}
