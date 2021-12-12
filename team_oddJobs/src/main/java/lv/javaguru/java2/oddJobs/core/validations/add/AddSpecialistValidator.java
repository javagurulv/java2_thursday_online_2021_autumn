package lv.javaguru.java2.oddJobs.core.validations.add;

import lv.javaguru.java2.oddJobs.core.requests.add.AddSpecialistRequest;
import lv.javaguru.java2.oddJobs.core.response.CoreError;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class AddSpecialistValidator {

    @Autowired
    private SessionFactory sessionFactory;

    public List<CoreError> validate(AddSpecialistRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateName(request).ifPresent(errors::add);
        validateSurname(request).ifPresent(errors::add);
        validateProfession(request).ifPresent(errors::add);
        validatePersonalCode(request).ifPresent(errors::add);
        validatePersonalCodeTwo(request).ifPresent(errors::add);
        validateCity(request).ifPresent(errors::add);
        return errors;

    }

    private Optional<CoreError> validateName(AddSpecialistRequest request) {
        return (request.getSpecialistName() == null || request.getSpecialistName().isEmpty())
                ? Optional.of(new CoreError("Name", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateSurname(AddSpecialistRequest request) {
        return (request.getSpecialistSurname() == null || request.getSpecialistSurname().isEmpty())
                ? Optional.of(new CoreError("Surname", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateProfession(AddSpecialistRequest request) {
        return (request.getSpecialistProfession() == null || request.getSpecialistProfession().isEmpty())
                ? Optional.of(new CoreError("Profession", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validatePersonalCode(AddSpecialistRequest request) {
        return (request.getPersonalCode() == null || request.getPersonalCode().isEmpty())
                ? Optional.of(new CoreError("Personal code", "Must not be empty!"))
                : Optional.empty();
    }
    private Optional<CoreError> validatePersonalCodeTwo(AddSpecialistRequest request) {
        Query query = sessionFactory.getCurrentSession().createQuery("select personalCode from Specialist where personalCode=:personalCode");
        query.setParameter("personalCode", request.getPersonalCode());
        if (!((org.hibernate.query.Query) query).list().isEmpty())
            return Optional.of(new CoreError("Personal code", "Already exist!"));
        return Optional.empty();
    }


    private Optional<CoreError> validateCity(AddSpecialistRequest request) {
        return (request.getCity() == null || request.getCity().isEmpty())
                ? Optional.of(new CoreError("City", "Must not be empty!"))
                : Optional.empty();
    }
}
