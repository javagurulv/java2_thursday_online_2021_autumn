package lv.javaguru.java2.services.Find;

import lv.javaguru.java2.Specialist;
import lv.javaguru.java2.core.requests.Find.FindSpecialistRequest;
import lv.javaguru.java2.core.requests.Find.Ordering;
import lv.javaguru.java2.core.requests.Find.Paging;
import lv.javaguru.java2.core.responce.CoreError;
import lv.javaguru.java2.core.responce.Find.FindSpecialistResponse;
import lv.javaguru.java2.core.validations.FindSpecialistValidator;
import lv.javaguru.java2.database.Database;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FindSpecialistService {

    private Database database;
    private FindSpecialistValidator specialistValidator;


    public FindSpecialistService(Database database, FindSpecialistValidator specialistValidator) {
        this.database = database;
        this.specialistValidator = specialistValidator;
    }

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

        private List<Specialist> ordering(List<Specialist>specialists,Ordering ordering) {
            if (ordering != null) {
                Comparator<Specialist> comparator = ordering.getOrderBy().equals("specialistSurname")
                        ? Comparator.comparing(Specialist::getSpecialistSurname)
                        : Comparator.comparing(Specialist::getSpecialistName);
                if (ordering.getOrderDirection().equals("DESCENDING")) {
                    comparator = comparator.reversed();
                }
                return specialists.stream().sorted(comparator).collect(Collectors.toList());
            } else {
                return specialists;
            }
        }

    private List<Specialist> paging(List<Specialist> specialists, Paging paging) {
        if (paging != null) {
            int skip = (paging.getPageNumber() - 1) * paging.getPageSize();
            return specialists.stream()
                    .skip(skip)
                    .limit(paging.getPageSize())
                    .collect(Collectors.toList());
        } else {
            return specialists;
        }
    }


        private List<Specialist> find(FindSpecialistRequest request){
        List<Specialist> specialists = new ArrayList<>();
        if (request.isIdProvided() && !request.isNameProvided() && !request.isSurnameProvided() && !request.isProfessionProvided()) {
            specialists = database.findSpecialistById(request.getSpecialistId());
        }
        if (!request.isIdProvided() && request.isNameProvided() && !request.isSurnameProvided() && !request.isProfessionProvided()) {
            specialists = database.findSpecialistByName(request.getSpecialistName());
        }
        if (!request.isIdProvided() && !request.isNameProvided() && request.isSurnameProvided() && !request.isProfessionProvided()) {
            specialists = database.findSpecialistBySurname(request.getSpecialistSurname());
        }
        if (!request.isIdProvided() && !request.isNameProvided() && !request.isSurnameProvided() && request.isProfessionProvided()) {
            specialists = database.findSpecialistByProfession(request.getSpecialistProfession());
        }
        if (request.isIdProvided() && request.isNameProvided() && request.isSurnameProvided() && request.isProfessionProvided()) {
            specialists = database.findSpecialistByIdAndNameAndSurnameAndProfession(request.getSpecialistId(), request.getSpecialistName(), request.getSpecialistSurname(), request.getSpecialistProfession());
        }

        return specialists;
    }
}
