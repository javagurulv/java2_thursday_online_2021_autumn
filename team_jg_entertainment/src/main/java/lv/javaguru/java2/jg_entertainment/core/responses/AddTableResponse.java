package lv.javaguru.java2.jg_entertainment.core.responses;

import lv.javaguru.java2.jg_entertainment.Table;

public class AddTableResponse {

	private Table newTable;

	public AddTableResponse(Table newTable) {
		this.newTable = newTable;
	}

	public Table getNewTable() {
		return newTable;
	}
}