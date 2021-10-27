package lv.javaguru.java2.jg_entertainment.restaurant.core.requests.visitors;

public class AddVisitorRequest {

    private String name;
    private String surname;
    private String telephone;

    public AddVisitorRequest(String name, String surname, String telephone) {
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
}
