package solutions;/*
https://www.codewars.com/kata/surreal-numbers-construction-rule
There is a neat number system called "Surreal Numbers." You can read about them here: https://en.wikipedia.org/wiki/Surreal_number.

This kata focuses on the definition of a surreal number in terms of a form. A form is a pair of sets of numbers, L and R, written as follows:

{ L | R }

From wikipedia: A form { L | R } is numeric if the intersection of L and R is the empty set and each element of R is greater than every element of L.

Given two strings of space-separated numbers representing L and R, determine if { L | R } would represent a surreal number by returning a boolean.

All numbers in the supplied string are either integers or ratios of integers and may be positive or negative. For negative ratios, the minus sign is always the first symbol and there is at most one minus sign per number. One or both strings may be empty and in this case the form represents a valid surreal number and isSurreal should return true.

Examples:

isSurreal("-1", "1") = true
isSurreal("1", "-1") = false
isSurreal("1/2", "3/4") = true
isSurreal("-3 -2 -1 0", "17") = true
isSurreal("18 33 -2 19 -1/8", "99 34 200/3") = true
isSurreal("18 33 -2 19 1/8", "99 31 200/3") = false
isSurreal("-3", "-2 65") = true
 */

import java.util.Arrays;
import java.util.SortedSet;
import java.util.TreeSet;

public class SurrealNumbers {

    public boolean isSurreal(String left, String right) {
        if (left.isEmpty() || right.isEmpty()) {
            return true;
        }
        Form form = new Form(parseNumbers(left), parseNumbers(right));
        return form.isSurreal();
    }

    private SortedSet<Double> parseNumbers(String elements) {
        String[] splitElements = elements.split("\\s+");
        SortedSet<Double> parsedNumbers = Arrays.stream(splitElements).map(this::stringToDouble).collect(TreeSet<Double>::new, SortedSet::add, SortedSet::addAll);
        return parsedNumbers;
    }

    private double stringToDouble(String element) {
        if (element.contains("/")) {
            String[] elementPieces = element.split("/");
            int numerator = Integer.parseInt(elementPieces[0]);
            int denominator = Integer.parseInt(elementPieces[1]);
            return (double) numerator / denominator;
        }
        return Double.parseDouble(element);
    }

    private class Form {
        private SortedSet<Double> left;
        private SortedSet<Double> right;

        private Form(SortedSet<Double> left, SortedSet<Double> right) {
            this.left = left;
            this.right = right;
        }

        private boolean isSurreal() {
            for (double leftNumber : left) {
                if (right.contains(leftNumber)) {
                    return false;
                }
                if (leftNumber > right.first()) {
                    return false;
                }
            }
            return true;
        }
    }
}
