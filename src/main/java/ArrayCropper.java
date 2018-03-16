/*
You need to crop an array of ints, such as if it is an image and you want to crop a specific segment from it in the graphics editor to remove the maximum amount of the background and leave only the object you intrested in.

The background consists of 0.
The object consists of 1.

For example the Array:

  {0,1,0,0},
  {0,0,1,0},
  {1,1,1,0},
  {0,0,0,0}
After the cropping will look like:

  {0,1,0},
  {0,0,1},
  {1,1,1}
*/

public class ArrayCropper {
    public static int[][] getCroppedFieldAsArray(int[][] src) {
        //TODO: Crop some arrays here.

        int topBoundary = src[0].length;
        int bottomBoundary = 0;

        int leftBoundary = src.length;
        int rightBoundary = 0;

        for (int rowIndex = 0; rowIndex < src.length; rowIndex++) {
            for (int colIndex = 0; colIndex < src[0].length; colIndex++) {
                if (src[rowIndex][colIndex] == 1) {
                    if (rowIndex < topBoundary) {
                        topBoundary = rowIndex;
                    }
                    if (rowIndex > bottomBoundary) {
                        bottomBoundary = rowIndex;
                    }
                    if (colIndex < leftBoundary) {
                        leftBoundary = colIndex;
                    }
                    if (colIndex > rightBoundary) {
                        rightBoundary = colIndex;
                    }
                }
            }
        }

        return cropArray(src, topBoundary, bottomBoundary, leftBoundary, rightBoundary);
    }

    private static int[][] cropArray(int[][] src, int topBoundary, int bottomBoundary, int leftBoundary, int rightBoundary) {

        int arrayRows = bottomBoundary - topBoundary + 1;
        int arrayCols = rightBoundary - leftBoundary + 1;

        if (arrayCols < 0 | arrayRows < 0) {
            return null;
        }

        int[][] cropped = new int[arrayRows][arrayCols];

        for (int rowIndex = 0; rowIndex < cropped.length; rowIndex++) {
            for (int colIndex = 0; colIndex < cropped[0].length; colIndex++) {
                cropped[rowIndex][colIndex] = src[rowIndex + topBoundary][colIndex + leftBoundary];
            }
        }

        return cropped;
    }

}