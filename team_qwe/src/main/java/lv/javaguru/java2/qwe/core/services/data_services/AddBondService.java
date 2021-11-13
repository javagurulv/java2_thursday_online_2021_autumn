package lv.javaguru.java2.qwe.core.services.data_services;

import lv.javaguru.java2.qwe.core.domain.Bond;
import lv.javaguru.java2.qwe.core.database.Database;
import lv.javaguru.java2.qwe.core.requests.data_requests.AddBondRequest;
import lv.javaguru.java2.qwe.core.requests.data_requests.CoreRequest;
import lv.javaguru.java2.qwe.core.responses.data_responses.AddBondResponse;
import lv.javaguru.java2.qwe.core.responses.CoreError;
import lv.javaguru.java2.qwe.core.services.validator.AddBondValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Component
@Transactional
public class AddBondService {

    @Autowired private Database database;
    @Autowired private AddBondValidator validator;

    public AddBondResponse execute(CoreRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (errors.isEmpty()) {
            Bond bond = createBond((AddBondRequest) request);
            database.addBond(bond);
            return new AddBondResponse(bond);
        }
        return new AddBondResponse(errors);
    }

    private Bond createBond(AddBondRequest request) {
        return new Bond(
                request.getTicker(),
                request.getName(),
                request.getIndustry(),
                request.getCurrency(),
                Double.parseDouble(request.getMarketPrice()),
                Double.parseDouble(request.getCoupon()),
                request.getRating(),
                Integer.parseInt(request.getNominal()),
                LocalDate.parse(request.getMaturity())
        );
    }

}