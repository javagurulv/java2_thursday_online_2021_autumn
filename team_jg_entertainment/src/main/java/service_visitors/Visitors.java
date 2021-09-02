package service_visitors;

import java.util.Objects;

public class Visitors {

    private String clientName;
    private String surname;
    private String age;
    private Long idClient;

    private long telephoneNumber;
   // private String emailClient;

    public Visitors(String clientName, String surname,String age) {
        this.clientName = clientName;
        this.surname = surname;
        this.age = age;
    }

    public Visitors(String clientName, String surname,String age, long telephoneNumber) {
        this.clientName = clientName;
        this.surname = surname;
        this.age = age;
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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public long getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(long telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

//    public String getEmailClient() {
//        return emailClient;
//    }

//    public void setEmailClient(String emailClient) {
//        this.emailClient = emailClient;
//    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Visitors that = (Visitors) o;
        return Objects.equals(clientName, that.clientName)
                && Objects.equals(idClient, that.idClient)
                && Objects.equals(surname, that.surname)
                && Objects.equals(age, that.age)
                && Objects.equals(telephoneNumber, that.telephoneNumber);
             //   && Objects.equals(emailClient, that.emailClient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientName, surname, age, idClient, telephoneNumber); //, emailClient
    }

    @Override
    public String toString() {
        return "Client information in catalogue: {" +
                " client name ->'" + clientName + '\'' +
                ", surname ->'" + surname + '\'' +
                ", ID client ->" + idClient +
                ", age client ->" + age +
                ", telephone number ->" + telephoneNumber +
//                ", email client ->" + emailClient +
                '}';
    }
}
