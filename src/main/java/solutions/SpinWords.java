package solutions;/*
Write a function that takes in a string of one or more words, and returns the same string, but with all five or more letter words reversed (Just like the name of this Kata). Strings passed in will consist of only letters and spaces. Spaces will be included only when more than one word is present.


Examples:

spinWords( "Hey fellow warriors" ) => returns "Hey wollef sroirraw"
spinWords( "This is a test") => returns "This is a test"
spinWords( "This is another test" )=> returns "This is rehtona test"
 */


import java.util.Arrays;
import java.util.stream.Collectors;

public class SpinWords {

    public String spinWords(String sentence) {
        //split out as array first, then process each word and recompile into sentence
        String[] words = parseSentenceToArray(sentence);
        String spunSentence = spinSentence(words);
        return spunSentence;
    }

    private String[] parseSentenceToArray(String sentence) {
        //parse sentence into array using spaces as delimiters
        String[] words = sentence.split("\\s");
        return words;
    }

    private String spinSentence(String[] words) {
        //run through stream and if length>5, pass to spinWord & collect into sentence
        return Arrays.stream(words).map(x -> x.length() >= 5 ? reverseWord(x) : x)
                .collect(Collectors.joining(" "));
    }

    private String reverseWord(String word) {
        //reverse characters in string
        StringBuilder reversed = (new StringBuilder(word)).reverse();
        return reversed.toString();

        /*
        Or, the long way:
        StringBuilder reversed = new StringBuilder();
        for (int i = word.length() - 1; i >= 0; i--) {
        reversed.append(word.charAt(i));
        }
        */
    }
}
