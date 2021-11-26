package lv.javaguru.java2.qwe.core.API;

import lv.javaguru.java2.qwe.core.database.Database;
import lv.javaguru.java2.qwe.core.domain.Security;
import lv.javaguru.java2.qwe.core.domain.Stock;
import org.json.JSONArray;
import org.json.JSONObject;
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
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    @Value(("${realTime.portfolio.enabled}"))
    private boolean realTimeDataForPortfolioEnabled;

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
        Optional<Security> security = database.findSecurityByTickerOrName(ticker);
        if (security.isPresent() && realTimePrice != -1) {
            Stock stock = (Stock) security.get();
            stock.setMarketPrice(realTimePrice);
            try {
                database.updateStock(stock);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void updateSinglePrice(String ticker, Double realTimePrice) {
        Optional<Security> security = database.findSecurityByTickerOrName(ticker);
        if (security.isPresent() && realTimePrice != -1) {
            Stock stock = (Stock) security.get();
            stock.setMarketPrice(realTimePrice);
            try {
                database.updateStock(stock);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Double getQuote(String ticker) throws NumberFormatException {
        HttpResponse<String> response = sendHttpRequest(ticker);
        if (response != null) {
            String[] lines = response.body().split(",");
            Optional<String> target = Arrays.stream(lines)
                    .filter(line -> line.contains("regularMarketPrice"))
                    .findAny();
            String lastPrice = target.map(s -> s.substring(21)).orElse("-1");
            updateSinglePrice(ticker, Double.parseDouble(lastPrice));
            return Double.parseDouble(lastPrice);
        } else {
            return null;
        }
    }

    public void getQuotesForMultipleSecurities(List<Security> list) {
        if (realTimeDataForPortfolioEnabled) {
            long start = System.nanoTime();
            String[] tickers = getTickers(list);
            if (!tickers[0].isEmpty()) {
                JSONObject[] jsonObjects = IntStream.rangeClosed(0, tickers.length - 1)
                        .mapToObj(i -> Objects.requireNonNull(sendHttpRequestTest(tickers[i])).getJSONObject("quoteResponse"))
                        .toArray(JSONObject[]::new);
                JSONArray arr = IntStream.rangeClosed(0, jsonObjects.length - 1)
                        .mapToObj(i -> jsonObjects[i].getJSONArray("result"))
                        .reduce((JSONArray::putAll)).orElse(null);
                IntStream.rangeClosed(0, list.size() - 1)
                        .parallel()
                        .forEach(i -> {
                            assert arr != null;
                            JSONObject obj = arr.getJSONObject(i);
                            if (obj.getString("symbol").equals(list.get(i).getTicker())) {
                                list.get(i).setMarketPrice(obj.getBigDecimal("regularMarketPrice").doubleValue());
                            }
                        });
            }
            long duration = (System.nanoTime() - start) / 1_000_000;
            System.out.println("PERFORMANCE: " + duration + " ms");
        }
    }

    private HttpResponse<String> sendHttpRequest(String ticker) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://yfapi.net/v6/finance/quote?region=US&lang=en&symbols=" + ticker))
                    .header("x-api-key", "69DeXzYODO5uwFXrwj38GxFCu06P98X9vO6PgkI1") // <== ввести свой API key!
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();
            return HttpClient.newHttpClient()
                    .send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static JSONObject sendHttpRequestTest(String ticker) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://yfapi.net/v6/finance/quote?region=US&lang=en&symbols=" + ticker))
                    .header("x-api-key", "69DeXzYODO5uwFXrwj38GxFCu06P98X9vO6PgkI1") // <== ввести свой API key!
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient()
                    .send(request, HttpResponse.BodyHandlers.ofString());
            return new JSONObject(response.body());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String[] getTickers(List<Security> portfolio) {
        String line = portfolio.stream()
                .map(Security::getTicker)
                .collect(Collectors.joining(","));
//        String[] lines = line.split("(?<=\\G\\w+,\\w+,\\w+,\\w+,\\w+,\\w+,\\w+,\\w+,\\w+),");
        return line.split("(?<=\\G\\w{1,4},\\w{1,4},\\w{1,4},\\w{1,4},\\w{1,4},\\w{1,4},\\w{1,4},\\w{1,4},\\w{1,4},\\w{1,4}),");
    }

}