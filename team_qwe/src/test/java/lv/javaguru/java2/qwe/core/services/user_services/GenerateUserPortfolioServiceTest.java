package lv.javaguru.java2.qwe.core.services.user_services;

import lv.javaguru.java2.qwe.Cash;
import lv.javaguru.java2.qwe.Position;
import lv.javaguru.java2.qwe.Stock;
import lv.javaguru.java2.qwe.core.database.Database;
import lv.javaguru.java2.qwe.core.requests.user_requests.GenerateUserPortfolioRequest;
import lv.javaguru.java2.qwe.core.responses.CoreError;
import lv.javaguru.java2.qwe.core.responses.user_responses.GenerateUserPortfolioResponse;
import lv.javaguru.java2.qwe.dependency_injection.ApplicationContext;
import lv.javaguru.java2.qwe.dependency_injection.DIApplicationContextBuilder;
import lv.javaguru.java2.qwe.utils.UtilityMethods;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class GenerateUserPortfolioServiceTest {

    private final ApplicationContext appContext =
            new DIApplicationContextBuilder().build("lv.javaguru.java2.qwe");
    @Mock
    private Database database = appContext.getBean(Database.class);
    private final GenerateUserPortfolioService service =
            appContext.getBean(GenerateUserPortfolioService.class);

    @Test
    public void shouldReturnResponseWithErrorsWhenValidationFails1() {

        GenerateUserPortfolioRequest request = new GenerateUserPortfolioRequest("");
        List<CoreError> errors = List.of(
                new CoreError("UserName", "must not be empty!")
        );
        GenerateUserPortfolioResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(errors.size(), 1);
        assertTrue(errors.contains(new CoreError("UserName", "must not be empty!")));

        Mockito.verifyNoInteractions(database);
    }

    @Test
    public void shouldReturnResponseWithErrorsWhenValidationFails2() {

        UtilityMethods.importDataForTests(appContext);
        List<Position> portfolio = List.of(
                new Position(new Cash(), 100_000, 1),
                new Position(service.getUserData().getDatabase().getSecurityList().get(123), 100, 100)
        );
        service.getUserData().getUserList().get(0).setPortfolio(portfolio);
        GenerateUserPortfolioRequest request = new GenerateUserPortfolioRequest("Alexander");
        List<CoreError> errors = List.of(
                new CoreError("", "portfolio has been already generated for this user!")
        );
        GenerateUserPortfolioResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(errors.size(), 1);
        assertTrue(errors.contains(new CoreError("", "portfolio has been already generated for this user!")));

        Mockito.verifyNoInteractions(database);
    }

    @Test
    public void shouldReturnGeneratedUserPortfolio() {

        UtilityMethods.importDataForTests(appContext);
        GenerateUserPortfolioRequest request = new GenerateUserPortfolioRequest("Alexander");
        GenerateUserPortfolioResponse response = service.execute(request);

        List<Position> portfolio = List.of(
                new Position(new Stock("CenterPoint Energy Inc.", "Utilities", "USD", 26.34, 2.43, 1.41), 569, 26.34),
                new Position(new Stock("PPL Corporation", "Utilities", "USD", 29.74, 5.58, 1.32), 504, 29.74),
                new Position(new Stock("HCA Healthcare Inc", "Health Care", "USD", 249.34, 0.77, 1.46), 120, 249.34),
                new Position(new Stock("Align Technology Inc.", "Health Care", "USD", 681.57, 0, 1.46), 44, 681.57),
                new Position(new Stock("APA Corp.", "Energy", "USD", 16.07, 0.62, 2), 3266, 16.07),
                new Position(new Stock("Occidental Petroleum Corporation", "Energy", "USD", 21.95, 0.18, 1.69), 2391, 21.95),
                new Position(new Stock("Sysco Corporation", "Consumer Staples", "USD", 76.79, 2.45, 1.66), 97, 76.79),
                new Position(new Stock("Constellation Brands Inc. Class A", "Consumer Staples", "USD", 213.83, 1.42, 1.34), 35, 213.83),
                new Position(new Stock("DXC Technology Co.", "Technology", "USD", 35.44, 0, 1.59), 1058, 35.44),
                new Position(new Stock("Lam Research Corporation", "Technology", "USD", 565.97, 0.92, 1.50), 66, 565.97),
                new Position(new Stock("Simon Property Group Inc.", "Real Estate", "USD", 128.77, 4.66, 1.86), 524, 128.77),
                new Position(new Stock("Ventas Inc.", "Real Estate", "USD", 54.59, 3.3, 1.85), 1236, 54.59),
                new Position(new Stock("Freeport-McMoRan Inc.", "Materials", "USD", 32.8, 0.91, 1.59), 1371, 32.8),
                new Position(new Stock("LyondellBasell Industries NV", "Materials", "USD", 98.21, 4.6, 1.4), 458, 98.21),
                new Position(new Stock("Lincoln National Corporation", "Financials", "USD", 65.81, 2.55, 2.17), 911, 65.81),
                new Position(new Stock("Discover Financial Services", "Financials", "USD", 127.26, 1.57, 2.03), 471, 127.26),
                new Position(new Stock("Expedia Group Inc.", "Communications", "USD", 137.79, 0, 1.64), 163, 137.79),
                new Position(new Stock("Interpublic Group of Companies Inc.", "Communications", "USD", 36.45, 2.96, 1.36), 617, 36.45),
                new Position(new Stock("Boeing Company", "Industrials", "USD", 212.67, 0, 2.18), 352, 212.67),
                new Position(new Stock("United Airlines Holdings Inc.", "Industrials", "USD", 44.03, 0, 1.98), 1703, 44.03),
                new Position(new Stock("Caesars Entertainment Inc", "Consumer Discretionary", "USD", 85.64, 0, 2.93), 1021, 85.64),
                new Position(new Stock("Penn National Gaming Inc.", "Consumer Discretionary", "USD", 67.65, 0, 2.33), 1293, 67.65),
                new Position(new Cash(), 872.1, 1)
        );
        assertEquals(portfolio.size(), response.getPortfolio().size());
        IntStream.rangeClosed(0, portfolio.size() - 1)
                .forEach(i -> assertTrue(portfolio.contains(response.getPortfolio().get(i))));
        assertEquals(portfolio.get(22).getAmount(), response.getPortfolio().get(22).getAmount());
        assertEquals(LocalDate.now(), response.getPortfolioGenerationDate());

    }

}