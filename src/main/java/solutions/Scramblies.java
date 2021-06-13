package solutions;/*
Write function scramble(str1,str2) that returns true if a portion of str1 characters can be rearranged to match str2, otherwise returns false.

For example:
str1 is 'rkqodlw' and str2 is 'world' the output should return true.
str1 is 'cedewaraaossoqqyt' and str2 is 'codewars' should return true.
str1 is 'katas' and str2 is 'steak' should return false.

Only lower case letters will be used (a-z). No punctuation or digits will be included.
Performance needs to be considered
 */

import java.util.*;

public class Scramblies {

    public static boolean scramble(String str1, String str2) {
        //compares the letters in each string. if at any point, str1 doesn't have as many of a given letter as str2 does, returns false.
        List<Integer> letterList1 = getLetterList(str1);
        List<Integer> letterList2 = getLetterList(str2);
        for (int i = 0; i < letterList1.size(); i++) {
            if(letterList2.get(i)>letterList1.get(i)){
                return false;
            }
        }
        return true;
    }

    private static List<Integer> getLetterList(String str) {
        // creates a map containing full alphabet and the number of times each letter occurs within the string
        Map<Character, Integer> charMap = new HashMap<>();
        for (char letter = 'a'; letter <= 'z'; letter++) {
            charMap.put(letter, 0);
        }
        for (char letter : str.toCharArray()) {
            int letterCount = charMap.get(letter) + 1;
            charMap.put(letter, letterCount);
        }
        return new ArrayList<>(charMap.values());
    }
}
