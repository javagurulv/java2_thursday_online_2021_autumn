package lv.javaguru.java2.oddJobs.core.response.find;

import lv.javaguru.java2.oddJobs.core.response.CoreError;
import lv.javaguru.java2.oddJobs.core.response.CoreResponse;
import lv.javaguru.java2.oddJobs.core.domain.Advertisement;

import java.util.List;

public class FindAdvertisementResponse extends CoreResponse {

    private List<Advertisement> advertisements;

    public FindAdvertisementResponse(List<Advertisement> advertisements,List<CoreError> errors) {
        super(errors);
        this.advertisements = advertisements;
    }

    public List<Advertisement> getAdvertisements() {
        return advertisements;
    }
}
