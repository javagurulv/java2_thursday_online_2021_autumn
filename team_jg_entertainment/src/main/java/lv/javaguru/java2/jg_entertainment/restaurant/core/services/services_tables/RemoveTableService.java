package lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_tables;

import lv.javaguru.java2.jg_entertainment.restaurant.core.database.table_repository.TableRepository;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.tables.RemoveTableRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.tables.CoreError;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.tables.RemoveTableResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators.RemoveTableValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class RemoveTableService {

    @Autowired private TableRepository tableRepository;
    @Autowired private RemoveTableValidator validator;

    public RemoveTableResponse execute(RemoveTableRequest request) {

        List<CoreError> coreErrors = validator.coreErrors(request);
        if (!coreErrors.isEmpty()) {
            return new RemoveTableResponse(coreErrors);
        }
        boolean isTableRemoved =
                tableRepository.deleteById(request.getTableIdToRemove());
        return new RemoveTableResponse(isTableRemoved);
    }
}