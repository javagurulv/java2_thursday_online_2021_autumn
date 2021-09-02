package main.java;

import java.util.Objects;

class Reservation {

    private String client_Name;
    private int nr_of_persons;
    private String date;


    public Reservation(String client_Name, int nr_of_persons, String date) {
        this.client_Name = client_Name;
        this.nr_of_persons = nr_of_persons;
        this.date = date;
    }

    public String getClient_Name() {
        return client_Name;
    }

    public void setClient_Name(String client_Name) {
        this.client_Name = client_Name;
    }

    public int getNr_of_persons() {
        return nr_of_persons;
    }

    public void setNr_of_persons(int nr_of_persons) {
        this.nr_of_persons = nr_of_persons;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return nr_of_persons == that.nr_of_persons && Objects.equals(client_Name, that.client_Name) && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(client_Name, nr_of_persons, date);
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "client_Name='" + client_Name + '\'' +
                ", nr_of_persons=" + nr_of_persons +
                ", date='" + date + '\'' +
                '}';
    }
}
