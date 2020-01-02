import com.learning.web.development.utils.StringUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertFalse;
import static org.springframework.test.util.AssertionErrors.assertTrue;

public class StringUtilsTest {
    @Test
    public void testToArray() {

    }

    @Test
    public void testJoinArray() {

    }

    @Test
    public void testIsEmpty() {
        assertFalse("Non empty string claimed to be empty", StringUtils.isEmpty("TEST"));
        assertTrue("Empty string not recognized", StringUtils.isEmpty(""));
        assertTrue("Whitespaces not recognized", StringUtils.isEmpty(" "));
    }

    @Test
    public void testToDouble() {
        assertEquals(3.1415, StringUtils.toDouble("3.1415"), 0.0001);
        //assertEquals("Not NaN for null", Double.NaN, StringUtils.toDouble(null), 0.00001);
    }

    @Test
    public void testFromDouble() {
        double source = 3.1415;
        String expected = "3.1415";

        String actual = StringUtils.fromDouble(source);
        assertEquals(expected, actual);
    }
}
