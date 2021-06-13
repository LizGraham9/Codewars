package solutions;/*
https://www.codewars.com/kata/cure-cancer

Now you are a doctor.

You are working with a patient's body which has many cells.

The patient's body is a matrix where every row represents a cell.

Each cell contains just uppercase and lowercase letters,

and every cell in the body should be the same.

Oh no! It seems that one of the cells have mutated!

It is your job to locate the mutation so that the chemo specialists can fix it!

return the location [i,j] within the matrix...

before it's too late! :(

example:

cellscellscellscodecodecells
cellscellscellscodecodecells
cellscellscellscodecodecells
cellscellscellscodecodecells
cellscellscellscodecodecells
cellscellscellscodecodecells
cellscellscellscodecodecells
cellscellscellscodecodecells
cellscellscellscodecodecells
cellscellscellscodecadecells <- here it is! [9, 20]
cellscellscellscodecodecells
cellscellscellscodecodecells
cellscellscellscodecodecells
cellscellscellscodecodecells
no bodies will have less than 3 cells.

if the diagnose was a false alarm, return an empty array.
 */

//use first three rows to determine "correct" pattern. Walk through remainder and compare to pattern.
// Once find, return bad spot.

import java.util.Arrays;

public class CureCancer {
    public static int[] mutationLocation(char[][] body) {
        char[] healthyCellPattern = healthyCellPattern(body);

        for (int i = 0; i < body.length; i++) {
            for (int j = 0; j < body[0].length; j++) {
                if (body[i][j] != healthyCellPattern[j]) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[0];
    }

    private static char[] healthyCellPattern(char[][] body) {
        return Arrays.equals(body[0], body[1]) ? body[0] : body[2];
    }
}
