package lv.javaguru.java2.oddJobs.core.services.add;

import lv.javaguru.java2.oddJobs.core.requests.add.AddAdvertismentRequest;
import lv.javaguru.java2.oddJobs.core.responce.add.AddAdvertismentResponce;
import lv.javaguru.java2.oddJobs.database.Database;
import lv.javaguru.java2.oddJobs.domain.Advertisement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddAdvertismentService {

    @Autowired
    private Database database;


    public AddAdvertismentResponce execute(AddAdvertismentRequest request) {
        Advertisement advertisement = new Advertisement(request.getAdvTitle(), request.getAdvDescription());
        database.addAdvertisement(advertisement);
        return new AddAdvertismentResponce(advertisement);

    }
}
