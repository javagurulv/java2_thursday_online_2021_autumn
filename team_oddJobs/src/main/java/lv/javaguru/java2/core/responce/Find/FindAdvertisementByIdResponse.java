package lv.javaguru.java2.core.responce.Find;

import lv.javaguru.java2.Advertisement;

public class FindAdvertisementByIdResponse {

    private Advertisement foundAdvertisement;

    public FindAdvertisementByIdResponse(Advertisement foundAdvertisement) {
        this.foundAdvertisement = foundAdvertisement;
    }

    public Advertisement getFoundAdvertisement() {return foundAdvertisement;}
}
