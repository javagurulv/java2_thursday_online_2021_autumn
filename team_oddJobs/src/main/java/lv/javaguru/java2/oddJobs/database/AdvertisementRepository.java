package lv.javaguru.java2.oddJobs.database;

import lv.javaguru.java2.oddJobs.domain.Advertisement;

import java.util.List;

public interface AdvertisementRepository {

    void addAdvertisement(Advertisement advertisement);

    boolean removeAdvertisement(Long advId, String advTitle);

    List<Advertisement> findAdvertisementByTitle(String advTitle);

    List<Advertisement> findAdvertisementById(Long advId);

    List<Advertisement> getAllAdvertisement();
}
