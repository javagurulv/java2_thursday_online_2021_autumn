package lv.javaguru.java2.jg_entertainment.core.responses;

public class RemoveTableResponse {

	private boolean tableRemoved;

	public RemoveTableResponse(boolean tableRemoved) {
		this.tableRemoved = tableRemoved;
	}

	public boolean isTableRemoved() {
		return tableRemoved;
	}
}