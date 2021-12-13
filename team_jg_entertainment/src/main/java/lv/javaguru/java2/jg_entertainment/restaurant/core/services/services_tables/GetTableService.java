package lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_tables;

import lv.javaguru.java2.jg_entertainment.restaurant.core.database.table_repository.TableRepository;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.tables.GetTableRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.tables.CoreError;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.tables.GetTableResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators.GetTableValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class GetTableService {

    @Autowired private TableRepository tableRepository;
    @Autowired private GetTableValidator validator;

    public GetTableResponse execute(GetTableRequest request) {
        List<CoreError> errorList = validator.validate(request);
        if (!errorList.isEmpty()) {
            return new GetTableResponse(errorList);
        }
        return tableRepository.getById(request.getId())
                .map(GetTableResponse::new)
                .orElseGet(() -> {
                    errorList.add(new CoreError("id", "was not found"));
                    return new GetTableResponse(errorList);
                });
    }
}
