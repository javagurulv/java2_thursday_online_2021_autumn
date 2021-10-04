package lv.javaguru.java2.services.Add;

import lv.javaguru.java2.Advertisement;
import lv.javaguru.java2.core.requests.Add.AddAdvertismentRequest;
import lv.javaguru.java2.core.responce.Add.AddAdvertismentResponce;
import lv.javaguru.java2.database.Database;
import lv.javaguru.java2.dependency_injection.DIComponent;
import lv.javaguru.java2.dependency_injection.DIDependency;

@DIComponent
public class AddAdvertismentService {

    @DIDependency
    private Database database;


    public AddAdvertismentResponce execute(AddAdvertismentRequest request) {
        Advertisement advertisement = new Advertisement(request.getAdvTitle(), request.getAdvDescription());
        database.addAdvertisement(advertisement);
        return new AddAdvertismentResponce(advertisement);

    }
}
