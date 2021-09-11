package lv.javaguru.java2.jg_entertainment.restaurant.core.requests_tables;

public class RemoveTableRequest {

	private Long tableIdToRemove;

	public RemoveTableRequest(Long tableIdToRemove) {
		this.tableIdToRemove = tableIdToRemove;
	}

	public Long getTableIdToRemove() {
		return tableIdToRemove;
	}
}
