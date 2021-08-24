package lv.javaguru.java2.oddJobs;

import java.util.Objects;

public class Specialist {
    private String name;
    private String surname;
    private String profession;

    public Specialist(String name, String surname, String profession) {
        this.name = name;
        this.surname = surname;
        this.profession = profession;
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

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Specialist specialist = (Specialist) o;
        return Objects.equals(name, specialist.name) && Objects.equals(surname, specialist.surname) && Objects.equals(profession, specialist.profession);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, profession);
    }

    @Override
    public String toString() {
        return "Specialist{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", profession='" + profession + '\'' +
                '}';
    }

}
