package lv.javaguru.java2.oddJobs.core.requests.find;

public class FindAdvertisementRequest {

    private Long advId;
    private String advTitle;

    private Ordering ordering;
    private Paging paging;

    public FindAdvertisementRequest(Long advId) {
        this.advId = advId;
    }

    public FindAdvertisementRequest(Long advId, String advTitle) {
        this.advId = advId;
        this.advTitle = advTitle;
    }

    public FindAdvertisementRequest(Long advId, String advTitle, Ordering ordering) {
        this.advId = advId;
        this.advTitle = advTitle;
        this.ordering = ordering;
    }

    public FindAdvertisementRequest(Long advId, String advTitle, Ordering ordering, Paging paging) {
        this.advId = advId;
        this.advTitle = advTitle;
        this.ordering = ordering;
        this.paging = paging;
    }

    public Long getAdvId() {
        return advId;
    }

    public String getAdvTitle() {
        return advTitle;
    }

    public Ordering getOrdering() {
        return ordering;
    }

    public Paging getPaging() {
        return paging;
    }

    public boolean isIdProvided() {
        return this.advId != null;
    }

    public boolean isTitleProvided() {
        return this.advTitle != null && !this.advTitle.isEmpty();
    }

}
