package solutions;

import solutions.Abbreviator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AbbreviatorTest {

    @Test
    public void abbreviateTest() {

        Abbreviator abbreviator = new Abbreviator();

        assertEquals("i18n", abbreviator.abbreviate("internationalization"));
        assertEquals("a11y", abbreviator.abbreviate("accessibility"));
        assertEquals("e6t-r3s are r4y fun!", abbreviator.abbreviate("elephant-rides are really fun!"));
    }
}
