package lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_tables;

import lv.javaguru.java2.jg_entertainment.restaurant.core.database.DatabaseTable;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.tables.RemoveTableRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.tables.CoreError;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.tables.RemoveTableResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators.ValidatorRemoveTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RemoveTableService {

    @Autowired
    private DatabaseTable databaseTable;
    @Autowired
    private ValidatorRemoveTable validator;

    public RemoveTableResponse execute(RemoveTableRequest request) {

        List<CoreError> coreErrors = validator.coreErrors(request);
        if (!coreErrors.isEmpty()) {
            return new RemoveTableResponse(coreErrors);
        }
        boolean isTableRemoved =
                databaseTable.deleteById(request.getTableIdToRemove());
        return new RemoveTableResponse(isTableRemoved);
    }
}