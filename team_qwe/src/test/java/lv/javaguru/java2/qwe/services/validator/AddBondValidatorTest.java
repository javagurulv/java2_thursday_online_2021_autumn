package lv.javaguru.java2.qwe.services.validator;

import lv.javaguru.java2.qwe.Bond;
import lv.javaguru.java2.qwe.database.Database;
import lv.javaguru.java2.qwe.database.DatabaseImpl;
import lv.javaguru.java2.qwe.request.AddBondRequest;
import org.junit.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AddBondValidatorTest {

    private final Database database = new DatabaseImpl();
    private final AddBondValidator validator = new AddBondValidator(database);

    @Test
    public void shouldReturnEmptyList() {
        AddBondRequest request = new AddBondRequest(
                "Gazprom 4.75 31/12/2031",
                "Energy",
                "USD",
                "108.75",
                "4.75",
                "BBB+",
                "1000",
                "31/12/2031"
        );
        List<String> errorList = validator.validate(request);
        assertTrue(errorList.isEmpty());
    }

    @Test
    public void shouldReturnNameError1() {
        AddBondRequest request = new AddBondRequest(
                "",
                "Energy",
                "USD",
                "108.75",
                "4.75",
                "BBB+",
                "1000",
                "31/12/2031"
        );
        List<String> errorList = validator.validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0), "Name: 3 to 100 symbols required!");
    }

    @Test
    public void shouldReturnNameError2() {
        database.addBond(new Bond("Gazprom 4.75 31/12/2031", "Energy", "USD", 108.75,
                4.75, "BBB+", 1000, "31/12/2031"));
        AddBondRequest request = new AddBondRequest(
                "Gazprom 4.75 31/12/2031",
                "Energy",
                "USD",
                "108.75",
                "4.75",
                "BBB+",
                "1000",
                "31/12/2031"
        );
        List<String> errorList = validator.validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0), "Name: security with such name already exists in the database!");
    }

    @Test
    public void shouldReturnIndustryError() {
        AddBondRequest request = new AddBondRequest(
                "Gazprom 4.75 31/12/2031",
                "",
                "USD",
                "108.75",
                "4.75",
                "BBB+",
                "1000",
                "31/12/2031"
        );
        List<String> errorList = validator.validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0), "Industry: is empty!");
    }

    @Test
    public void shouldReturnCurrencyError() {
        AddBondRequest request = new AddBondRequest(
                "Gazprom 4.75 31/12/2031",
                "Energy",
                "",
                "108.75",
                "4.75",
                "BBB+",
                "1000",
                "31/12/2031"
        );
        List<String> errorList = validator.validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0), "Currency: is empty!");
    }

    @Test
    public void shouldReturnMarketPriceError1() {
        AddBondRequest request = new AddBondRequest(
                "Gazprom 4.75 31/12/2031",
                "Energy",
                "USD",
                "1o8.75",
                "4.75",
                "BBB+",
                "1000",
                "31/12/2031"
        );
        List<String> errorList = validator.validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0), "Market price: wrong format! Must be double!");
    }

    @Test
    public void shouldReturnMarketPriceError2() {
        AddBondRequest request = new AddBondRequest(
                "Gazprom 4.75 31/12/2031",
                "Energy",
                "USD",
                "-108.75",
                "4.75",
                "BBB+",
                "1000",
                "31/12/2031"
        );
        List<String> errorList = validator.validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0), "Market price: cannot be negative!");
    }

    @Test
    public void shouldReturnCouponError1() {
        AddBondRequest request = new AddBondRequest(
                "Gazprom 4.75 31/12/2031",
                "Energy",
                "USD",
                "108.75",
                "",
                "BBB+",
                "1000",
                "31/12/2031"
        );
        List<String> errorList = validator.validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0), "Coupon: wrong format! Must be double!");
    }

    @Test
    public void shouldReturnCouponError2() {
        AddBondRequest request = new AddBondRequest(
                "Gazprom 4.75 31/12/2031",
                "Energy",
                "USD",
                "108.75",
                "-4.75",
                "BBB+",
                "1000",
                "31/12/2031"
        );
        List<String> errorList = validator.validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0), "Coupon: cannot be negative!");
    }

    @Test
    public void shouldReturnRatingError1() {
        AddBondRequest request = new AddBondRequest(
                "Gazprom 4.75 31/12/2031",
                "Energy",
                "USD",
                "108.75",
                "4.75",
                "BBBB+",
                "1000",
                "31/12/2031"
        );
        List<String> errorList = validator.validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0), "Rating: 1 to 4 symbols are required!");
    }

    @Test
    public void shouldReturnRatingError2() {
        AddBondRequest request = new AddBondRequest(
                "Gazprom 4.75 31/12/2031",
                "Energy",
                "USD",
                "108.75",
                "4.75",
                "",
                "1000",
                "31/12/2031"
        );
        List<String> errorList = validator.validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0), "Rating: 1 to 4 symbols are required!");
    }

    @Test
    public void shouldReturnNominalError1() {
        AddBondRequest request = new AddBondRequest(
                "Gazprom 4.75 31/12/2031",
                "Energy",
                "USD",
                "108.75",
                "4.75",
                "BBB+",
                "",
                "31/12/2031"
        );
        List<String> errorList = validator.validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0), "Nominal: wrong format! Must be integer!");
    }

    @Test
    public void shouldReturnNominalError2() {
        AddBondRequest request = new AddBondRequest(
                "Gazprom 4.75 31/12/2031",
                "Energy",
                "USD",
                "108.75",
                "4.75",
                "BBB+",
                "-1000",
                "31/12/2031"
        );
        List<String> errorList = validator.validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0), "Nominal: cannot be negative!");
    }

    @Test
    public void shouldReturnMaturityError1() {
        AddBondRequest request = new AddBondRequest(
                "Gazprom 4.75 31/12/2031",
                "Energy",
                "USD",
                "108.75",
                "4.75",
                "BBB+",
                "1000",
                "31/12/20031"
        );
        List<String> errorList = validator.validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0), "Maturity: 10 symbols are required!");
    }

    @Test
    public void shouldReturnMaturityError2() {
        AddBondRequest request = new AddBondRequest(
                "Gazprom 4.75 31/12/2031",
                "Energy",
                "USD",
                "108.75",
                "4.75",
                "BBB+",
                "1000",
                "31122031"
        );
        List<String> errorList = validator.validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0), "Maturity: 10 symbols are required!");
    }

    @Test
    public void shouldReturnNameCurrencyRatingNominalErrors() {
        AddBondRequest request = new AddBondRequest(
                "Ga",
                "Energy",
                "",
                "108.75",
                "4.75",
                "BBBB+",
                "-1000",
                "31/12/2031"
        );
        List<String> errorList = validator.validate(request);
        assertEquals(errorList.size(), 4);
        assertTrue(errorList.contains("Name: 3 to 100 symbols required!"));
        assertTrue(errorList.contains("Currency: is empty!"));
        assertTrue(errorList.contains("Rating: 1 to 4 symbols are required!"));
        assertTrue(errorList.contains("Nominal: cannot be negative!"));
    }

    @Test
    public void shouldReturnAllErrors() {
        AddBondRequest request = new AddBondRequest(
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                ""
        );
        List<String> errorList = validator.validate(request);
        assertEquals(errorList.size(), 8);
        assertTrue(errorList.contains("Name: 3 to 100 symbols required!"));
        assertTrue(errorList.contains("Industry: is empty!"));
        assertTrue(errorList.contains("Currency: is empty!"));
        assertTrue(errorList.contains("Market price: wrong format! Must be double!"));
        assertTrue(errorList.contains("Coupon: wrong format! Must be double!"));
        assertTrue(errorList.contains("Rating: 1 to 4 symbols are required!"));
        assertTrue(errorList.contains("Nominal: wrong format! Must be integer!"));
        assertTrue(errorList.contains("Maturity: 10 symbols are required!"));
    }

}