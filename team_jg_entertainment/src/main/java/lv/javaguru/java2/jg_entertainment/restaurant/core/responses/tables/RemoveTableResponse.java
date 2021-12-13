package lv.javaguru.java2.jg_entertainment.restaurant.core.responses.tables;

import java.util.List;

public class RemoveTableResponse extends CoreResponse{

	private Long id;
	private boolean tableRemoved;

	public RemoveTableResponse(List<CoreError> errorsList) {
		super(errorsList);
	}

	public RemoveTableResponse(boolean tableRemoved) {
		this.tableRemoved = tableRemoved;
	}

	public RemoveTableResponse(Long id) {
		this.id = id;
	}

	public boolean isTableRemoved() {
		return tableRemoved;
	}

	public Long getId() {
		return id;
	}
}