package solutions;// Test partially copied from codewars. NOT entirely original work.

import org.junit.jupiter.api.Test;
import solutions.RomanNumeralEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RomanNumeralEncoderTest {

    private RomanNumeralEncoder encoder = new RomanNumeralEncoder();

    @Test
    public void shouldCovertToRoman() {

        assertEquals("MDCCLXXVI", encoder.convertToNumerals(1776));
//        assertEquals("MCMLIV", encoder.convertToNumerals(1954));
        assertEquals("I", encoder.convertToNumerals(1));
//        assertEquals("solution(4) should equal to IV", "IV", encoder.convertToNumerals(4));
//        assertEquals("solution(6) should equal to VI", "VI", encoder.convertToNumerals(6));
    }
}
