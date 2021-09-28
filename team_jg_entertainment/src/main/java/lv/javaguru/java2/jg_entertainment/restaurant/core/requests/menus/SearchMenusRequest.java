package lv.javaguru.java2.jg_entertainment.restaurant.core.requests.menus;

import java.util.Objects;

public class SearchMenusRequest {
    private String title;
    private String description;

    public SearchMenusRequest(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
    public boolean isTitleProvided() {
        return this.title != null && !this.title.isEmpty();
    }

    public boolean isDescriptionProvided() {
        return this.description != null && !this.description.isEmpty();
    }

}
