package lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_tables;

import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.tables.CoreError;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators.AddTableValidator;
import lv.javaguru.java2.jg_entertainment.restaurant.domain.Table;
import lv.javaguru.java2.jg_entertainment.restaurant.core.database.table_repository.TableRepository;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.tables.AddTableRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.tables.AddTableResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class AddTableService {

    @Autowired private TableRepository tableRepository;
    @Autowired private AddTableValidator validator;

    public AddTableResponse execute(AddTableRequest request) {
        List<CoreError> coreErrors = validator.validate(request);
        if (!coreErrors.isEmpty()) {
            return new AddTableResponse(coreErrors);
        }
        Table table =
                new Table(request.getTitle(),
                        request.getTableCapacity(),
                        request.getPrice());

        tableRepository.save(table);
        return new AddTableResponse(table);
    }
}
