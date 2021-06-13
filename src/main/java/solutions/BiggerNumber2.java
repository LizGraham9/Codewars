package solutions;

import java.util.Arrays;

public class BiggerNumber2 {

    public static int findEvenIndex(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (isBalanced(arr, i)) {
                return i;
            }
        }
        return -1;
    }

    private static boolean isBalanced(int[] arr, int i) {
        int leftSum = Arrays.stream(arr).limit(i).sum();
        int rightSum = Arrays.stream(arr).skip(i + 1).sum();

        return leftSum == rightSum;
    }

}
