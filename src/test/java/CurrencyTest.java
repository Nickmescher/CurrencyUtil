import model.CBRFCurrencyAPI;
import model.CurrencyAPI;
import model.CurrencyModel;
import org.junit.jupiter.api.Test;
        import static org.junit.jupiter.api.Assertions.*;

        import java.io.IOException;

        import org.dom4j.DocumentException;

public class CurrencyTest {

    @Test
    public void testGetCurrencyRate_ValidCurrencyAndDate_ReturnsCurrencyModel() throws IOException, DocumentException {
        String currencyCode = "USD";
        String date = "08/10/2022";

        CurrencyAPI currencyAPI = new CBRFCurrencyAPI();
        CurrencyModel currency = currencyAPI.getCurrencyRate(currencyCode, date);

        System.out.println(currency);
        assertNotNull(currency);
        assertEquals(currencyCode, currency.getCurrencyCode());
        assertEquals("Доллар США", currency.getName());
        assertTrue(currency.getRate() > 0);
    }

    @Test
    public void testGetCurrencyRate_InvalidCurrency_ReturnsNull() throws IOException, DocumentException {
        String currencyCode = "INVALID";
        String date = "2022-10-08";

        CurrencyAPI currencyAPI = new CBRFCurrencyAPI();
        CurrencyModel currency = currencyAPI.getCurrencyRate(currencyCode, date);

        assertNull(currency);
    }

    @Test
    public void testGetCurrencyName_ValidCurrency_ReturnsCurrencyName() throws IOException, DocumentException {
        String currencyCode = "USD";

        CurrencyAPI currencyAPI = new CBRFCurrencyAPI();
        String currencyName = currencyAPI.getCurrencyName(currencyCode);

        assertNotNull(currencyName);
        assertEquals("Доллар США", currencyName);
    }

    @Test
    public void testGetCurrencyName_InvalidCurrency_ReturnsNull() throws IOException, DocumentException {
        String currencyCode = "INVALID";

        CurrencyAPI currencyAPI = new CBRFCurrencyAPI();
        String currencyName = currencyAPI.getCurrencyName(currencyCode);

        assertNull(currencyName);
    }

}
