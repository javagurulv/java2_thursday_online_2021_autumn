package lv.javaguru.java2.jg_entertainment.restaurant.core.requests.users;

public class AddUserRequest {

    private String name;
    private String surname;
    private String telephone;

    public AddUserRequest() {
    }

    public AddUserRequest(String name, String surname, String telephone) {
        this.name = name;
        this.surname = surname;
        this.telephone = telephone;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
