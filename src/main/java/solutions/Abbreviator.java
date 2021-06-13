package solutions;/*
The word i18n is a common abbreviation of internationalization in the developer community, used instead of typing the whole word and trying to spell it correctly. Similarly, a11y is an abbreviation of accessibility.

Write a function that takes a string and turns any and all "words" (see below) within that string of length 4 or greater into an abbreviation, following these rules:

A "word" is a sequence of alphabetical characters. By this definition, any other character like a space or hyphen (eg. "elephant-ride") will split up a series of letters into two words (eg. "elephant" and "ride").
The abbreviated version of the word should have the first letter, then the number of removed characters, then the last letter (eg. "elephant ride" => "e6t r2e").
 */

import java.util.Arrays;
import java.util.stream.Collectors;

public class Abbreviator {

    public String abbreviate(String string) {
        // split string into array with non-alphanumeric as own entries
        // pass each element of array into abbreviateLongWords method and return combined string

        String[] wordArray = string.split("(?<=[^a-zA-Z])|(?=[^a-zA-Z])");
        return Arrays.stream(wordArray).map(x -> x.length() < 4 ? x : abbreviateWord(x)).collect(Collectors.joining());
    }

    private String abbreviateWord(String word) {
        StringBuilder abbreviatedWord = new StringBuilder();
        char firstLetter = word.charAt(0);
        char lastLetter = word.charAt(word.length() - 1);
        int lettersAbbreviated = word.length() - 2;

        abbreviatedWord.append(firstLetter);
        abbreviatedWord.append(lettersAbbreviated);
        abbreviatedWord.append(lastLetter);

        return abbreviatedWord.toString();
    }
}
