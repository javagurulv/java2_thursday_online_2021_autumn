package lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_tables;

import lv.javaguru.java2.jg_entertainment.restaurant.core.database.table_repository.TableRepository;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.tables.UpdateTableRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.tables.CoreError;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.tables.UpdateTableResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators.UpdateTableRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class UpdateTableService {

    @Autowired private TableRepository tableRepository;
    @Autowired private UpdateTableRequestValidator validator;

    public UpdateTableResponse execute(UpdateTableRequest request) {
        List<CoreError> errorList = validator.validate(request);
        if (!errorList.isEmpty()) {
            return new UpdateTableResponse(errorList);
        }
        return tableRepository.getById(request.getId())
                .map(table -> {
                    table.setTitle(request.getNewTitle());
                    table.setTableCapacity(request.getNewTableCapacity());
                    table.setPrice(request.getNewPrice());
                    return new UpdateTableResponse(table);
                })
                .orElseGet(() -> {
                    errorList.add(new CoreError("id", "was not found"));
                    return new UpdateTableResponse(errorList);
                });
    }
}
