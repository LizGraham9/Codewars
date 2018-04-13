/*
A format for expressing an ordered list of integers is to use a comma separated list of either

individual integers
or a range of integers denoted by the starting integer separated from the end integer in the range by a dash, '-'. The range includes all integers in the interval including both endpoints. It is not considered a range unless it spans at least 3 numbers. For example ("12, 13, 15-17")
Complete the solution so that it takes a list of integers in increasing order and returns a correctly formatted string in the range format.

Example:

Solution.rangeExtraction(new int[] {-6, -3, -2, -1, 0, 1, 3, 4, 5, 7, 8, 9, 10, 11, 14, 15, 17, 18, 19, 20})
# returns "-6,-3-1,3-5,7-11,14,15,17-20"
 */


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RangeExtractor {

    public static String rangeExtraction(int[] arr) {
        int leftBound = 0;
        int rightBound = 0;
        int rightBoundNeighbor;
        List<String> ranges = new ArrayList<>();

        while (rightBound < arr.length - 1) {
            rightBoundNeighbor = rightBound + 1;

            if (arr[rightBoundNeighbor] != arr[rightBound] + 1 || rightBound == arr.length-1) {
                ranges.add(createRange(leftBound, rightBound, arr));
                leftBound = rightBoundNeighbor;
            }
            rightBound++;
        }
        return ranges.stream().collect(Collectors.joining(","));
    }


    private static String createRange(int leftBound, int rightBound, int[] arr) {
        int rangeLength = rightBound - leftBound;

        String leftString = Integer.toString(arr[leftBound]);
        String rightString = Integer.toString(arr[rightBound]);
        String joiner;

        if (rangeLength == 0) {
            return leftString;
        }
        if (rangeLength == 1) {
            joiner = ",";
        } else {
            joiner = "-";
        }
        return leftString + joiner + rightString;
    }
}
