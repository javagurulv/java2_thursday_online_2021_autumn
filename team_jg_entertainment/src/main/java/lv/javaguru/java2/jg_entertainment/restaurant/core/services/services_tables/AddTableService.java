package lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_tables;

import lv.javaguru.java2.jg_entertainment.restaurant.dependency_injection.DIComponent;
import lv.javaguru.java2.jg_entertainment.restaurant.dependency_injection.DIDependency;
import lv.javaguru.java2.jg_entertainment.restaurant.domain.Table;
import lv.javaguru.java2.jg_entertainment.restaurant.core.database.TableDatabase;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.tables.AddTableRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.tables.AddTableResponse;

@DIComponent
public class AddTableService {

	@DIDependency private TableDatabase tableDatabase;

	public AddTableResponse execute(AddTableRequest request) {
		Table table = new Table(request.getTitle(), request.getTableCapacity(),request.getPrice());
		tableDatabase.save(table);
		return new AddTableResponse(table);
	}

}
