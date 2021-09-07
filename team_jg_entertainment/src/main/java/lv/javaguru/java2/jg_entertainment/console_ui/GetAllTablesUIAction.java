package lv.javaguru.java2.jg_entertainment.console_ui;

import lv.javaguru.java2.jg_entertainment.core.requests.GetAllTablesRequest;
import lv.javaguru.java2.jg_entertainment.core.responses.GetAllTablesResponse;
import lv.javaguru.java2.jg_entertainment.core.services.GetAllTablesService;

public class GetAllTablesUIAction implements UIAction {

	private GetAllTablesService getAllTablesService;

	public GetAllTablesUIAction(GetAllTablesService getAllTablesService) {
		this.getAllTablesService = getAllTablesService;
	}

	@Override
	public void execute() {
		System.out.println("Table list: ");
		GetAllTablesRequest request = new GetAllTablesRequest();
		GetAllTablesResponse response = getAllTablesService.execute(request);
		response.getTables().forEach(System.out::println);
		System.out.println("Table list end.");
	}
}