package lv.javaguru.java2.jg_entertainment.core.services;

import lv.javaguru.java2.jg_entertainment.core.database.TableDatabase;
import lv.javaguru.java2.jg_entertainment.core.requests.RemoveTableRequest;
import lv.javaguru.java2.jg_entertainment.core.responses.RemoveTableResponse;

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