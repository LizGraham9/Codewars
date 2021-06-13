package solutions;/*
Create a function taking a positive integer as its parameter and returning a string containing the Roman Numeral representation of that integer.

Modern Roman numerals are written by expressing each digit separately starting with the left most digit and skipping any digit with a value of zero. In Roman numerals 1990 is rendered: 1000=M, 900=CM, 90=XC; resulting in MCMXC. 2008 is written as 2000=MM, 8=VIII; or MMVIII. 1666 uses each Roman symbol in descending order: MDCLXVI.

Example:

conversion.solution(1000); //should return "M"
Help:

Symbol    Value
I          1
V          5
X          10
L          50
C          100
D          500
M          1,000
Remember that there can't be more than 3 identical symbols in a row.

More about roman numerals - http://en.wikipedia.org/wiki/Roman_numerals
 */


import java.util.*;

public class RomanNumeralEncoder {

    private final Set<Numeral> NUMERALS = EnumSet.allOf(Numeral.class);
    private final Numeral[] DESCENDING_NUMERALS_ARRAY = NUMERALS.stream().sorted(Comparator.comparing(Numeral::getValue).reversed()).toArray(Numeral[]::new);

    public String convertToNumerals(int n) {
//        int valueToConvert = n;
//
//        StringBuilder sb = new StringBuilder();
//        StringBuilder sb2 = new StringBuilder();
//
//        for (int i = 0; i < DESCENDING_NUMERALS_ARRAY.length; i++) {
//
//            int leadingDigit = Integer.toString(valueToConvert).charAt(0);
//
//            if (valueToConvert / DESCENDING_NUMERALS_ARRAY[i].value >= 1) {
//                sb.append(DESCENDING_NUMERALS_ARRAY[i].symbol);
//            }
//
//
//            String digitNotation = (leadingDigit == 4 || leadingDigit == 9) ? composeSubtractiveNotation(valueToConvert, numeral) : composeAdditiveNotation(valueToConvert, numeral);
//            sb.append(digitNotation);
//
//            int occurrence = valueToConvert / DESCENDING_NUMERALS_ARRAY[i].value;
//            if (occurrence > 0) valueToConvert %= DESCENDING_NUMERALS_ARRAY[i].value * occurrence;
//        }
//        return sb.toString();
        return "";
    }

    private String composeSubtractiveNotation(int value, Numeral numeral) {
        char firstPart;
        char secondPart;

        for (int i = 0; i < DESCENDING_NUMERALS_ARRAY.length; i++) {
            if (value / DESCENDING_NUMERALS_ARRAY[i].value > 1) {
            }

        }

        if (value >= 100) {
            firstPart = Numeral.HUNDRED.symbol;
        }

        return "";
    }

    private String composeAdditiveNotation(int value, Numeral numeral) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < value / numeral.value; i++) sb.append(numeral.symbol);
        return sb.toString();
    }

    private enum Numeral {

        ONE('I', 1),
        FIVE('V', 5),
        TEN('X', 10),
        FIFTY('L', 50),
        HUNDRED('C', 100),
        FIVEHUNDRED('D', 500),
        THOUSAND('M', 1000);

        private char symbol;
        private int value;

        Numeral(char symbol, int value) {
            this.symbol = symbol;
            this.value = value;
        }

        private int getValue() {
            return this.value;
        }
    }
}
