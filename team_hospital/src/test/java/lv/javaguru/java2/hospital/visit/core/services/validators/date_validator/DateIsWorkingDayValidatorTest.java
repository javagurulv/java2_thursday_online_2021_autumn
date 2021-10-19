package lv.javaguru.java2.hospital.visit.core.services.validators.date_validator;

import lv.javaguru.java2.hospital.visit.core.responses.CoreError;
import lv.javaguru.java2.hospital.visit.core.services.validators.existence.search_criteria.GetVisitDate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class DateIsWorkingDayValidatorTest {

    @Mock private GetVisitDate getVisitDate;
    @InjectMocks private DateIsWorkingDayValidator workingDayValidator;

    @Test
    public void shouldReturnDateWorkingDayError() {
        String date = "16/01/2022 15:00";
        Mockito.when(getVisitDate.getVisitDateFromString(date)).thenReturn(new Date(date));
        Optional<CoreError> error = workingDayValidator.validate(date);
        assertTrue(error.isPresent());
        assertEquals(error.get().getField(), "Date");
        assertEquals(error.get().getDescription(), "is not working day!");
    }
}