package lv.javaguru.java2.oddJobs.core.validations.remove;

import lv.javaguru.java2.oddJobs.core.requests.remove.RemoveClientRequest;
import lv.javaguru.java2.oddJobs.core.response.CoreError;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class RemoveClientValidator {
    @Autowired
    private SessionFactory sessionFactory;

    public List<CoreError> validate(RemoveClientRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateId(request).ifPresent(errors::add);
        validateName(request).ifPresent(errors::add);
        validateSurname(request).ifPresent(errors::add);
        validateIdDb(request).ifPresent(errors::add);
        validateNameDb(request).ifPresent(errors::add);
        validateSurnameDb(request).ifPresent(errors::add);

        return errors;
    }


    private Optional<CoreError> validateId(RemoveClientRequest request) {
        return (request.getClientId() == null || request.getClientId().describeConstable().isEmpty())
                ? Optional.of(new CoreError("Id", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateIdDb(RemoveClientRequest request) {
        Query query = sessionFactory.getCurrentSession().createQuery("select clientId from Client where clientId=:clientId");
        query.setParameter("clientId", request.getClientId());
        if (((org.hibernate.query.Query) query).list().isEmpty())
            return Optional.of(new CoreError("Field Id:", "No client with this ID in Database."));
        return Optional.empty();
    }

    private Optional<CoreError> validateName(RemoveClientRequest request) {
        return (request.getClientName() == null || request.getClientName().isEmpty())
                ? Optional.of(new CoreError("Name", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateNameDb(RemoveClientRequest request) {
        Query query = sessionFactory.getCurrentSession().createQuery("select clientName from Client where clientName=:clientName");
        query.setParameter("clientName", request.getClientName());
        if (((org.hibernate.query.Query) query).list().isEmpty())
            return Optional.of(new CoreError("Field Name:", "No client with this name in Database."));
        return Optional.empty();
    }

    private Optional<CoreError> validateSurname(RemoveClientRequest request) {
        return (request.getClientSurname() == null || request.getClientSurname().isEmpty())
                ? Optional.of(new CoreError("Surname", "Must not be empty!"))
                : Optional.empty();
    }
    private Optional<CoreError> validateSurnameDb(RemoveClientRequest request) {
        Query query = sessionFactory.getCurrentSession().createQuery("select clientSurname from Client where clientSurname=:clientSurname");
        query.setParameter("clientSurname", request.getClientSurname());
        if (((org.hibernate.query.Query) query).list().isEmpty())
            return Optional.of(new CoreError("Field Surname:", "No client with this surname in Database."));
        return Optional.empty();
    }

}
