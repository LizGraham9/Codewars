/*
https://www.codewars.com/kata/find-numbers-with-same-amount-of-divisors

The integers 14 and 15, are contiguous (1 the difference between them, obvious) and have the same number of divisors.

14 ----> 1, 2, 7, 14 (4 divisors)
15 ----> 1, 3, 5, 15 (4 divisors)
The next pair of contiguous integers with this property is 21 and 22.

21 -----> 1, 3, 7, 21 (4 divisors)
22 -----> 1, 2, 11, 22 (4 divisors)
We have 8 pairs of integers below 50 having this property, they are:

"[[2, 3], [14, 15], [21, 22], [26, 27], [33, 34], [34, 35], [38, 39], [44, 45]]"
Let's see now the integers that have a difference of 3 between them. There are seven pairs below 100:

"[[2, 5], [35, 38], [55, 58], [62, 65], [74, 77], [82, 85], [91, 94]]"
Let's name, diff, the difference between two integers, next and prev, (diff = next - prev) and nMax, an upper bound of the range.

We need a special function, count_pairsInt(), that receives two arguments, diff and nMax and outputs the amount of pairs of integers that fulfill this property, all of them being smaller (not smaller or equal) than nMax.

Let's see it more clearly with examples.

count_pairsInt(1, 50) -----> 8 (See case above)
count_pairsInt(3, 100) -----> 7 (See case above)
Happy coding!!!
 */

//build map of all numbers up to nMax w/ value divisors.
//go through and check index + diff and see if same number of divisors. if so, advance count.
//stop nMax - diff from end of list.

//...96, 97, 98, 99]

import java.util.HashMap;
import java.util.Map;

public class SameNbDivisors {
    public static int countPairsInt(int diff, long nMax) {
        int pairsIntsCount = 0;
        Map<Long, Long> divisorCounts = computeDivisorCounts(nMax);

        for (long i = 1; i < nMax - diff; i++) {
            if (divisorCounts.get(i).equals(divisorCounts.get(i + diff))) {
                pairsIntsCount++;
            }
        }
        return pairsIntsCount;
    }

    private static Map<Long, Long> computeDivisorCounts(long nMax) {
        Map<Long, Long> divisorCounts = new HashMap<>();
        for (long i = 1; i < nMax; i++) {
            divisorCounts.put(i, countFactors(i));
        }

        return divisorCounts;
    }

    private static long countFactors(long num) {
        int divisors = 1;
        for (long i = 1; i <= Math.floor(Math.sqrt(num)); i++) {
            if (num % i == 0) {
                divisors++;
                long otherFactor = num / i;
                if (otherFactor != i) {
                    divisors++;
                }
            }
        }
        return divisors;
    }
}
