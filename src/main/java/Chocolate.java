/*
https://www.codewars.com/kata/chocolate-problem
Chocolate factory produces unusual chocolate. Chocolate bars come in the form of long tiles 1 × N, which consists of N squares. Each square shows the portrait of one of the famous N confectioners of this company. Different chocolates have the same N confectioners' portraits, but in a different order.

Task
Write a method, that for a given order of portraits of two chocolate bars determines a minimum number of breaks, that you need to perform on a first bar to form a second bar by repositioning the broken parts.

Restriction
you can break a bar only on the boundaries of its' squares.
you can’t flip initial bar or it's parts.
Input data
N - integer number (2 ≤ N ≤ 1000000), specifies the size of a chocolate bar, i.e. the number of squares in it. All bakers are numbered from 1 to N.
firstBar, secondBar - integer arrays of N different numbers each (all the numbers don't exceed N) - portraits' order in the first and second bars respectively. It is known that these orders are different.
Your task is to calculate a single number - the minimum number of breaks that you need to perform on a first bar, to form a second bar by repositioning the broken parts.

Example:

int N = 5;
int firstBar[]  = {4, 3, 2, 5, 1};
int secondBar[] = {1, 2, 5, 3, 4};
chocolate(N, firstBar, secondBar);   // => returns 3
 */

/*

start with firstBar[0] and find corresponding value in second bar.
If right neighbors don't match, add break after right value and move to right neighbor.
If right neigbors do match, keep going until they don't. add break.
Do not add break if at last index of first bar

Just need to convert second bar to HashMap with number as key & initial position as value
 */

import java.util.HashMap;
import java.util.Map;

public class Chocolate {

    public static int chocolate(int barLength, int[] firstBar, int[] secondBar) {
        Map<Integer, Integer> secondBarMapping = mapBar(secondBar);
        int breakCount = 0;

        for (int i = 0; i < barLength - 1; i++) {
            int sbIndex = secondBarMapping.get(firstBar[i]);
            if (sbIndex >= barLength - 1 || firstBar[i + 1] != secondBar[sbIndex + 1]) {
                breakCount++;
            }

        }
        return breakCount;
    }

    private static Map<Integer, Integer> mapBar(int[] bar) {
        Map<Integer, Integer> barMapping = new HashMap<>();
        for (int i = 0; i < bar.length; i++) {
            barMapping.put(bar[i], i);
        }
        return barMapping;
    }
}
