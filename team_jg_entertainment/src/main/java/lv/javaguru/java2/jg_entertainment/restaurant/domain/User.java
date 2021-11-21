package lv.javaguru.java2.jg_entertainment.restaurant.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "visitors")
public class User {

    @Id
    @Column(name = "visitor_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "visitor_name", nullable = false)
    private String userName;

    @Column(name = "visitor_surname", nullable = false)
    private String surname;

    @Column(name = "visitor_telephone_number", nullable = false)
    private String telephoneNumber;

    public User() {
    }

    public User(String userName, String surname) {
        this.userName = userName;
        this.surname = surname;
    }

    public User(String userName, String surname, String telephoneNumber) {
        this.userName = userName;
        this.surname = surname;
        this.telephoneNumber = telephoneNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
        User that = (User) o;
        return Objects.equals(userName, that.userName)
                && Objects.equals(userId, that.userId)
                && Objects.equals(surname, that.surname)
                && Objects.equals(telephoneNumber, that.telephoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, surname, userId, telephoneNumber);
    }

    @Override
    public String toString() {
        return "Client information in catalogue: {" +
                " client name ->'" + userName + '\'' +
                ", surname ->'" + surname + '\'' +
                ", ID client ->" + userId +
                // ", age client ->" + age +
                ", telephone number ->" + telephoneNumber +
                '}';
    }
}
