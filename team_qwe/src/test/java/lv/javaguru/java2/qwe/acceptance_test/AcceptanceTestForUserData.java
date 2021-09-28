package lv.javaguru.java2.qwe.acceptance_test;

import lv.javaguru.java2.qwe.*;
import lv.javaguru.java2.qwe.core.requests.user_requests.*;
import lv.javaguru.java2.qwe.core.responses.user_responses.*;
import lv.javaguru.java2.qwe.core.services.user_services.*;
import lv.javaguru.java2.qwe.dependency_injection.ApplicationContext;
import lv.javaguru.java2.qwe.dependency_injection.DIApplicationContextBuilder;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static java.util.Map.*;
import static java.util.List.*;

public class AcceptanceTestForUserData {

    @Before
    public void init() {
        getAllUserListService().getUserData().getUserList().get(0).setPortfolio(
                of(
                        new Position(new Stock("APA Corp.", "Energy", "USD", 16.07, 0.62, 2), 3266, 16.07),
                        new Position(new Stock("Freeport-McMoRan Inc.", "Materials", "USD", 32.8, 0.91, 1.59), 1371, 32.8),
                        new Position(new Stock("Boeing Company", "Industrials", "USD", 212.67, 0, 2.18), 352, 212.67),
                        new Position(new Stock("Occidental Petroleum Corporation", "Energy", "USD", 21.95, 0.18, 1.69), 2391, 21.95),
                        new Position(new Cash(), 50125.15, 1)
                )
        );
    }

    private final ApplicationContext appContext =
            new DIApplicationContextBuilder().build("lv.javaguru.java2.qwe");

    @Test
    public void addUserToUserDataTest() {
        AddUserRequest request1 = new AddUserRequest(
                "Marina", "42", "WEALTHY", "500000"
        );
        AddUserRequest request2 = new AddUserRequest(
                "Michael", "12", "LOWER_MIDDLE", "25000"
        );
        getAddUserService().execute(request1);
        getAddUserService().execute(request2);
        GetAllUserListRequest request3 = new GetAllUserListRequest();
        GetAllUserListResponse response = getAllUserListService().execute(request3);

        assertEquals(5, response.getList().size());
    }

    @Test
    public void findUserByNameTest() {
        FindUserByNameRequest request1 = new FindUserByNameRequest("Vladimir");
        FindUserByNameResponse response1 = getFindUserByName().execute(request1);
        FindUserByNameRequest request2 = new FindUserByNameRequest("Alex");
        FindUserByNameResponse response2 = getFindUserByName().execute(request2);

        assertEquals(new User(
                "Vladimir", 78, Type.LOWER_MIDDLE, 30000
        ), response1.getUser());
        assertNull(response2.getUser());
    }

    @Test
    public void getUserInvestmentsByEachIndustryTest() {
        GetUserInvestmentsByEachIndustryRequest request1 =
                new GetUserInvestmentsByEachIndustryRequest("Alexander");
        GetUserInvestmentsByEachIndustryResponse response1 =
                getUserInvestmentsByEachIndustryService().execute(request1);
        GetUserInvestmentsByEachIndustryRequest request2 =
                new GetUserInvestmentsByEachIndustryRequest("Michael");
        GetUserInvestmentsByEachIndustryResponse response2 =
                getUserInvestmentsByEachIndustryService().execute(request2);
        Map<String, Double> map = ofEntries(
                entry("Energy", 104_967.07),
                entry("Materials", 44_968.8),
                entry("Industrials", 74_859.84),
                entry("Cash", 50_125.15)
        );

        assertEquals(map.get("Energy"), response1.getInvestmentMap().get("Energy"), 0.01);
        assertEquals(map.get("Materials"), response1.getInvestmentMap().get("Materials"), 0.01);
        assertEquals(map.get("Industrials"), response1.getInvestmentMap().get("Industrials"), 0.01);
        assertEquals(map.get("Cash"), response1.getInvestmentMap().get("Cash"), 0.01);
        assertNull(response2.getInvestmentMap());
    }

    @Test
    public void getUserPortfolioGroupedByIndustryServiceTest() {
        GetUserPortfolioGroupedByIndustryRequest request1 =
                new GetUserPortfolioGroupedByIndustryRequest("Alexander");
        GetUserPortfolioGroupedByIndustryResponse response1 =
                getUserPortfolioGroupedByIndustryService().execute(request1);
        GetUserPortfolioGroupedByIndustryRequest request2 =
                new GetUserPortfolioGroupedByIndustryRequest("Michael");
        GetUserPortfolioGroupedByIndustryResponse response2 =
                getUserPortfolioGroupedByIndustryService().execute(request2);
        Map<String, List<String>> map = ofEntries(
                entry("Energy", List.of("APA Corp.", "Occidental Petroleum Corporation")),
                entry("Materials", of("Freeport-McMoRan Inc.")),
                entry("Industrials", of("Boeing Company")),
                entry("Cash", of("Cash"))
        );

        assertEquals(map.get("Energy"), response1.getIndustryMap().get("Energy"));
        assertEquals(map.get("Materials"), response1.getIndustryMap().get("Materials"));
        assertEquals(map.get("Industrials"), response1.getIndustryMap().get("Industrials"));
        assertEquals(map.get("Cash"), response1.getIndustryMap().get("Cash"));
        assertNull(response2.getIndustryMap());
    }

    @Test
    public void getUserPortfolioTest() {
        GetUserPortfolioRequest request1 =
                new GetUserPortfolioRequest("Alexander");
        GetUserPortfolioResponse response1 =
                getUserPortfolioService().execute(request1);
        GetUserPortfolioRequest request2 =
                new GetUserPortfolioRequest("Michael");
        GetUserPortfolioResponse response2 =
                getUserPortfolioService().execute(request2);
        List<Position> list = of(
                new Position(new Stock("APA Corp.", "Energy", "USD", 16.07, 0.62, 2), 3266, 16.07),
                new Position(new Stock("Freeport-McMoRan Inc.", "Materials", "USD", 32.8, 0.91, 1.59), 1371, 32.8),
                new Position(new Stock("Boeing Company", "Industrials", "USD", 212.67, 0, 2.18), 352, 212.67),
                new Position(new Stock("Occidental Petroleum Corporation", "Energy", "USD", 21.95, 0.18, 1.69), 2391, 21.95),
                new Position(new Cash(), 50125.15, 1)
        );

        assertEquals(list, response1.getPortfolio());
        assertTrue(response2.getPortfolio().isEmpty());
    }

    private AddUserService getAddUserService() {
        return appContext.getBean(AddUserService.class);
    }

    private GetAllUserListService getAllUserListService() {
        return appContext.getBean(GetAllUserListService.class);
    }

    private FindUserByNameService getFindUserByName() {
        return appContext.getBean(FindUserByNameService.class);
    }

    private GetUserInvestmentsByEachIndustryService getUserInvestmentsByEachIndustryService() {
        return appContext.getBean(GetUserInvestmentsByEachIndustryService.class);
    }

    private GetUserPortfolioGroupedByIndustryService getUserPortfolioGroupedByIndustryService() {
        return appContext.getBean(GetUserPortfolioGroupedByIndustryService.class);
    }

    private GetUserPortfolioService getUserPortfolioService() {
        return appContext.getBean(GetUserPortfolioService.class);
    }

}