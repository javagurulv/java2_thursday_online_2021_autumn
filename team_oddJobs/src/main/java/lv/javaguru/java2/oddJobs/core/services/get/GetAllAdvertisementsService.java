package lv.javaguru.java2.oddJobs.core.services.get;

import lv.javaguru.java2.oddJobs.database.AdvertisementRepository;
import lv.javaguru.java2.oddJobs.domain.Advertisement;
import lv.javaguru.java2.oddJobs.database.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetAllAdvertisementsService {
    @Autowired
    private AdvertisementRepository advertisementRepository;

    public List<Advertisement> execute() {
        return advertisementRepository.getAllAdvertisement();
    }
}
