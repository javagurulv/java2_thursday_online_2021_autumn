package lv.javaguru.java2.oddJobs.core.validations.remove;

import lv.javaguru.java2.oddJobs.core.requests.remove.RemoveAdvertismentRequest;
import lv.javaguru.java2.oddJobs.core.response.CoreError;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class RemoveAdvertisementValidator {

    @Autowired
    private SessionFactory sessionFactory;

    public List<CoreError> validate(RemoveAdvertismentRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateTitle(request).ifPresent(errors::add);
        validateId(request).ifPresent(errors::add);
        validateTitleDb(request).ifPresent(errors::add);
        validateIdDb(request).ifPresent(errors::add);
        return errors;

    }

    private Optional<CoreError> validateId(RemoveAdvertismentRequest request) {
        return (request.getAdvId() == null || request.getAdvId().describeConstable().isEmpty())
                ? Optional.of(new CoreError("Id", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateIdDb(RemoveAdvertismentRequest request) {
        Query query = sessionFactory.getCurrentSession().createQuery("select advId from Advertisement where advId=:advId");
        query.setParameter("advId", request.getAdvId());
        if (((org.hibernate.query.Query) query).list().isEmpty())
            return Optional.of(new CoreError("Field Id:", "No Id with this name in Database."));
        return Optional.empty();
    }

    private Optional<CoreError> validateTitle(RemoveAdvertismentRequest request) {
        return (request.getAdvTitle() == null || request.getAdvTitle().isEmpty())
                ? Optional.of(new CoreError("Title", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateTitleDb(RemoveAdvertismentRequest request) {
        Query query = sessionFactory.getCurrentSession().createQuery("select advTitle from Advertisement where advTitle=:advTitle");
        query.setParameter("advTitle", request.getAdvTitle());
        if (((org.hibernate.query.Query) query).list().isEmpty())
            return Optional.of(new CoreError("Field Title:", "No title with this name in Database."));
        return Optional.empty();
    }

}
