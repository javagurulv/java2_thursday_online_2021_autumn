package lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_tables;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.tables.RemoveTableRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.tables.RemoveTableResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_tables.RemoveTableService;
import lv.javaguru.java2.jg_entertainment.restaurant.dependency_injection.DIComponent;
import lv.javaguru.java2.jg_entertainment.restaurant.dependency_injection.DIDependency;

import java.util.Scanner;

@DIComponent
public class RemoveTableUIAction implements UIAction {

	@DIDependency private RemoveTableService removeTableService;

	@Override
	public void execute() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter table id to remove: ");
		Long tableId = Long.parseLong(scanner.nextLine());
		RemoveTableRequest request = new RemoveTableRequest(tableId);
		RemoveTableResponse response = removeTableService.execute(request);
		if (response.isTableRemoved()) {
			System.out.println("Your table was removed from list.");
		} else {
			System.out.println("Your table not removed from list.");
		}
	}
}