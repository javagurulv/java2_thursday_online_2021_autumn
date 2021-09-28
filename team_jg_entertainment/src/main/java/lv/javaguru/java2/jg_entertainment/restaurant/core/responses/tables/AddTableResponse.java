package lv.javaguru.java2.jg_entertainment.restaurant.core.responses.tables;

import lv.javaguru.java2.jg_entertainment.restaurant.domain.Table;

public class AddTableResponse {

	private Table newTable;

	public AddTableResponse(Table newTable) {
		this.newTable = newTable;
	}

	public Table getNewTable() {
		return newTable;
	}
}