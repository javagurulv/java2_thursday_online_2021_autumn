package lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_tables;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.tables.GetAllTablesRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.tables.GetAllTablesResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_tables.GetAllTablesService;
import lv.javaguru.java2.jg_entertainment.restaurant.dependency_injection.DIComponent;
import lv.javaguru.java2.jg_entertainment.restaurant.dependency_injection.DIDependency;

@DIComponent
public class GetAllTablesUIAction implements UIAction {

	@DIDependency private GetAllTablesService getAllTablesService;

//	public GetAllTablesUIAction(GetAllTablesService getAllTablesService) {
//		this.getAllTablesService = getAllTablesService;
//	}

	@Override
	public void execute() {
		System.out.println("Table list: ");
		GetAllTablesRequest request = new GetAllTablesRequest();
		GetAllTablesResponse response = getAllTablesService.execute(request);
		response.getTables().forEach(System.out::println);
		System.out.println("Table list end.");
	}
}