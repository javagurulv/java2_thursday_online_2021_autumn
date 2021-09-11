package lv.javaguru.java2.jg_entertainment.restaurant.core.services_tables;

import lv.javaguru.java2.jg_entertainment.Table;
import lv.javaguru.java2.jg_entertainment.restaurant.core.database_tables.TableDatabase;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests_tables.GetAllTablesRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses_tables.GetAllTablesResponse;

import java.util.List;

public class GetAllTablesService {

	private TableDatabase tableDatabase;

	public GetAllTablesService(TableDatabase tableDatabase) {
		this.tableDatabase = tableDatabase;
	}

	public GetAllTablesResponse execute(GetAllTablesRequest request) {
		List<Table> tables = tableDatabase.getAllTables();
		return new GetAllTablesResponse(tables);
	}

}