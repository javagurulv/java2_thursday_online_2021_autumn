package lv.javaguru.java2.jg_entertainment.restaurant.core.requests.visitors;

public class RequestFindVisitorInformation {

    private String name;
    private Long idVisitors;

    public RequestFindVisitorInformation(Long idVisitors, String name) {
        this.name = name;
        this.idVisitors = idVisitors;
    }

    public String getName() {
        return name;
    }

    public Long getIdVisitors() {
        return idVisitors;
    }

}
