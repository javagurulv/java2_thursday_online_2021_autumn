package lv.javaguru.java2.jg_entertainment.restaurant.core.requests.visitors;

public class RequestFindVisitorInformation {

    private String name;
    private String surname;
    private Long idVisitors;

    public RequestFindVisitorInformation(Long idVisitors, String name) {
        this.name = name;
        this.idVisitors = idVisitors;
    }

    public RequestFindVisitorInformation(String name, String surname, Long idVisitors) {
        this.name = name;
        this.surname = surname;
        this.idVisitors = idVisitors;
    }

    public String getName() {
        return name;
    }

    public Long getIdVisitors() {
        return idVisitors;
    }

    public boolean isNameProvided() {
        return this.name != null && !this.name.isEmpty();
    }

    public boolean isSurnameProvided() {
        return this.surname != null && !this.surname.isEmpty();
    }

    public boolean isIdProvided() {
        return this.idVisitors != null;
    }
}
