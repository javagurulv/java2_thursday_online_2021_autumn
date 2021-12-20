package lv.javaguru.java2.hospital.visit.core.services;

import lv.javaguru.java2.hospital.database.jpa.JpaVisitRepository;
import lv.javaguru.java2.hospital.database.visit_repository.VisitRepository;
import lv.javaguru.java2.hospital.visit.core.responses.CoreError;
import lv.javaguru.java2.hospital.visit.core.requests.DeleteVisitRequest;
import lv.javaguru.java2.hospital.visit.core.responses.DeleteVisitResponse;
import lv.javaguru.java2.hospital.visit.core.services.validators.DeleteVisitValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;


@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class DeleteVisitServiceTest {

    @Mock private JpaVisitRepository visitRepository;
    @Mock private DeleteVisitValidator validator;
    @InjectMocks private DeleteVisitService service;

    @Test
    public void shouldReturnErrorWhenVisitIdNotProvided() {
        DeleteVisitRequest request = new DeleteVisitRequest(null);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("ID", "Must not be empty!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        DeleteVisitResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().get(0).getField(), "ID");
        assertEquals(response.getErrors().get(0).getDescription(), "Must not be empty!");
    }

    @Test
    public void shouldDeleteVisitWithIdFromDatabase() {
        Mockito.when(validator.validate(any())).thenReturn(new ArrayList<>());
        DeleteVisitRequest request = new DeleteVisitRequest("1");
        DeleteVisitResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertTrue(response.isTrue());
    }

}