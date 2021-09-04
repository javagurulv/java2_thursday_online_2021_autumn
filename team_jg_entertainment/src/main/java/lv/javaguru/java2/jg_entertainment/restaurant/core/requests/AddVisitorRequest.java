package lv.javaguru.java2.jg_entertainment.restaurant.core.requests;

public class AddVisitorRequest {

    private String name;
    private String surname;
    private long telephone;

    public AddVisitorRequest(String name, String surname, long telephone) {
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

    public long getTelephone() {
        return telephone;
    }
}
