/*
https://projecteuler.net/problem=104

Pandigital Fibonacci ends
Problem 104
The Fibonacci sequence is defined by the recurrence relation:

Fn = Fn−1 + Fn−2, where F1 = 1 and F2 = 1.
It turns out that F541, which contains 113 digits, is the first Fibonacci number for which the last nine digits are 1-9 pandigital (contain all the digits 1 to 9, but not necessarily in order). And F2749, which contains 575 digits, is the first Fibonacci number for which the first nine digits are 1-9 pandigital.

Given that Fk is the first Fibonacci number for which the first nine digits AND the last nine digits are 1-9 pandigital, find k.
 */


import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class PandigitalFibonacci {

    public static void main(String[] args) {
        PandigitalFibonacci panFib = new PandigitalFibonacci();

        System.out.println("attempting to find value!");

        int symmetricN = panFib.findSymmetricallyPandigitalN();
        System.out.println(symmetricN);
    }

    private int findSymmetricallyPandigitalN() {
        Map<Integer, BigInteger> fibCache = new HashMap<>();
        fibCache.put(0, BigInteger.valueOf(0));
        fibCache.put(1, BigInteger.valueOf(1));

        BigInteger currentFib;
        int symmetricN;

        for (int n = 0; true; n++) {
            long startTime = System.currentTimeMillis();
            currentFib = fib(n, fibCache);
            System.out.println(n + ":");
            System.out.println();
            long fibTime = System.currentTimeMillis();
            System.out.println("Time to run Fib: " + (fibTime - startTime) + "ms");
            if (isSymmetricallyPandigital(currentFib)) {
                symmetricN = n;
                break;
            }
            System.out.println("Time to run isSymPan: " + (System.currentTimeMillis() - fibTime) + "ms");
            System.out.println("-------------");
            System.out.println();
        }

        return symmetricN;
    }

    private BigInteger fib(int n, Map<Integer, BigInteger> fibCache) {
        long startTime = System.currentTimeMillis();

        if (fibCache.containsKey(n)) {
            return fibCache.get(n);
        }

        BigInteger currentFib = fib(n - 1, fibCache).add(fib(n - 2, fibCache));
        fibCache.put(n, currentFib);

        return currentFib;
    }

    private boolean isSymmetricallyPandigital(BigInteger number) {
        if (String.valueOf(number).length() < 9) {
            return false;
        }

        String numberString = String.valueOf(number);

        String head = numberString.substring(0, 9);
        String tail = numberString.substring(numberString.length() - 9, numberString.length());

        return isPandigital(head) && isPandigital(tail);
    }

    private boolean isPandigital(String numberString) {
        for (int i = 1; i < 10; i++) {
            if (!numberString.contains(Integer.toString(i))) {
                return false;
            }
        }
        return true;
    }

}


