package lv.javaguru.java2.qwe.core.services.validator;

import lv.javaguru.java2.qwe.core.domain.Bond;
import lv.javaguru.java2.qwe.acceptance_test.AcceptanceTestForDatabase;
import lv.javaguru.java2.qwe.core.database.Database;
import lv.javaguru.java2.qwe.core.requests.data_requests.AddBondRequest;
import lv.javaguru.java2.qwe.core.responses.CoreError;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AddBondValidatorTest extends AcceptanceTestForDatabase {

    private final Database database = super.getAppContext().getBean(Database.class);
    private final AddBondValidator validator = super.getAppContext().getBean(AddBondValidator.class);

    @Test
    public void shouldReturnEmptyList() {
        AddBondRequest request = new AddBondRequest(
                "GAZPRU",
                "Gazprom 4.75 31/12/2031",
                "Energy",
                "USD",
                "108.75",
                "4.75",
                "BBB+",
                "1000",
                "31/12/2031"
        );
        List<CoreError> errorList = validator.validate(request);
        assertTrue(errorList.isEmpty());
    }

    @Test
    public void shouldReturnNameError1() {
        AddBondRequest request = new AddBondRequest(
                "GAZPRU",
                "",
                "Energy",
                "USD",
                "108.75",
                "4.75",
                "BBB+",
                "1000",
                "31/12/2031"
        );
        List<CoreError> errorList = validator.validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "Name");
        assertEquals(errorList.get(0).getMessage(), "3 to 100 symbols required!");
    }

    @Ignore
    @Test
    public void shouldReturnNameError2() {
        database.addBond(new Bond("GAZPRU", "Gazprom 4.75 31/12/2031", "Energy", "USD", 108.75,
                4.75, "BBB+", 1000, "31/12/2031"));
        AddBondRequest request = new AddBondRequest(
                "GAZPRU",
                "Gazprom 4.75 31/12/2031",
                "Energy",
                "USD",
                "108.75",
                "4.75",
                "BBB+",
                "1000",
                "31/12/2031"
        );
        List<CoreError> errorList = validator.validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "Name");
        assertEquals(errorList.get(0).getMessage(), "security with such name already exists in the database!");
    }

    @Test
    public void shouldReturnIndustryError() {
        AddBondRequest request = new AddBondRequest(
                "GAZPRU",
                "Gazprom 4.75 31/12/2031",
                "",
                "USD",
                "108.75",
                "4.75",
                "BBB+",
                "1000",
                "31/12/2031"
        );
        List<CoreError> errorList = validator.validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "Industry");
        assertEquals(errorList.get(0).getMessage(), "is empty!");
    }

    @Test
    public void shouldReturnCurrencyError() {
        AddBondRequest request = new AddBondRequest(
                "GAZPRU",
                "Gazprom 4.75 31/12/2031",
                "Energy",
                "",
                "108.75",
                "4.75",
                "BBB+",
                "1000",
                "31/12/2031"
        );
        List<CoreError> errorList = validator.validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "Currency");
        assertEquals(errorList.get(0).getMessage(), "is empty!");
    }

    @Test
    public void shouldReturnMarketPriceError1() {
        AddBondRequest request = new AddBondRequest(
                "GAZPRU",
                "Gazprom 4.75 31/12/2031",
                "Energy",
                "USD",
                "1o8.75",
                "4.75",
                "BBB+",
                "1000",
                "31/12/2031"
        );
        List<CoreError> errorList = validator.validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "Market price");
        assertEquals(errorList.get(0).getMessage(), "wrong format! Must be double!");
    }

    @Test
    public void shouldReturnMarketPriceError2() {
        AddBondRequest request = new AddBondRequest(
                "GAZPRU",
                "Gazprom 4.75 31/12/2031",
                "Energy",
                "USD",
                "-108.75",
                "4.75",
                "BBB+",
                "1000",
                "31/12/2031"
        );
        List<CoreError> errorList = validator.validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "Market price");
        assertEquals(errorList.get(0).getMessage(), "cannot be negative!");
    }

    @Test
    public void shouldReturnCouponError1() {
        AddBondRequest request = new AddBondRequest(
                "GAZPRU",
                "Gazprom 4.75 31/12/2031",
                "Energy",
                "USD",
                "108.75",
                "",
                "BBB+",
                "1000",
                "31/12/2031"
        );
        List<CoreError> errorList = validator.validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "Coupon");
        assertEquals(errorList.get(0).getMessage(), "wrong format! Must be double!");
    }

    @Test
    public void shouldReturnCouponError2() {
        AddBondRequest request = new AddBondRequest(
                "GAZPRU",
                "Gazprom 4.75 31/12/2031",
                "Energy",
                "USD",
                "108.75",
                "-4.75",
                "BBB+",
                "1000",
                "31/12/2031"
        );
        List<CoreError> errorList = validator.validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "Coupon");
        assertEquals(errorList.get(0).getMessage(), "cannot be negative!");
    }

    @Test
    public void shouldReturnRatingError1() {
        AddBondRequest request = new AddBondRequest(
                "GAZPRU",
                "Gazprom 4.75 31/12/2031",
                "Energy",
                "USD",
                "108.75",
                "4.75",
                "BBBB+",
                "1000",
                "31/12/2031"
        );
        List<CoreError> errorList = validator.validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "Rating");
        assertEquals(errorList.get(0).getMessage(), "1 to 4 symbols are required!");
    }

    @Test
    public void shouldReturnRatingError2() {
        AddBondRequest request = new AddBondRequest(
                "GAZPRU",
                "Gazprom 4.75 31/12/2031",
                "Energy",
                "USD",
                "108.75",
                "4.75",
                "",
                "1000",
                "31/12/2031"
        );
        List<CoreError> errorList = validator.validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "Rating");
        assertEquals(errorList.get(0).getMessage(), "1 to 4 symbols are required!");
    }

    @Test
    public void shouldReturnNominalError1() {
        AddBondRequest request = new AddBondRequest(
                "GAZPRU",
                "Gazprom 4.75 31/12/2031",
                "Energy",
                "USD",
                "108.75",
                "4.75",
                "BBB+",
                "",
                "31/12/2031"
        );
        List<CoreError> errorList = validator.validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "Nominal");
        assertEquals(errorList.get(0).getMessage(), "wrong format! Must be integer!");
    }

    @Test
    public void shouldReturnNominalError2() {
        AddBondRequest request = new AddBondRequest(
                "GAZPRU",
                "Gazprom 4.75 31/12/2031",
                "Energy",
                "USD",
                "108.75",
                "4.75",
                "BBB+",
                "-1000",
                "31/12/2031"
        );
        List<CoreError> errorList = validator.validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "Nominal");
        assertEquals(errorList.get(0).getMessage(), "cannot be negative!");
    }

    @Test
    public void shouldReturnMaturityError1() {
        AddBondRequest request = new AddBondRequest(
                "GAZPRU",
                "Gazprom 4.75 31/12/2031",
                "Energy",
                "USD",
                "108.75",
                "4.75",
                "BBB+",
                "1000",
                "31/12/20031"
        );
        List<CoreError> errorList = validator.validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "Maturity");
        assertEquals(errorList.get(0).getMessage(), "10 symbols are required!");
    }

    @Test
    public void shouldReturnMaturityError2() {
        AddBondRequest request = new AddBondRequest(
                "GAZPRU",
                "Gazprom 4.75 31/12/2031",
                "Energy",
                "USD",
                "108.75",
                "4.75",
                "BBB+",
                "1000",
                "31122031"
        );
        List<CoreError> errorList = validator.validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "Maturity");
        assertEquals(errorList.get(0).getMessage(), "10 symbols are required!");
    }

    @Test
    public void shouldReturnNameCurrencyRatingNominalErrors() {
        AddBondRequest request = new AddBondRequest(
                "GAZPRU",
                "Ga",
                "Energy",
                "",
                "108.75",
                "4.75",
                "BBBB+",
                "-1000",
                "31/12/2031"
        );
        List<CoreError> errorList = validator.validate(request);
        assertEquals(errorList.size(), 4);
        assertTrue(errorList.contains(new CoreError("Name", "3 to 100 symbols required!")));
        assertTrue(errorList.contains(new CoreError("Currency", "is empty!")));
        assertTrue(errorList.contains(new CoreError("Rating", "1 to 4 symbols are required!")));
        assertTrue(errorList.contains(new CoreError("Nominal", "cannot be negative!")));
    }

    @Test
    public void shouldReturnAllErrors() {
        AddBondRequest request = new AddBondRequest(
                "GAZPRU",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                ""
        );
        List<CoreError> errorList = validator.validate(request);
        assertEquals(errorList.size(), 8);
        assertTrue(errorList.contains(new CoreError("Name", "3 to 100 symbols required!")));
        assertTrue(errorList.contains(new CoreError("Industry", "is empty!")));
        assertTrue(errorList.contains(new CoreError("Currency", "is empty!")));
        assertTrue(errorList.contains(new CoreError("Market price", "wrong format! Must be double!")));
        assertTrue(errorList.contains(new CoreError("Coupon", "wrong format! Must be double!")));
        assertTrue(errorList.contains(new CoreError("Rating", "1 to 4 symbols are required!")));
        assertTrue(errorList.contains(new CoreError("Nominal", "wrong format! Must be integer!")));
        assertTrue(errorList.contains(new CoreError("Maturity", "10 symbols are required!")));
    }

}