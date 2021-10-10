package lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_tables;

import lv.javaguru.java2.jg_entertainment.restaurant.domain.Table;
import lv.javaguru.java2.jg_entertainment.restaurant.core.database.TableDatabase;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.tables.GetAllTablesRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.tables.GetAllTablesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetAllTablesService {

	@Autowired
	private TableDatabase tableDatabase;

	public GetAllTablesResponse execute(GetAllTablesRequest request) {
		List<Table> tables = tableDatabase.getAllTables();
		return new GetAllTablesResponse(tables);
	}

}