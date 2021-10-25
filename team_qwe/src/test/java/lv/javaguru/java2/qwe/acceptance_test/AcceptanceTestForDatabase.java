package lv.javaguru.java2.qwe.acceptance_test;

import lv.javaguru.java2.qwe.core.domain.Bond;
import lv.javaguru.java2.qwe.config.AppConfiguration;
import lv.javaguru.java2.qwe.core.domain.Stock;
import lv.javaguru.java2.qwe.core.requests.data_requests.*;
import lv.javaguru.java2.qwe.core.responses.data_responses.FilterStocksByMultipleParametersResponse;
import lv.javaguru.java2.qwe.core.responses.data_responses.FindSecurityByNameResponse;
import lv.javaguru.java2.qwe.core.responses.data_responses.GetAllSecurityListResponse;
import lv.javaguru.java2.qwe.core.responses.data_responses.RemoveSecurityResponse;
import lv.javaguru.java2.qwe.core.services.data_services.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.junit.Test;

import static org.junit.Assert.*;
import static java.util.List.*;

public class AcceptanceTestForDatabase {

    private final ApplicationContext appContext =
            new AnnotationConfigApplicationContext(AppConfiguration.class);

    public ApplicationContext getAppContext() {
        return appContext;
    }

    @Test
    public void addSecuritiesToDatabaseTest() {
        AddStockRequest request1 = new AddStockRequest(
                "BABA US", "Alibaba", "Technology", "USD",
                "160.13", "0", "1.32"
        );
        AddStockRequest request2 = new AddStockRequest(
                "INTC US", "Intel", "Technology", "USD",
                "53.73", "2.o5", "1.12"
        );
        AddBondRequest request3 = new AddBondRequest(
                "GAZPRU", "Gazprom 4.75 30/12/2031", "Energy", "USD", "108.75",
                "4.75", "BBB+", "1000", "30/12/2031"
        );
        AddBondRequest request4 = new AddBondRequest(
                "VALEBZ", "Vale 2.875 30/06/2027", "Materials", "USD", "104.25",
                "2.875", "BBB", "1000", "30/12/20031"
        );
        getAddStockService().execute(request1);
        getAddStockService().execute(request2);
        getAddBondService().execute(request3);
        getAddBondService().execute(request4);
        GetAllSecurityListResponse response = getAllSecurityListService().execute(new GetAllSecurityListRequest());

        assertEquals(3, response.getList().size());
        assertEquals("Alibaba", response.getList().get(1).getName());
        assertEquals("Gazprom 4.75 30/12/2031", response.getList().get(2).getName());
    }

    @Test
    public void removeSecuritiesFromDatabaseTest() {
        AddStockRequest request1 = new AddStockRequest(
                "BABA US", "Alibaba", "Technology", "USD",
                "160.13", "0", "1.32"
        );
        AddBondRequest request2 = new AddBondRequest(
                "GAZPRU", "Gazprom 4.75 30/12/2031", "Energy", "USD", "108.75",
                "4.75", "BBB+", "1000", "30/12/2031"
        );
        RemoveSecurityRequest request3 = new RemoveSecurityRequest("Alibaba");
        RemoveSecurityRequest request4 = new RemoveSecurityRequest("Gazprom");
        getAddStockService().execute(request1);
        getAddBondService().execute(request2);
        RemoveSecurityResponse response1 = getRemoveSecurityService().execute(request3);
        RemoveSecurityResponse response2 = getRemoveSecurityService().execute(request4);
        GetAllSecurityListResponse response3 = getAllSecurityListService().execute(new GetAllSecurityListRequest());

        assertTrue(response1.isRemoved());
        assertFalse(response2.isRemoved());
        assertEquals(2, response3.getList().size());
        assertEquals("Gazprom 4.75 30/12/2031", response3.getList().get(1).getName());
    }

