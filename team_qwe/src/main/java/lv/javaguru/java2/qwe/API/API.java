package lv.javaguru.java2.qwe.API;

import lv.javaguru.java2.qwe.core.database.Database;
import lv.javaguru.java2.qwe.core.domain.Security;
import lv.javaguru.java2.qwe.core.domain.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
@Transactional
public class API {

    @Autowired private Database database;

    @Value("${realTime.marketPrice.enabled}")
    private boolean realMarketPriceDataEnabled;

    @Value("${realTime.marketPrice.initDelay}")
    private int realMarketPriceInitDelay;

    @Value("${realTime.marketPrice.period}")
    private int realMarketPricePeriod;

    public void setRealMarketPriceUpdate() {
        if (realMarketPriceDataEnabled && realMarketPricePeriod > 0) {
            ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);
            Runnable simulator = () -> updateMarketPrice("AAPL");
            scheduledExecutorService.scheduleAtFixedRate(
                    simulator, realMarketPriceInitDelay, realMarketPricePeriod, TimeUnit.SECONDS
            );
        }
    }

    private void updateMarketPrice(String ticker) {
        Double realTimePrice = getQuote(ticker);
        Optional<Security> stock = database.findSecurityByTickerOrName(ticker + " US");
        System.out.println("REAL-TIME PRICE IS: " + realTimePrice);
        if (stock.isPresent() && realTimePrice != null ) {
            Stock stock1 = (Stock) stock.get();
            stock1.setMarketPrice(realTimePrice);
            System.out.println("NEW PRICE IS SET!");
            try {
                database.editStock(stock1);
            } catch (Exception e) {
                System.out.println("ERROR!");
                e.printStackTrace();
            }
            System.out.println("PRICE UPDATED!");
        }
    }

    private static Double getQuote(String ticker) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://yfapi.net/v6/finance/quote?region=US&lang=en&symbols=" + ticker))
                    .header("x-api-key", "") // <== ввести свой API key!
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient()
                    .send(request, HttpResponse.BodyHandlers.ofString());
            String[] lines = response.body().split(",");
            Optional<String> target = Arrays.stream(lines)
                    .filter(line -> line.contains("regularMarketPrice"))
                    .findAny();
            String lastPrice = target.map(s -> s.substring(21)).orElse("-1");
            return Double.parseDouble(lastPrice);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

}