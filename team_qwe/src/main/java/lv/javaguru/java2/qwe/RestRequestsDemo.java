package lv.javaguru.java2.qwe;

import lv.javaguru.java2.qwe.core.domain.Stock;
import lv.javaguru.java2.qwe.core.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class RestRequestsDemo {

    public static void main(String[] args) {

        RestTemplate restTemplate = new RestTemplate();

        //найти акцию
        String url = "http://localhost:8080/security/{ticker}";
        Stock stock = restTemplate.getForObject(url, Stock.class, "AAPL");
        System.out.println(stock);

        //Добавить акцию
        Stock stock1 = new Stock("BABA", "Alibaba", "Technology", "USD", 125.23, 0, 1.32);
        String url1 = "http://localhost:8080/security/";
        ResponseEntity<Stock> response = restTemplate.postForEntity(url1, stock1, Stock.class);
        System.out.println(response.getBody());

        //Удалить акцию
        restTemplate.delete(url, "BABA");

        //найти пользователя
        String url2 = "http://localhost:8080/user/{name}";
        User user = restTemplate.getForObject(url2, User.class, "Vladimir");
        System.out.println(user);
        user.getPortfolio().forEach(System.out::println);

        //Показать портфель пользователя
        String url3 = "http://localhost:8080/user/portfolio/{name}";
        List<?> portfolio = restTemplate.getForObject(url3, List.class, "Alexander");
        portfolio.forEach(System.out::println);

    }

}