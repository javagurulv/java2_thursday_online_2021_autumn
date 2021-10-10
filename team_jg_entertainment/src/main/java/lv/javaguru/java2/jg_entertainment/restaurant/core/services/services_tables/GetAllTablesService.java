package lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_tables;

import lv.javaguru.java2.jg_entertainment.restaurant.domain.Table;
import lv.javaguru.java2.jg_entertainment.restaurant.core.database.DatabaseTable;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.tables.GetAllTablesRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.tables.GetAllTablesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetAllTablesService {

	@Autowired
	private DatabaseTable databaseTable;

	public GetAllTablesResponse execute(GetAllTablesRequest request) {
		List<Table> tables = databaseTable.getAllTables();
		return new GetAllTablesResponse(tables);
	}

}