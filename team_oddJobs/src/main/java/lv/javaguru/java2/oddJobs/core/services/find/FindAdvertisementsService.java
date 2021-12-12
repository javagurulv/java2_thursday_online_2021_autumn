package lv.javaguru.java2.oddJobs.core.services.find;

import lv.javaguru.java2.oddJobs.core.requests.find.FindAdvertisementRequest;
import lv.javaguru.java2.oddJobs.core.requests.find.Ordering;
import lv.javaguru.java2.oddJobs.core.requests.find.Paging;
import lv.javaguru.java2.oddJobs.core.response.CoreError;
import lv.javaguru.java2.oddJobs.core.response.find.FindAdvertisementResponse;
import lv.javaguru.java2.oddJobs.core.validations.find.FindAdvertisementValidator;
import lv.javaguru.java2.oddJobs.core.database.domainInterfaces.AdvertisementRepository;
import lv.javaguru.java2.oddJobs.core.domain.Advertisement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Transactional
public class FindAdvertisementsService {

    @Value("${search.ordering.enabled}")
    private boolean orderingEnabled;

    @Value("${search.paging.enabled}")
    private boolean pagingEnabled;

    @Autowired
    private AdvertisementRepository advertisementRepository;
    @Autowired
    private FindAdvertisementValidator findAdvertisementsValidator;

    public FindAdvertisementResponse execute(FindAdvertisementRequest request) {

        List<CoreError> errors = findAdvertisementsValidator.validate(request);

        if (!errors.isEmpty()) {
            return new FindAdvertisementResponse( null,errors);
        }

        List<Advertisement> advertisements = find(request);
        advertisements = ordering(advertisements, request.getOrdering());
        advertisements = paging(advertisements, request.getPaging());

        return new FindAdvertisementResponse(advertisements, errors);
    }

    private List<Advertisement> ordering(List<Advertisement> advertisements, Ordering ordering) {

        if (orderingEnabled && ordering != null) {
            Comparator<Advertisement> comparator = ordering.getOrderBy().equals("Title")
                    ? Comparator.comparing(Advertisement::getAdvId)
                    : Comparator.comparing(Advertisement::getAdvTitle);
            if (ordering.getOrderDirection().equals("DESCENDING")) {
                comparator = comparator.reversed();
            }
            return advertisements.stream().sorted(comparator).collect(Collectors.toList());
        } else {
            return advertisements;
        }
    }

    private List<Advertisement> paging(List<Advertisement> advertisements, Paging paging) {
        if (pagingEnabled && paging != null) {
            int skip = (paging.getPageNumber() - 1) * paging.getPageSize();
            return advertisements.stream()
                    .skip(skip)
                    .limit(paging.getPageSize())
                    .collect(Collectors.toList());
        } else {
            return advertisements;
        }
    }


    private List<Advertisement> find(FindAdvertisementRequest request) {
        List<Advertisement> advertisements = new ArrayList<>();
        if (request.isIdProvided() && !request.isTitleProvided()) {
            advertisements = advertisementRepository.findAdvertisementById(request.getAdvId());
        }
        if (!request.isIdProvided() && request.isTitleProvided()) {
            advertisements = advertisementRepository.findAdvertisementByTitle(request.getAdvTitle());
        }


        return advertisements;
    }

}
