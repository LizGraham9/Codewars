package solutions;/*
Return the number (count) of vowels in the given string.

        We will consider a, e, i, o, and u as vowels for this Kata.

        The input string will only consist of lower case letters and/or spaces.
*/

public class Vowels {

    public static int getCount(String str) {
        int vowelsCount = 0;
        // your code here
        char[] vowels = {'a', 'e', 'i', 'o', 'u'};

        for (char letter : str.toCharArray()) {
            for (char vowel : vowels) {
                if (letter == vowel) {
                    vowelsCount++;
                }
            }
        }
//   Using Streams:
//   vowelsCount = (int)str.chars().filter(x -> vowels.toString().indexOf(x) >= 0).count();
        return vowelsCount;
    }
}
