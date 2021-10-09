package lv.javaguru.java2.qwe.core.services.matchers;

import lv.javaguru.java2.qwe.core.domain.Stock;
import org.mockito.ArgumentMatcher;

public class StockMatcher implements ArgumentMatcher<Stock> {

    private final Stock stock;

    public StockMatcher(Stock stock) {
        this.stock = stock;
    }

    @Override
    public boolean matches(Stock argument) {
        return stock.equals(argument);
    }

}