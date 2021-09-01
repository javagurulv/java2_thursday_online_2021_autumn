package service_visitors;

import java.util.Objects;

public class Visitors {

    private String clientName;
    private String surname;
    private String age;
    private Long idClient;

    public Visitors(String clientName, String surname,String age) {
        this.clientName = clientName;
        this.surname = surname;
        this.age = age;
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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Visitors that = (Visitors) o;
        return Objects.equals(clientName, that.clientName)
                && Objects.equals(idClient, that.idClient)
                && Objects.equals(surname, that.surname)
                && Objects.equals(age, that.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientName, surname, age, idClient);
    }

    @Override
    public String toString() {
        return "Client information in catalogue: {" +
                " client name ->'" + clientName + '\'' +
                ", surname ->'" + surname + '\'' +
                ", ID client ->" + idClient +
                ", age client ->" + age +
                '}';
    }
}
