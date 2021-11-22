package lv.javaguru.java2.oddJobs.core.services.find;

import lv.javaguru.java2.oddJobs.core.requests.find.FindSpecialistRequest;
import lv.javaguru.java2.oddJobs.core.requests.find.Ordering;
import lv.javaguru.java2.oddJobs.core.requests.find.Paging;
import lv.javaguru.java2.oddJobs.core.responce.CoreError;
import lv.javaguru.java2.oddJobs.core.responce.find.FindSpecialistResponse;
import lv.javaguru.java2.oddJobs.core.validations.find.FindSpecialistValidator;
import lv.javaguru.java2.oddJobs.database.domainInterfaces.SpecialistRepository;
import lv.javaguru.java2.oddJobs.domain.Specialist;
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
public class FindSpecialistService {

    @Value("${search.ordering.enabled}")
    private boolean orderingEnabled;

    @Value("${search.paging.enabled}")
    private boolean pagingEnabled;

    @Autowired
    private SpecialistRepository specialistRepository;
    @Autowired
    private FindSpecialistValidator specialistValidator;

    public FindSpecialistResponse execute(FindSpecialistRequest request) {

        List<CoreError> errors = specialistValidator.validate(request);

        if (!errors.isEmpty()) {
            return new FindSpecialistResponse(null, errors);
        }

        List<Specialist> specialists = find(request);
        specialists = ordering(specialists, request.getOrdering());
        specialists = paging(specialists, request.getPaging());

        return new FindSpecialistResponse(specialists, null);
    }

    private List<Specialist> ordering(List<Specialist> specialists, Ordering ordering) {
        if (orderingEnabled && ordering != null) {
            Comparator<Specialist> comparator = ordering.getOrderBy().equals("Name")
                    ? Comparator.comparing(Specialist::getSpecialistName)
                    : Comparator.comparing(Specialist::getSpecialistSurname);
            if (ordering.getOrderDirection().equals("DESCENDING")) {
                comparator = comparator.reversed();
            }
            return specialists.stream().sorted(comparator).collect(Collectors.toList());
        } else {
            return specialists;
        }
    }

    private List<Specialist> paging(List<Specialist> specialists, Paging paging) {
        if (pagingEnabled && paging != null) {
            int skip = (paging.getPageNumber() - 1) * paging.getPageSize();
            return specialists.stream()
                    .skip(skip)
                    .limit(paging.getPageSize())
                    .collect(Collectors.toList());
        } else {
            return specialists;
        }
    }


    private List<Specialist> find(FindSpecialistRequest request) {
        List<Specialist> specialists = new ArrayList<>();
        if (!request.isNameProvided() && !request.isSurnameProvided() && !request.isProfessionProvided()) {
            specialists = specialistRepository.findSpecialistById(request.getSpecialistId());
        }
        if (request.isNameProvided() && !request.isSurnameProvided() && !request.isProfessionProvided()) {
            specialists = specialistRepository.findSpecialistByName(request.getSpecialistName());
        }
        if (!request.isNameProvided() && request.isSurnameProvided() && !request.isProfessionProvided()) {
            specialists = specialistRepository.findSpecialistBySurname(request.getSpecialistSurname());
        }
        if (!request.isNameProvided() && !request.isSurnameProvided() && request.isProfessionProvided()) {
            specialists = specialistRepository.findSpecialistByProfession(request.getSpecialistProfession());
        }
        if (request.isNameProvided() && request.isSurnameProvided() && request.isProfessionProvided()) {
            specialists = specialistRepository.findSpecialistByNameAndSurnameAndProfession(request.getSpecialistName(), request.getSpecialistSurname(), request.getSpecialistProfession());
        }

        return specialists;
    }
}
