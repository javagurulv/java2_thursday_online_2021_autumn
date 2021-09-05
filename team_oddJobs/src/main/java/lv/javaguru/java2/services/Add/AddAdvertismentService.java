package lv.javaguru.java2.services.Add;

import lv.javaguru.java2.Advertisement;
import lv.javaguru.java2.core.requests.Add.AddAdvertismentRequest;
import lv.javaguru.java2.core.responce.Add.AddAdvertismentResponce;
import lv.javaguru.java2.database.Database;

public class AddAdvertismentService {

    private Database database;

    public AddAdvertismentService(Database database) {
        this.database = database;
    }

    public AddAdvertismentResponce execute(AddAdvertismentRequest request) {
        Advertisement advertisement = new Advertisement(request.getAdvTitle(), request.getAdvDescription());
        database.addAdvertisement(advertisement);
        return new AddAdvertismentResponce(advertisement);

    }
}
