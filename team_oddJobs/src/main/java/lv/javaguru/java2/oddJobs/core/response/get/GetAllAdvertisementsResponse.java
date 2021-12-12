package lv.javaguru.java2.oddJobs.core.response.get;

import lv.javaguru.java2.oddJobs.core.domain.Advertisement;
import lv.javaguru.java2.oddJobs.core.response.CoreResponse;

import java.util.List;

public class GetAllAdvertisementsResponse extends CoreResponse {

    private List<Advertisement> advertisements;

    public GetAllAdvertisementsResponse(List<Advertisement> advertisements) {
        this.advertisements = advertisements;
    }

    public GetAllAdvertisementsResponse() {

    }

    public List<Advertisement> getAdvertisements() {
        return advertisements;
    }
}
