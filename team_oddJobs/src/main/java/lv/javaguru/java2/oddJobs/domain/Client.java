package lv.javaguru.java2.oddJobs.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Clients")
public class Client {
    @Id
    @Column(name = "clientId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clientId;

    @Column(name = "clientName", nullable = false)
    private String clientName;
    @Column(name = "clientSurname", nullable = false)

    private String clientSurname;


    public Client(String clientName, String clientSurname) {
        this.clientName = clientName;
        this.clientSurname = clientSurname;
    }

    public Client(Long clientId, String clientName, String clientSurname) {
        this.clientId = clientId;
        this.clientName = clientName;
        this.clientSurname = clientSurname;
    }

    public Client(Long clientId) {
        this.clientId = clientId;
    }

    public Client() {

    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long id) {
        this.clientId = id;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientSurname() {
        return clientSurname;
    }

    public void setClientSurname(String clientSurname) {
        this.clientSurname = clientSurname;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(clientId, client.clientId) && Objects.equals(clientName, client.clientName) && Objects.equals(clientSurname, client.clientSurname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId, clientName, clientSurname);
    }


    @Override
    public String toString() {
        return "Client{" +
                "clientId=" + clientId +
                ", clientName='" + clientName + '\'' +
                ", clientSurname='" + clientSurname + '\'' +
                '}';
    }
}
