package lv.javaguru.java2.jg_entertainment.restaurant.domain;

import java.util.Objects;

public class Visitors {

    private Long idClient;
    private String clientName;
    private String surname;
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
