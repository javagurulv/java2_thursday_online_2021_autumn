package lv.javaguru.java2.jg_entertainment.core.requests;

public class RemoveTableRequest {

	private Long tableIdToRemove;

	public RemoveTableRequest(Long tableIdToRemove) {
		this.tableIdToRemove = tableIdToRemove;
	}

	public Long getTableIdToRemove() {
		return tableIdToRemove;
	}
}
