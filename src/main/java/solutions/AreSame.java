package solutions;
/*
https://www.codewars.com/kata/are-they-the-same/

Given two arrays a and b write a function comp(a, b) (compSame(a, b) in Clojure) that checks whether the two arrays have the "same" elements, with the same multiplicities. "Same" means, here, that the elements in b are the elements in a squared, regardless of the order.

Examples
Valid arrays
a = [121, 144, 19, 161, 19, 144, 19, 11]
b = [121, 14641, 20736, 361, 25921, 361, 20736, 361]
comp(a, b) returns true because in b 121 is the square of 11, 14641 is the square of 121, 20736 the square of 144, 361 the square of 19, 25921 the square of 161, and so on. It gets obvious if we write b's elements in terms of squares:

a = [121, 144, 19, 161, 19, 144, 19, 11]
b = [11*11, 121*121, 144*144, 19*19, 161*161, 19*19, 144*144, 19*19]
Invalid arrays
If we change the first number to something else, comp may not return true anymore:

a = [121, 144, 19, 161, 19, 144, 19, 11]
b = [132, 14641, 20736, 361, 25921, 361, 20736, 361]
comp(a,b) returns false because in b 132 is not the square of any number of a.

a = [121, 144, 19, 161, 19, 144, 19, 11]
b = [121, 14641, 20736, 36100, 25921, 361, 20736, 361]
comp(a,b) returns false because in b 36100 is not the square of any number of a.

Remarks
a or b might be [] (all languages except R, Shell). a or b might be nil or null or None or nothing (except in Haskell, Elixir, C++, Rust, R, Shell, PureScript).

If a or b are nil (or null or None), the problem doesn't make sense so return false.

If a or b are empty then the result is self-evident.
 */

import java.util.Arrays;

public class AreSame {

    public static void main(String[] args) {
        int[] a = new int[]{0, -14, 191, 161, 19, 144, 195, 1};
        int[] b = {1, 0, 14 * 14, 191 * 191, 161 * 161, 19 * 19, 144 * 144, 195 * 195};
        System.out.print(AreSame.comp(a, b));
    }

    public static boolean comp(int[] original, int[] squared) {
        //option A -- sort each and then walk through both, rejecting as soon as match invalid.
        //sort both = O(nlog(n))
        //walk through = O(n)
        //option B -- square original and then compare lists to see if contain same elements.
        //squaring = O(n)
        //containsAll = O(n2)

        if (original == null || squared == null) {
            return false;
        }
        if (original.length != squared.length) {
            return false;
        }
        if (original.length == 0) {
            return true;
        }

        int[] sortedAndSquaredOriginal = Arrays.stream(original).map(x -> (int) Math.pow(x, 2)).sorted().toArray();
        int[] sortedSquared = Arrays.stream(squared).sorted().toArray();

        return Arrays.equals(sortedAndSquaredOriginal, sortedSquared);
    }
}
