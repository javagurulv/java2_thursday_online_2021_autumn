package lv.javaguru.java2.jg_entertainment.restaurant.core.requests.tables;

public class GetTableRequest {

    private Long id;

    public GetTableRequest() {
    }

    public GetTableRequest(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
