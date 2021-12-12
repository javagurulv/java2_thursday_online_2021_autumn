package lv.javaguru.java2.oddJobs.core.services.get;

import lv.javaguru.java2.oddJobs.core.database.domainInterfaces.AdvertisementRepository;
import lv.javaguru.java2.oddJobs.core.domain.Advertisement;
import lv.javaguru.java2.oddJobs.core.requests.get.GetAllAdvertisementRequest;
import lv.javaguru.java2.oddJobs.core.response.get.GetAllAdvertisementsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class GetAllAdvertisementsService {
    @Autowired
    private AdvertisementRepository advertisementRepository;

    public GetAllAdvertisementsResponse execute(GetAllAdvertisementRequest request) {
        List<Advertisement> advertisements = advertisementRepository.getAllAdvertisement();
        return new GetAllAdvertisementsResponse(advertisements);
    }
}
