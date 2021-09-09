package lv.javaguru.java2.jg_entertainment.console_ui;

import lv.javaguru.java2.jg_entertainment.core.requests.AddTableRequest;
import lv.javaguru.java2.jg_entertainment.core.responses.AddTableResponse;
import lv.javaguru.java2.jg_entertainment.core.services.AddTableService;

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
		AddTableRequest request = new AddTableRequest(tableTitle, tableCapacity);
		AddTableResponse response = addTableService.execute(request);
		System.out.println("New table id was: " + response.getNewTable().getId());
		System.out.println("Your table was added to list.");
	}

}