package lv.javaguru.java2.oddJobs.core.responce.add;

import lv.javaguru.java2.oddJobs.domain.Advertisement;

public class AddAdvertismentResponce {

    private Advertisement advertisement;

    public AddAdvertismentResponce(Advertisement advertisement) {
        this.advertisement = advertisement;
    }

    public Advertisement getAdvertisement() {
        return advertisement;
    }
}
