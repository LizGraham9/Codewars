package solutions;/*
https://www.codewars.com/kata/next-bigger-number-with-the-same-digits
You have to create a function that takes a positive integer number and returns the next bigger number formed by the same digits:

Kata.nextBiggerNumber(12)==21
Kata.nextBiggerNumber(513)==531
Kata.nextBiggerNumber(2017)==2071
If no bigger number can be composed using those digits, return -1:

Kata.nextBiggerNumber(9)==-1
Kata.nextBiggerNumber(111)==-1
Kata.nextBiggerNumber(531)==-1
 */

import java.util.Arrays;

public class BiggerNumber {

    public static long nextBiggerNumber(long n) {
        int[] digitArray = Long.toString(n).chars().map(Character::getNumericValue).toArray();
        int pivot = findPivot(digitArray);
        if (pivot < 0) return pivot;
        int nextHighestInTail = findNextHighestInTail(digitArray, pivot);
        digitArray = swapNumbers(digitArray, pivot, nextHighestInTail);

        return convertBackToLong(digitArray);
    }

    private static int findPivot(int[] digitArray) {
        for (int i = digitArray.length - 1; i > 0; i--) {
            if (digitArray[i - 1] < digitArray[i]) return i - 1;
        }
        return -1;
    }

    private static int findNextHighestInTail(int[] digitArray, int pivot) {
        Arrays.sort(digitArray, pivot + 1, digitArray.length);

        if (digitArray.length - pivot > 2) {
            for (int i = pivot + 1; i < digitArray.length; i++) {
                if (digitArray[i] > digitArray[pivot]) return i;
            }
        }
        return digitArray.length - 1;
    }

    private static int[] swapNumbers(int[] digitArray, int pivot, int nextHighest) {
        int pivotValue = digitArray[pivot];
        int nextHighestValue = digitArray[nextHighest];

        digitArray[pivot] = nextHighestValue;
        digitArray[nextHighest] = pivotValue;

        return digitArray;
    }

    private static long convertBackToLong(int[] digitArray) {
        StringBuilder sb = new StringBuilder();
        Arrays.stream(digitArray).forEach(x -> sb.append(x));

        return Long.parseLong(sb.toString());
    }
}
