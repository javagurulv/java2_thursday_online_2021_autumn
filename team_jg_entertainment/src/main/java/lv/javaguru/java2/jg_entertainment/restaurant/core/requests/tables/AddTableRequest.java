package lv.javaguru.java2.jg_entertainment.restaurant.core.requests.tables;

public class AddTableRequest {

	private String title;
	private int tableCapacity;

	public AddTableRequest(String title, int tableCapacity) {
		this.title = title;
		this.tableCapacity = tableCapacity;
	}

	public String getTitle() {return title;
	}

	public int getTableCapacity() {
		return tableCapacity;
	}
}
