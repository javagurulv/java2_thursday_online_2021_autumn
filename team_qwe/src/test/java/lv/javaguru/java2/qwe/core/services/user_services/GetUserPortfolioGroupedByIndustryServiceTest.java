package lv.javaguru.java2.qwe.core.services.user_services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

/*
@RunWith(MockitoJUnitRunner.class)
public class GetUserPortfolioGroupedByIndustryServiceTest {

    @Mock private UserData userData;
    @Mock private GetUserPortfolioGroupedByIndustryValidator validator;
    @InjectMocks private GetUserPortfolioGroupedByIndustryService service;

    @Test
    public void shouldReturnResponseWithErrorsWhenValidationFails() {
        GetUserPortfolioGroupedByIndustryRequest request =
                new GetUserPortfolioGroupedByIndustryRequest("");
        List<CoreError> errors = List.of(
                new CoreError("UserName", "must not be empty!")
        );
        Mockito.when(validator.validate(request)).thenReturn(errors);
        GetUserPortfolioGroupedByIndustryResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 1);
        assertEquals(errors.get(0).getField(), "UserName");
        assertEquals(errors.get(0).getMessage(), "must not be empty!");
        Mockito.verify(validator).validate(request);
        Mockito.verify(validator).validate(any());
    }

    @Test
    public void shouldReturnUserPortfolioGroupedByEachIndustryResult() {
        GetUserPortfolioGroupedByIndustryRequest request =
                new GetUserPortfolioGroupedByIndustryRequest("Alexander");
        Mockito.when(validator.validate(any())).thenReturn(new ArrayList<>());

        User user = new User(1, "Alexander", 25, Type.SUPER_RICH, 1_000_000);
        Map<String, List<String>> map = Map.ofEntries(
                Map.entry("Technology", List.of("Apple", "Intel")),
                Map.entry("Energy", List.of("Gazprom", "Total")),
                Map.entry("Health care", List.of("Pfizer", "Johnson&Johnson"))
        );
        Mockito.when(userData.findUserByName("Alexander")).thenReturn(Optional.of(user));
        Mockito.when(userData.getUserPortfolioGroupedByIndustry(user)).thenReturn(map);

        GetUserPortfolioGroupedByIndustryResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        Assert.assertEquals(map, response.getIndustryMap());
    }

}*/
