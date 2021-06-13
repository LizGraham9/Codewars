package solutions;// Test copied from codewars. NOT original work.

import solutions.BiggerNumber;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class BiggerNumberTest {
    @Test
    public void basicTests() {
        assertEquals(533127, BiggerNumber.nextBiggerNumber(532731));
        assertEquals(21, BiggerNumber.nextBiggerNumber(12));
        assertEquals(531, BiggerNumber.nextBiggerNumber(513));
        assertEquals(2071, BiggerNumber.nextBiggerNumber(2017));
        assertEquals(441, BiggerNumber.nextBiggerNumber(414));
        assertEquals(414, BiggerNumber.nextBiggerNumber(144));
    }
}
