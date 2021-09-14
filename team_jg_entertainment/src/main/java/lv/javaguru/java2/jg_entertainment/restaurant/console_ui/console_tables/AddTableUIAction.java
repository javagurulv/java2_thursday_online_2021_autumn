package lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_tables;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.tables.AddTableRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.tables.AddTableResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_tables.AddTableService;

import java.util.Scanner;

public class AddTableUIAction implements UIAction {

	private AddTableService addTableService;

	public AddTableUIAction(AddTableService addTableService) {
		this.addTableService = addTableService;
	}

	@Override
	public void execute() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter table title: ");
		String tableTitle = scanner.nextLine();
		System.out.println("Enter table capacity: ");
		int tableCapacity = scanner.nextInt();
		System.out.println("Enter table price: ");
		double price = scanner.nextDouble();
		AddTableRequest request = new AddTableRequest(tableTitle, tableCapacity, price);
		AddTableResponse response = addTableService.execute(request);
		System.out.println("New table id was: " + response.getNewTable().getId());
		System.out.println("Your table was added to list.");
	}

}