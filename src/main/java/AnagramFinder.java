
/*
Write a function that takes a list of words and outputs all the words that are anagrams of at least one other word in the list. The output list must preserve the order of the input list.

For instance,

Input: ['cat', 'cat', 'octopus', 'care', 'news', 'acre', 'act', 'newspaper']
Output: ['cat', 'cat', 'care', 'acre', 'act']
Notice that both instances of the word "cat" are anagrams of the word "act", and therefore both instances must appear in the output.
*/

import java.util.*;

class AnagramFinder {
    public static void main(String[] args) {
        List<String> input = Arrays.asList("cat", "cat", "octopus", "care", "news", "acre", "act", "newspaper");

        List<String> output = findAnagrams(input);

        System.out.println(output);
    }

    private static List<String> findAnagrams(List<String> words) {

        //O(n) where n is size of words
        boolean[] anagramValues = new boolean[words.size()];

        List<String> anagrams = new ArrayList<>();

        //Time complexity: O(n * n * m) = O(m*n^2) where n is the number of words & m is the word length

        //Space complexity: O(n) + O(m) + O(n) = O(n + m)

        // O(m)
        for(int i = 0; i < words.size() - 1; i++) {
            for(int j = i + 1; j < words.size(); j++) {
                if(areAnagrams(words.get(i),words.get(j))) {
                    anagramValues[i] = true;
                    anagramValues[j] = true;
                }
            }
        }

        //O(n)
        for(int i = 0; i < anagramValues.length; i++) {
            if(anagramValues[i]) {
                anagrams.add(words.get(i));
            }
        }
        return anagrams;
    }

    //
    private static boolean areAnagrams(String word1, String word2){
        return charMap(word1).equals(charMap(word2));
    }

    private static Map<Character, Integer> charMap(String word) {
        Map<Character, Integer> charMap = emptyCharMap();
        for(char c : word.toCharArray()) {
            charMap.put(c, charMap.get(c) + 1);
        }
        return charMap;
    }

    private static Map<Character, Integer> emptyCharMap() {
        Map<Character, Integer> emptyCharMap = new HashMap<>();
        for(char c = 'a'; c <= 'z'; c++) {
            emptyCharMap.put(c, 0);
        }
        return emptyCharMap;
    }
}
