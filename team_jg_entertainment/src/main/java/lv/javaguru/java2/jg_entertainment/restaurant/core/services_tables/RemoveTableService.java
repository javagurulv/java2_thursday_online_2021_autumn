package lv.javaguru.java2.jg_entertainment.restaurant.core.services_tables;

import lv.javaguru.java2.jg_entertainment.restaurant.core.database_tables.TableDatabase;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests_tables.RemoveTableRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses_tables.RemoveTableResponse;

public class RemoveTableService {

	private TableDatabase tableDatabase;

	public RemoveTableService(TableDatabase tableDatabase) {
		this.tableDatabase = tableDatabase;
	}

	public RemoveTableResponse execute(RemoveTableRequest request) {
		boolean isTableRemoved = tableDatabase.deleteById(request.getTableIdToRemove());
		return new RemoveTableResponse(isTableRemoved);
	}

}