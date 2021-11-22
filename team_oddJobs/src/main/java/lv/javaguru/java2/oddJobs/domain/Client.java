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

    @Column(name = "personalCode", nullable = false)
    private String personalCode;

    @Column(name = "city", nullable = false)
    private String city;


    public Client(String clientName, String clientSurname, String personalCode, String city) {
        this.clientName = clientName;
        this.clientSurname = clientSurname;
        this.personalCode=personalCode;
        this.city=city;
    }

    public Client(Long clientId, String clientName, String clientSurname,String personalCode, String city) {
        this.clientId = clientId;
        this.clientName = clientName;
        this.clientSurname = clientSurname;
        this.personalCode=personalCode;
        this.city=city;
    }

    public Client(Long clientId) {
        this.clientId = clientId;
    }

    public Client() {

    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
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

    public String getPersonalCode() {
        return personalCode;
    }

    public void setPersonalCode(String personalCode) {
        this.personalCode = personalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(clientId, client.clientId) && Objects.equals(clientName, client.clientName) && Objects.equals(clientSurname, client.clientSurname) && Objects.equals(personalCode, client.personalCode) && Objects.equals(city, client.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId, clientName, clientSurname, personalCode, city);
    }

    @Override
    public String toString() {
        return "Client{" +
                "clientId=" + clientId +
                ", clientName='" + clientName + '\'' +
                ", clientSurname='" + clientSurname + '\'' +
                ", personalCode='" + personalCode + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
