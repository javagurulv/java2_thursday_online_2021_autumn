package lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_menu.matcher;

import lv.javaguru.java2.jg_entertainment.restaurant.domain.Menu;
import org.mockito.ArgumentMatcher;

public class MenuMatcher implements ArgumentMatcher<Menu> {

    private String title;
    private String description;
    private double price;

    public MenuMatcher(String title, String description, double price) {
        this.title = title;
        this.description = description;
        this.price = price;
    }

    @Override
    public boolean matches(Menu argument) {
        return argument.getTitle().equals(title)
                && argument.getDescription().equals(description)
                && argument.getPrice() == price;
    }
}
