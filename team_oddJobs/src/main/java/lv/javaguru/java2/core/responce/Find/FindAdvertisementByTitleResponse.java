package lv.javaguru.java2.core.responce.Find;

import lv.javaguru.java2.Advertisement;

public class FindAdvertisementByTitleResponse {

    private Advertisement foundAdvertisment;

    public FindAdvertisementByTitleResponse(Advertisement foundAdvertisment) {
        this.foundAdvertisment = foundAdvertisment;
    }

    public Advertisement getFoundAdvertisement() {
        return foundAdvertisment;
    }
}
