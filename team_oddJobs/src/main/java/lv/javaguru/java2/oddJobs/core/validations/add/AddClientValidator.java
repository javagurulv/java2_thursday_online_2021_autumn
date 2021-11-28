package lv.javaguru.java2.oddJobs.core.validations.add;

import lv.javaguru.java2.oddJobs.core.requests.add.AddClientRequest;
import lv.javaguru.java2.oddJobs.core.responce.CoreError;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class AddClientValidator {

    @Autowired
    private SessionFactory sessionFactory;

    public List<CoreError> validate(AddClientRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateName(request).ifPresent(errors::add);
        validateSurname(request).ifPresent(errors::add);
        validatePersonalCode(request).ifPresent(errors::add);
        validatePersonalCodeTwo(request).ifPresent(errors::add);
        validateCity(request).ifPresent(errors::add);
        return errors;

    }

    private Optional<CoreError> validateName(AddClientRequest request) {
        return (request.getClientName() == null || request.getClientName().isEmpty())
                ? Optional.of(new CoreError("Name", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateSurname(AddClientRequest request) {
        return (request.getClientSurname() == null || request.getClientSurname().isEmpty())
                ? Optional.of(new CoreError("Surname", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validatePersonalCode(AddClientRequest request) {
        return (request.getPersonalCode() == null || request.getPersonalCode().isEmpty())
                ? Optional.of(new CoreError("Personal code", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validatePersonalCodeTwo(AddClientRequest request) {
        Query query = sessionFactory.getCurrentSession().createQuery("select personalCode from Client where personalCode=:personalCode");
        query.setParameter("personalCode", request.getPersonalCode());
        if (!((org.hibernate.query.Query) query).list().isEmpty())
        return Optional.of(new CoreError("Personal code", "Already exist!"));
        return Optional.empty();
    }


    private Optional<CoreError> validateCity(AddClientRequest request) {
        return (request.getCity() == null || request.getCity().isEmpty())
                ? Optional.of(new CoreError("City", "Must not be empty!"))
                : Optional.empty();
    }

}
