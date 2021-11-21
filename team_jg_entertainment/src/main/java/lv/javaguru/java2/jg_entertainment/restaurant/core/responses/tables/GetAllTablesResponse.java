package lv.javaguru.java2.jg_entertainment.restaurant.core.responses.tables;

import lv.javaguru.java2.jg_entertainment.restaurant.domain.Table;

import java.util.List;

public class GetAllTablesResponse extends CoreResponse {

	private List<Table> tables;

	public GetAllTablesResponse(List<Table> tables) {
		this.tables = tables;
	}

	public List<Table> getTables() {
		return tables;
	}
}
