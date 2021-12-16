package lv.javaguru.java2.oddJobs.core.domain;

public class ClientAccount extends Account {


    Client client = new Client();
    private String clientName = client.getClientName();
    private String clientSurname = client.getClientSurname();
    private String clientCity = client.getClientCity();

    public ClientAccount() {
    }


}