    @Test
    public void findSecurityByNameInDatabaseTest() {
        AddStockRequest request1 = new AddStockRequest(
                "BABA", "Alibaba", "Technology", "USD",
                "160.13", "0", "1.32"
        );
        AddBondRequest request2 = new AddBondRequest(
                "GAZPRU", "Gazprom 4.75 30/12/2031", "Energy", "USD", "108.75",
                "4.75", "BBB+", "1000", "30/12/2031"
        );
        FindSecurityByNameRequest request3 = new FindSecurityByNameRequest("Gazprom 4.75 30/12/2031");
        FindSecurityByNameRequest request4 = new FindSecurityByNameRequest("Intel");
        getAddStockService().execute(request1);
        getAddBondService().execute(request2);
        FindSecurityByNameResponse response1 = getFindSecurityByNameService().execute(request3);
        FindSecurityByNameResponse response2 = getFindSecurityByNameService().execute(request4);

        assertEquals(new Bond(
                "GAZPRU", "Gazprom 4.75 30/12/2031", "Energy", "USD", 108.75,
                4.75, "BBB+", 1000, "30/12/2031"
        ), response1.getSecurity());
        assertNull(response2.getSecurity());
    }

    @Test
    public void filterStocksByMultipleParametersTest() {
        AddStockRequest request1 = new AddStockRequest(
                "BABA US", "Alibaba", "Technology", "USD",
                "160.13", "0", "1.32"
        );
        AddStockRequest request2 = new AddStockRequest(
                "INTC US", "Intel", "Technology", "USD",
                "53.73", "2.05", "0.9"
        );
        AddStockRequest request3 = new AddStockRequest(
                "PBR US", "Petrobras", "Energy", "USD",
                "9.57", "4.5", "1.23"
        );
        AddStockRequest request4 = new AddStockRequest(
                "FB US", "Facebook", "Communications", "USD",
                "359.23", "0", "0.87"
        );
        FilterStocksByMultipleParametersRequest request5 = new FilterStocksByMultipleParametersRequest(
                of(
                        new FilterStocksByIndustryRequest("Technology"),
                        new OrderingRequest("Name", "DESCENDING")
                )
        );
        FilterStocksByMultipleParametersRequest request6 = new FilterStocksByMultipleParametersRequest(
                of(
                        new FilterStocksByAnyDoubleParameterRequest("Dividend", ">", "0"),
                        new FilterStocksByAnyDoubleParameterRequest("Risk weight", "<", "1")
                )
        );
        getAddStockService().execute(request1);
        getAddStockService().execute(request2);
        getAddStockService().execute(request3);
        getAddStockService().execute(request4);
        FilterStocksByMultipleParametersResponse response1 =
                getFilterStocksByMultipleParametersService().execute(request5);
        FilterStocksByMultipleParametersResponse response2 =
                getFilterStocksByMultipleParametersService().execute(request6);

        assertEquals(of(
                new Stock("INTC US", "Intel", "Technology", "USD", 53.73, 2.05, 0.9),
                new Stock("BABA US", "Alibaba", "Technology", "USD", 160.13, 0, 1.32)
        ), response1.getList());

        assertEquals(of(
                new Stock("INTC US", "Intel", "Technology", "USD", 53.73, 2.05, 0.9)
        ), response2.getList());
    }

    private AddStockService getAddStockService() {
        return appContext.getBean(AddStockService.class);
    }

    private AddBondService getAddBondService() {
        return appContext.getBean(AddBondService.class);
    }

    private GetAllSecurityListService getAllSecurityListService() {
        return appContext.getBean(GetAllSecurityListService.class);
    }

    private RemoveSecurityService getRemoveSecurityService() {
        return appContext.getBean(RemoveSecurityService.class);
    }

    private FindSecurityByNameService getFindSecurityByNameService() {
        return appContext.getBean(FindSecurityByNameService.class);
    }

    private FilterStocksByMultipleParametersService getFilterStocksByMultipleParametersService() {
        return appContext.getBean(FilterStocksByMultipleParametersService.class);
    }

}