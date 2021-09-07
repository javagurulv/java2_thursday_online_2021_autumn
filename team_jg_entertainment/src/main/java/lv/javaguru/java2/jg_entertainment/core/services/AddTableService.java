package lv.javaguru.java2.jg_entertainment.core.services;

import lv.javaguru.java2.jg_entertainment.Table;
import lv.javaguru.java2.jg_entertainment.core.database.TableDatabase;
import lv.javaguru.java2.jg_entertainment.core.requests.AddTableRequest;
import lv.javaguru.java2.jg_entertainment.core.responses.AddTableResponse;

public class AddTableService {

	private TableDatabase tableDatabase;

	public AddTableService(TableDatabase tableDatabase) {
		this.tableDatabase = tableDatabase;
	}

	public AddTableResponse execute(AddTableRequest request) {
		Table table = new Table(request.getTitle(), request.getTableCapacity());
		tableDatabase.save(table);
		return new AddTableResponse(table);
	}

}
