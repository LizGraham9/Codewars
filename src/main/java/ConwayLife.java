/*
https://www.codewars.com/kata/conways-game-of-life-unlimited-edition

Given a 2D array and a number of generations, compute n timesteps of Conway's Game of Life.

The rules of the game are:

Any live cell with fewer than two live neighbours dies, as if caused by underpopulation.
Any live cell with more than three live neighbours dies, as if by overcrowding.
Any live cell with two or three live neighbours lives on to the next generation.
Any dead cell with exactly three live neighbours becomes a live cell.
Each cell's neighborhood is the 8 cells immediately around it (i.e. Moore Neighborhood). The universe is infinite in both the x and y dimensions and all cells are initially dead - except for those specified in the arguments. The return value should be a 2d array cropped around all of the living cells. (If there are no living cells, then return [[]].)

For illustration purposes, 0 and 1 will be represented as ░░ and ▓▓ blocks respectively (PHP, C: plain black and white squares). You can take advantage of the htmlize function to get a text representation of the universe, e.g.:

System.out.println(LifeDebug.htmlize(cells));
 */


public class ConwayLife {


    public static void main(String[] args) {
        int[][] glider = {
                {1, 0, 0},
                {0, 1, 1},
                {1, 1, 0}
        };
        int[][] twoGliders = {
                {1, 1, 1, 0, 0, 0, 1, 0},
                {1, 0, 0, 0, 0, 0, 0, 1},
                {0, 1, 0, 0, 0, 1, 1, 1}
        };


        int generations = 1;

        int[][] currentGrid = getGeneration(twoGliders, generations);

        for (int i = 0; i < currentGrid.length; i++) {
            for (int j = 0; j < currentGrid[0].length; j++) {
                System.out.print(currentGrid[i][j]);
            }
            System.out.println();
        }
    }

    public static int[][] getGeneration(int[][] initialGrid, int generations) {
        int[][] currentGrid = initialGrid;

        for (int i = 0; i < generations; i++) {
            currentGrid = tick(currentGrid);
        }

        return currentGrid;
    }

    private static int[][] tick(int[][] previousGrid) {
        int previousHeight = previousGrid.length;
        int previousWidth = previousGrid[0].length;

        int[][] currentGrid = new int[previousHeight + 2][previousWidth + 2];

        for (int i = 0; i < currentGrid.length; i++) {
            for (int j = 0; j < currentGrid[0].length; j++) {
                currentGrid[i][j] = determineNewCellStatus(i, j, previousGrid);
            }
        }
        return cropArray(currentGrid);
    }

    private static int determineNewCellStatus(int rowIndex, int colIndex, int[][] previousGrid) {
        int liveNeighbors = countLiveNeighbors(rowIndex, colIndex, previousGrid);
        boolean wasAlive = wasAlive(rowIndex, colIndex, previousGrid);

        if (wasAlive && hasEnoughNeighborsToStayAlive(liveNeighbors)) {
            return 1;
        }

        if (!wasAlive && hasEnoughNeighborsToRevive(liveNeighbors)) {
            return 1;
        }

        return 0;
    }

    private static boolean wasAlive(int rowIndex, int colIndex, int[][] previousGrid) {
        if (colIndex < 1 || colIndex > previousGrid[0].length) {
            return false;
        }
        if (rowIndex < 1 || rowIndex > previousGrid.length) {
            return false;
        }
        return previousGrid[rowIndex - 1][colIndex - 1] == 1;
    }

    private static boolean hasEnoughNeighborsToStayAlive(int neighbors) {
        return neighbors >= 2 && neighbors <= 3;
    }

    private static boolean hasEnoughNeighborsToRevive(int neighbors) {
        return neighbors == 3;
    }
    
    private static int countLiveNeighbors(int rowIndex, int colIndex, int[][] previousGrid) {
        int liveNeighbors = 0;

        for (int i = rowIndex - 1; i <= rowIndex + 1; i++) {
            for (int j = colIndex - 1; j <= colIndex + 1; j++) {
                if (wasAlive(i, j, previousGrid) && !(i == rowIndex && j == colIndex)) {
                    liveNeighbors++;
                }
            }
        }
        return liveNeighbors;
    }

    private static int[][] cropArray(int[][] originalArr) {
        int leftBound = originalArr[0].length;
        int rightBound = -1;
        int topBound = originalArr.length;
        int bottomBound = -1;

        for (int rowIndex = 0; rowIndex < originalArr.length; rowIndex++) {
            for (int colIndex = 0; colIndex < originalArr[0].length; colIndex++) {
                if (originalArr[rowIndex][colIndex] == 1) {
                    if (colIndex < leftBound) {
                        leftBound = colIndex;
                    }
                    if (colIndex > rightBound) {
                        rightBound = colIndex;
                    }
                    if (rowIndex < topBound) {
                        topBound = rowIndex;
                    }
                    if (rowIndex > bottomBound) {
                        bottomBound = rowIndex;
                    }
                }
            }
        }
        return buildCroppedArray(originalArr, leftBound, rightBound, topBound, bottomBound);
    }

    private static int[][] buildCroppedArray(int[][] arr, int leftBound, int rightBound, int topBound, int bottomBound) {
        int newCols = rightBound - leftBound + 1;
        int newRows = bottomBound - topBound + 1;
        int[][] croppedArray = new int[newRows][newCols];

        for (int rowIndex = 0; rowIndex < croppedArray.length; rowIndex++) {
            for (int colIndex = 0; colIndex < croppedArray[0].length; colIndex++) {
                croppedArray[rowIndex][colIndex] = arr[rowIndex + topBound][colIndex + leftBound];
            }
        }
        return croppedArray;
    }
}