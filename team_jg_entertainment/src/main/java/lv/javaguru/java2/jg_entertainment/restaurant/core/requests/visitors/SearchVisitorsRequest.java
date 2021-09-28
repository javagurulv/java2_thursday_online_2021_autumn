package lv.javaguru.java2.jg_entertainment.restaurant.core.requests.visitors;

public class SearchVisitorsRequest {

    private String nameVisitors;
    private String surnameVisitors;
    private Long idVisitors;
    private Long telephoneNumber;

    private Ordering ordering;
    private Paging paging;

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

    public SearchVisitorsRequest(String nameVisitors,
                                 String surnameVisitors,
                                 Paging paging) {
        this.nameVisitors = nameVisitors;
        this.surnameVisitors = surnameVisitors;
        this.paging = paging;
    }

    public SearchVisitorsRequest(String nameVisitors,
                                 String surnameVisitors,
                                 Ordering ordering,
                                 Paging paging) {
        this.nameVisitors = nameVisitors;
        this.surnameVisitors = surnameVisitors;
        this.ordering = ordering;
        this.paging = paging;
    }

    public SearchVisitorsRequest(String nameVisitors,
                                 Long telephoneNumber,
                                 Ordering ordering,
                                 Paging paging) {
        this.nameVisitors = nameVisitors;
        this.telephoneNumber = telephoneNumber;
        this.ordering = ordering;
        this.paging = paging;
    }

    public SearchVisitorsRequest(Long idVisitors,
                                 Ordering ordering,
                                 Paging paging) {
        this.idVisitors = idVisitors;
        this.ordering = ordering;
        this.paging = paging;
    }

    public String getNameVisitors() {
        return nameVisitors;
    }

    public String getSurnameVisitors() {
        return surnameVisitors;
    }

    public Long getIdVisitors() {
        return idVisitors;
    }

    public Long getTelephoneNumber() {
        return telephoneNumber;
    }

    public boolean isNameProvided() {
        return this.nameVisitors != null
                && !this.nameVisitors.isEmpty();
    }

    public boolean isSurnameProvided() {
        return this.surnameVisitors != null
                && !this.surnameVisitors.isEmpty();
    }

    public boolean isIDProvided() {
        return this.idVisitors != null
                && this.idVisitors != 0;
    }

    public boolean isTelephoneNumberProvided() {
        return this.telephoneNumber != null
                && this.telephoneNumber != 0;
    }

    public Ordering getOrdering() {
        return ordering;
    }

    public Paging getPaging() {
        return paging;
    }
}
