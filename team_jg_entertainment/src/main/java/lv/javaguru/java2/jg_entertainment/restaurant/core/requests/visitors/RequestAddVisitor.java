package lv.javaguru.java2.jg_entertainment.restaurant.core.requests.visitors;

public class RequestAddVisitor {

    private String name;
    private String surname;
    private Long telephone;

    public RequestAddVisitor(String name, String surname, Long telephone) {
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

    public Long getTelephone() {
        return telephone;
    }
}
