package lv.javaguru.java2.core.requests.Find;

public class FindAdvertisementByIdRequest {

    private long advId;

    public FindAdvertisementByIdRequest(long advId) {this.advId = advId;}

    public Long getAdvId() {return advId;}

    public boolean isIdProvided() {
        return this.advId != 0;
    }
}
