package lv.javaguru.java2.jg_entertainment.restaurant.core.requests.tables;

public class RemoveTableRequest {

    private Long tableIdToRemove;

    public RemoveTableRequest() {
    }

    public RemoveTableRequest(Long tableIdToRemove) {
        this.tableIdToRemove = tableIdToRemove;
    }

    public void setTableIdToRemove(Long tableIdToRemove) {
        this.tableIdToRemove = tableIdToRemove;
    }

    public Long getTableIdToRemove() {
        return tableIdToRemove;
    }
}
