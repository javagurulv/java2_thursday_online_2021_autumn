package lv.javaguru.java2.jg_entertainment.restaurant.core.requests.visitors;

public class SearchVisitorsRequest {

    private String nameVisitors;
    private String surnameVisitors;

    private Ordering ordering;

    public SearchVisitorsRequest(String nameVisitors,
                                 String surnameVisitors) {
        this.nameVisitors = nameVisitors;
        this.surnameVisitors = surnameVisitors;
    }

    public SearchVisitorsRequest(String nameVisitors,
                                 String surnameVisitors,
                                 Ordering ordering) {
        this.nameVisitors = nameVisitors;
        this.surnameVisitors = surnameVisitors;
        this.ordering = ordering;
    }

    public String getNameVisitors() {
        return nameVisitors;
    }

    public String getSurnameVisitors() {
        return surnameVisitors;
    }

    public boolean isNameProvided() {
        return this.nameVisitors != null && !this.nameVisitors.isEmpty();
    }

    public boolean isSurnameProvided() {
        return this.surnameVisitors != null && !this.surnameVisitors.isEmpty();
    }

    public Ordering getOrdering() {
        return ordering;
    }
}
