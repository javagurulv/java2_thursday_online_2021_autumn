package lv.javaguru.java2.oddJobs.core.validations.remove;


import lv.javaguru.java2.oddJobs.core.requests.remove.RemoveSpecialistRequest;
import lv.javaguru.java2.oddJobs.core.response.CoreError;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class RemoveSpecialistValidator {

    @Autowired
    private SessionFactory sessionFactory;


    public List<CoreError> validate(RemoveSpecialistRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateId(request).ifPresent(errors::add);
        validateName(request).ifPresent(errors::add);
        validateSurname(request).ifPresent(errors::add);
        validateNameDb(request).ifPresent(errors::add);
        validateSurnameDb(request).ifPresent(errors::add);
        validateIdDb(request).ifPresent(errors::add);

        return errors;
    }


    private Optional<CoreError> validateId(RemoveSpecialistRequest request) {
        return (request.getSpecialistId() == null || request.getSpecialistId().describeConstable().isEmpty())
                ? Optional.of(new CoreError("Field Id:", "Must not be empty!"))
                : Optional.empty();
    }
    private Optional<CoreError> validateIdDb(RemoveSpecialistRequest request) {
        Query query = sessionFactory.getCurrentSession().createQuery("select specialistId from Specialist where specialistId=:specialistId");
        query.setParameter("specialistId", request.getSpecialistId());
        if (((org.hibernate.query.Query) query).list().isEmpty())
            return Optional.of(new CoreError("Field Id:", "No specialist with this ID in Database."));
        return Optional.empty();
    }

    private Optional<CoreError> validateName(RemoveSpecialistRequest request) {
        return (request.getSpecialistName() == null || request.getSpecialistName().isEmpty())
                ? Optional.of(new CoreError("Field Name:", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateNameDb(RemoveSpecialistRequest request) {
        Query query = sessionFactory.getCurrentSession().createQuery("select specialistName from Specialist where specialistName=:specialistName");
        query.setParameter("specialistName", request.getSpecialistName());
        if (((org.hibernate.query.Query) query).list().isEmpty())
            return Optional.of(new CoreError("Field Name:", "No specialist with this name in Database."));
        return Optional.empty();
    }

    private Optional<CoreError> validateSurname(RemoveSpecialistRequest request) {
        return (request.getSpecialistSurname() == null || request.getSpecialistSurname().isEmpty())
                ? Optional.of(new CoreError("Field Surname:", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateSurnameDb(RemoveSpecialistRequest request) {
        Query query = sessionFactory.getCurrentSession().createQuery("select specialistSurname from Specialist where specialistSurname=:specialistSurname");
        query.setParameter("specialistSurname", request.getSpecialistSurname());
        if (((org.hibernate.query.Query) query).list().isEmpty())
            return Optional.of(new CoreError("Field Surname:", "No specialist with this surname in Database."));
        return Optional.empty();
    }


}
