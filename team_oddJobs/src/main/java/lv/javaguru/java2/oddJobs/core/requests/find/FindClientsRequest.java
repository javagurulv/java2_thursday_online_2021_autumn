package lv.javaguru.java2.oddJobs.core.requests.find;


public class FindClientsRequest {

    private Long clientId;
    private String clientName;
    private String clientSurname;

    private Ordering ordering;
    private Paging paging;


    public FindClientsRequest(Long clientId, String clientName, String clientSurname) {
        this.clientId = clientId;
        this.clientName = clientName;
        this.clientSurname = clientSurname;
    }

    public FindClientsRequest(String clientName, String clientSurname, Ordering ordering) {
        this.clientName = clientName;
        this.clientSurname = clientSurname;
        this.ordering = ordering;
    }

    public FindClientsRequest(Long clientId, String clientName, String clientSurname, Ordering ordering) {
        this.clientId = clientId;
        this.clientName = clientName;
        this.clientSurname = clientSurname;
        this.ordering = ordering;
    }

    public FindClientsRequest(String clientName, String clientSurname, Ordering ordering, Paging paging) {
        this.clientName = clientName;
        this.clientSurname = clientSurname;
        this.ordering = ordering;
        this.paging = paging;
    }

    public FindClientsRequest(Long clientId, String clientName, String clientSurname, Ordering ordering, Paging paging) {
        this.clientId = clientId;
        this.clientName = clientName;
        this.clientSurname = clientSurname;
        this.ordering = ordering;
        this.paging = paging;
    }

    public FindClientsRequest(long clientId, String clientName, String clientSurname, Paging paging) {
        this.clientId = clientId;
        this.clientName = clientName;
        this.clientSurname = clientSurname;
        this.paging = paging;
    }

    public FindClientsRequest(String clientName, String clientSurname) {
        this.clientName = clientName;
        this.clientSurname = clientSurname;
    }

    public FindClientsRequest(String clientName) {
        this.clientName = clientName;
    }

    public Long getClientId() {
        return clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public String getClientSurname() {
        return clientSurname;
    }

    public Ordering getOrdering() {
        return ordering;
    }

    public Paging getPaging() {
        return paging;
    }

    public boolean isIdProvided() {
        return this.clientId != null;
    }

    public boolean isNameProvided() {
        return this.clientName != null && !this.clientName.isEmpty();
    }

    public boolean isSurnameProvide() {
        return this.clientSurname != null && !this.clientSurname.isEmpty();
    }
}
