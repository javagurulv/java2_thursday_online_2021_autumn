package lv.javaguru.java2.jg_entertainment.restaurant.core.requests.tables;

public class SearchTableRequest {

    private String titleTable;
    private Long idTable;

    private OrderingTable ordering;
    private PagingTable paging;


    public SearchTableRequest(String titleTable, Long idTable) {
        this.titleTable = titleTable;
        this.idTable = idTable;
    }

    public SearchTableRequest(String titleTable,
                              Long idTable,
                              OrderingTable ordering) {
        this.titleTable = titleTable;
        this.idTable = idTable;
        this.ordering = ordering;
    }

    public SearchTableRequest(String titleTable,
                              Long idTable,
                              PagingTable paging) {
        this.titleTable = titleTable;
        this.idTable = idTable;
        this.paging = paging;
    }

    public SearchTableRequest(String titleTable,
                              Long idTable,
                              OrderingTable ordering,
                              PagingTable paging) {
        this.titleTable = titleTable;
        this.idTable = idTable;
        this.ordering = ordering;
        this.paging = paging;
    }

    public SearchTableRequest(String titleTable,
                              OrderingTable ordering,
                              PagingTable paging) {
        this.titleTable = titleTable;
        this.ordering = ordering;
        this.paging = paging;
    }

    public String getTitleTable() {
        return titleTable;
    }

    public Long getIdTable() {
        return idTable;
    }

    public OrderingTable getOrdering() {
        return ordering;
    }

    public PagingTable getPaging() {
        return paging;
    }

    public boolean isTitleProvided() {
        return this.titleTable != null
                && !this.titleTable.isEmpty();
    }

    public boolean isIDProvided() {
        return this.idTable != null
                && this.idTable != 0;
    }
}
