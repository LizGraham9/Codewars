package solutions;// Test copied from codewars. NOT original work.
import org.junit.jupiter.api.Test;
import solutions.WeightSort;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WeightSortTest {

    @Test
    public void BasicTests() {
        System.out.println("****** Basic Tests ******");
        assertEquals("2000 103 123 4444 99", WeightSort.orderWeight("103 123 4444 99 2000"));
        assertEquals("11 11 2000 10003 22 123 1234000 44444444 9999", WeightSort.orderWeight("2000 10003 1234000 44444444 9999 11 11 22 123"));
    }
}
