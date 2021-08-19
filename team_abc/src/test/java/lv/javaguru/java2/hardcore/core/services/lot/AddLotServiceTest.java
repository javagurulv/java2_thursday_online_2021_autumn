package lv.javaguru.java2.hardcore.core.services.lot;

import lv.javaguru.java2.hardcore.core.database.LotRepository;
import lv.javaguru.java2.hardcore.core.domain.Lot;
import lv.javaguru.java2.hardcore.core.requests.lot.AddLotRequest;
import lv.javaguru.java2.hardcore.core.response.CoreError;
import lv.javaguru.java2.hardcore.core.response.lot.AddLotResponse;
import lv.javaguru.java2.hardcore.core.services.lot.validators.AddLotValidator;
import lv.javaguru.java2.hardcore.core.services.user.LoginService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(MockitoJUnitRunner.class)
public class AddLotServiceTest {

    @Mock private LotRepository lotRepository;
    @Mock private Lot lot;
    @Mock private AddLotValidator lotValidator;
    @Mock private LoginService loginService;
    @InjectMocks
    private AddLotService service;


    @Test
    public void shouldReturntValidatorErrors() {
        List<CoreError> errors = new ArrayList<>();
        CoreError error = new CoreError("test1","test1");
        errors.add(error);
        AddLotRequest request = new AddLotRequest("abc", BigDecimal.ONE);

        Mockito.when(lotValidator.validate(request)).thenReturn(errors);

        AddLotResponse response = service.execute(request);

        assertTrue(response.hasErrors());

        assertEquals(response.getErrors().size(), 1);
    }

    @Test
    public void shouldReturnCorrectResponce() {
        AddLotRequest request = new AddLotRequest("abc", BigDecimal.ONE);
        Mockito.when(lotValidator.validate(request)).thenReturn(List.of());
        AddLotResponse response = service.execute(request);
        Lot lot = response.getNewLot();
        Mockito.verify(lotRepository).saveLot(lot);
    }
}