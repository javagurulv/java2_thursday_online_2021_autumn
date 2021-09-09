package lv.javaguru.java2.jg_entertainment.core.services;

import lv.javaguru.java2.jg_entertainment.Table;
import lv.javaguru.java2.jg_entertainment.core.database.TableDatabase;
import lv.javaguru.java2.jg_entertainment.core.requests.GetAllTablesRequest;
import lv.javaguru.java2.jg_entertainment.core.responses.GetAllTablesResponse;

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