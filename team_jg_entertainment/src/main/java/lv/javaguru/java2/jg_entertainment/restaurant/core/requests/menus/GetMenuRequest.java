package lv.javaguru.java2.jg_entertainment.restaurant.core.requests.menus;

public class GetMenuRequest {

    private Long id;

    public GetMenuRequest() {
    }

    public GetMenuRequest(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
