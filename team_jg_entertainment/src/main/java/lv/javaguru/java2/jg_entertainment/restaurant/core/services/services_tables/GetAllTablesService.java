package lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_tables;

import lv.javaguru.java2.jg_entertainment.restaurant.dependency_injection.DIComponent;
import lv.javaguru.java2.jg_entertainment.restaurant.dependency_injection.DIDependency;
import lv.javaguru.java2.jg_entertainment.restaurant.domain.Table;
import lv.javaguru.java2.jg_entertainment.restaurant.core.database.TableDatabase;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.tables.GetAllTablesRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.tables.GetAllTablesResponse;

import java.util.List;

@DIComponent
public class GetAllTablesService {

	@DIDependency private TableDatabase tableDatabase;

//	public GetAllTablesService(TableDatabase tableDatabase) {
//		this.tableDatabase = tableDatabase;
//	}

	public GetAllTablesResponse execute(GetAllTablesRequest request) {
		List<Table> tables = tableDatabase.getAllTables();
		return new GetAllTablesResponse(tables);
	}

}