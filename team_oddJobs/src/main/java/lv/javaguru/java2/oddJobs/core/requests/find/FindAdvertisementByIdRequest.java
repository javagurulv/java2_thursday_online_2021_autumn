package lv.javaguru.java2.oddJobs.core.requests.find;

public class FindAdvertisementByIdRequest {

    private long advId;

    public FindAdvertisementByIdRequest(long advId) {this.advId = advId;}

    public Long getAdvId() {return advId;}

    public boolean isIdProvided() {
        return this.advId != 0;
    }
//     public long getAdvId() {return advId;}
}
