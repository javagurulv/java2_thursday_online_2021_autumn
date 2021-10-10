package lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_tables;

import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.tables.CoreError;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators.ValidatorAddTable;
import lv.javaguru.java2.jg_entertainment.restaurant.domain.Table;
import lv.javaguru.java2.jg_entertainment.restaurant.core.database.DatabaseTable;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.tables.AddTableRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.tables.AddTableResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddTableService {

    @Autowired
    private DatabaseTable databaseTable;
    @Autowired
    private ValidatorAddTable validator;

    public AddTableResponse execute(AddTableRequest request) {
        List<CoreError> coreErrors = validator.validate(request);
        if (!coreErrors.isEmpty()) {
            return new AddTableResponse(coreErrors);
        }
        Table table =
                new Table(request.getTitle(),
                        request.getTableCapacity(),
                        request.getPrice());

        databaseTable.save(table);
        return new AddTableResponse(table);
    }

}
