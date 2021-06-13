package solutions;// Test copied from codewars. NOT original work.

import org.junit.jupiter.api.Test;
import solutions.SpinWords;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpinWordsTest {

    @Test
    public void spinWordsTest() {
//        assertEquals("emocleW", new Solutions.SpinWords().spinWords("Welcome"));
        assertEquals("Hey wollef sroirraw", new SpinWords().spinWords("Hey fellow warriors"));
    }
}
