package lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_tables;

import lv.javaguru.java2.jg_entertainment.restaurant.core.database.TableDatabase;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.tables.RemoveTableRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.tables.RemoveTableResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.dependency_injection.DIComponent;
import lv.javaguru.java2.jg_entertainment.restaurant.dependency_injection.DIDependency;

@DIComponent
public class RemoveTableService {

	@DIDependency private TableDatabase tableDatabase;

	public RemoveTableResponse execute(RemoveTableRequest request) {
		boolean isTableRemoved = tableDatabase.deleteById(request.getTableIdToRemove());
		return new RemoveTableResponse(isTableRemoved);
	}
}