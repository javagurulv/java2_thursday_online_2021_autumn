package lv.javaguru.java2.jg_entertainment.restaurant.domain;

import javax.persistence.*;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "visitors")
public class Visitors {

    @Id
    @Column(name = "visitor_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idClient;

    @Column(name = "visitor_name", nullable = false)
    private String clientName;

    @Column(name = "visitor_surname", nullable = false)
    private String surname;

    @Column(name = "visitor_telephone_number", nullable = false)
    private String telephoneNumber;

    public Visitors() {
    }

    public Visitors(String clientName, String surname) {
        this.clientName = clientName;
        this.surname = surname;
    }

    public Visitors(String clientName, String surname, String telephoneNumber) {
        this.clientName = clientName;
        this.surname = surname;
        this.telephoneNumber = telephoneNumber;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Long getIdClient() {
        return idClient;
    }

    public void setIdClient(Long idClient) {
        this.idClient = idClient;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Visitors that = (Visitors) o;
        return Objects.equals(clientName, that.clientName)
                && Objects.equals(idClient, that.idClient)
                && Objects.equals(surname, that.surname)
                && Objects.equals(telephoneNumber, that.telephoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientName, surname, idClient, telephoneNumber);
    }

    @Override
    public String toString() {
        return "Client information in catalogue: {" +
                " client name ->'" + clientName + '\'' +
                ", surname ->'" + surname + '\'' +
                ", ID client ->" + idClient +
                // ", age client ->" + age +
                ", telephone number ->" + telephoneNumber +
                '}';
    }
}
