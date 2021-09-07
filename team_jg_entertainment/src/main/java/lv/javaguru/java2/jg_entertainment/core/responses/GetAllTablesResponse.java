package lv.javaguru.java2.jg_entertainment.core.responses;

import lv.javaguru.java2.jg_entertainment.Table;

import java.util.List;

public class GetAllTablesResponse {

	private List<Table> tables;

	public GetAllTablesResponse(List<Table> tables) {
		this.tables = tables;
	}

	public List<Table> getTables() {
		return tables;
	}
}
