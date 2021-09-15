package lv.javaguru.java2.jg_entertainment.restaurant.core.requests.tables;

public class AddTableRequest {

	private String title;
	private int tableCapacity;
	private double price;

	public AddTableRequest(String title, int tableCapacity, double price) {
		this.title = title;
		this.tableCapacity = tableCapacity;
		this.price = price;
	}

	public String getTitle() {return title;
	}

	public int getTableCapacity() {
		return tableCapacity;
	}

	public double getPrice() {
		return price;
	}
}
