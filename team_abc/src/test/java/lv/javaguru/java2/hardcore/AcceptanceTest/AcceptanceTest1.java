package lv.javaguru.java2.hardcore.AcceptanceTest;


import lv.javaguru.java2.hardcore.config.SpringCoreConfiguration;
import lv.javaguru.java2.hardcore.core.requests.lot.AddLotRequest;
import lv.javaguru.java2.hardcore.core.requests.lot.GetAllLotsRequest;
import lv.javaguru.java2.hardcore.core.response.lot.GetAllLotsResponse;
import lv.javaguru.java2.hardcore.core.services.lot.AddLotService;
import lv.javaguru.java2.hardcore.core.services.lot.GetAllLotsService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class AcceptanceTest1 {

//NON FINISHED TEST
private ApplicationContext appContext;

    @Before
    public void setup(){
        appContext = new AnnotationConfigApplicationContext(SpringCoreConfiguration.class);
    }

    @Test
    public void shouldReturnCorrectLotList() {
        AddLotRequest request = new AddLotRequest("Lot1", new BigDecimal("2.29"));
        getAddLotService().execute(request);

        AddLotRequest request2 = new AddLotRequest("Lot2", new BigDecimal("2.40"));
        getAddLotService().execute(request);

        GetAllLotsResponse response = getAllLotsService().execute(new GetAllLotsRequest());
        assertEquals(response.getGetAllLots().size(),2);

    }

    private AddLotService getAddLotService() {
        return this.appContext.getBean(AddLotService.class);
    }

    private GetAllLotsService getAllLotsService() {
        return this.appContext.getBean(GetAllLotsService.class);
    }

}