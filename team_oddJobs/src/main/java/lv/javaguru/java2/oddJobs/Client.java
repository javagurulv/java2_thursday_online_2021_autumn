package lv.javaguru.java2.oddJobs;

import java.util.Objects;

public class Client {
    private String name;
    private String surname;
    private String service;

    public Client(String name, String surname) {
        this.name = name;
        this.surname = surname;

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }


    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(name, client.name) && Objects.equals(surname, client.surname) && Objects.equals(service, client.service);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, service);
    }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", service='" + service + '\'' +
                '}';
    }
}
