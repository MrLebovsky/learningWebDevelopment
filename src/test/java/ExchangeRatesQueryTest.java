import com.learning.web.development.services.rates.ExchangeRatesQuery;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ Instant.class })
public class ExchangeRatesQueryTest {

    @Test
    public void testGetCourses() {
        /*String instantExpected = "2014-12-22T10:15:30Z";
        Clock clock = Clock.fixed(Instant.parse(instantExpected), ZoneId.of("UTC"));
        Instant instant = Instant.now(clock);
        mockStatic(Instant.class);
        when(Instant.now()).thenReturn(instant);*/

        System.out.println(Instant.now().toString());
        ExchangeRatesQuery exchangeRatesQuery = new ExchangeRatesQuery(new RestTemplate());
        assertThrows(IllegalStateException.class, exchangeRatesQuery::getCourses);
    }
}
