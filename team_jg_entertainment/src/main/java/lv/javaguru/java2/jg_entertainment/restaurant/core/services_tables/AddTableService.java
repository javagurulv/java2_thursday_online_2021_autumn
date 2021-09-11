package lv.javaguru.java2.jg_entertainment.restaurant.core.services_tables;

import lv.javaguru.java2.jg_entertainment.Table;
import lv.javaguru.java2.jg_entertainment.restaurant.core.database_tables.TableDatabase;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests_tables.AddTableRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses_tables.AddTableResponse;

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
