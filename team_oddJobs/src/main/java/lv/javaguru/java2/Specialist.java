package lv.javaguru.java2;

import java.util.Objects;

public class Specialist {
    private Long specialistId;
    private String specialistName;
    private String specialistSurname;
    private String specialistProfession;

    public Specialist(String name, String surname, String profession) {
        this.specialistName = name;
        this.specialistSurname = surname;
        this.specialistProfession = profession;
    }

    public Specialist(Long specialistId) {
        this.specialistId = specialistId;
    }

    public Specialist(String specialistProfession) {
        this.specialistProfession = specialistProfession;
    }

    public Long getSpecialistId() {
        return specialistId;
    }

    public void setSpecialistId(Long specialistId) {
        this.specialistId = specialistId;
    }

    public String getSpecialistName() {
        return specialistName;
    }

    public void setSpecialistName(String specialistName) {
        this.specialistName = specialistName;
    }

    public String getSpecialistSurname() {
        return specialistSurname;
    }

    public void setSpecialistSurname(String specialistSurname) {
        this.specialistSurname = specialistSurname;
    }

    public String getSpecialistProfession() {
        return specialistProfession;
    }

    public void setSpecialistProfession(String specialistProfession) {
        this.specialistProfession = specialistProfession;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Specialist that = (Specialist) o;
        return Objects.equals(specialistId, that.specialistId) && Objects.equals(specialistName, that.specialistName) && Objects.equals(specialistSurname, that.specialistSurname) && Objects.equals(specialistProfession, that.specialistProfession);
    }

    @Override
    public int hashCode() {
        return Objects.hash(specialistId, specialistName, specialistSurname, specialistProfession);
    }

    @Override
    public String toString() {
        return "Specialist{" +
                "id=" + specialistId +
                ", name='" + specialistName + '\'' +
                ", surname='" + specialistSurname + '\'' +
                ", profession='" + specialistProfession + '\'' +
                '}';
    }

}
