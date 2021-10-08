package lv.javaguru.java2.jg_entertainment.restaurant.matchersTables;

import lv.javaguru.java2.jg_entertainment.restaurant.domain.Table;
import org.mockito.ArgumentMatcher;

public class Matchers implements ArgumentMatcher<Table> {

    private String tableTitle;
    private int tableCapacity;
    private double price;

    public Matchers(String tableTitle,
                    int tableCapacity,
                    double price) {
        this.tableTitle = tableTitle;
        this.tableCapacity = tableCapacity;
        this.price = price;
    }

    @Override
    public boolean matches(Table tables) {
        return tables.getTitle().equals(tableTitle)
                && tables.getTableCapacity() == tableCapacity
                && tables.getPrice() == price;
    }
}
