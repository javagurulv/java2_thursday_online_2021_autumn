package lv.javaguru.java2.jg_entertainment.restaurant.core.requests.visitors;

public class FindVisitorInformationRequest {

    private String name;
    private Long idVisitors;

    public FindVisitorInformationRequest(String name, Long idVisitors) {
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
