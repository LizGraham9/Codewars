/*
https://www.codewars.com/kata/street-fighter-2-character-selection

Short Intro

Some of you might remember spending afternoons playing Street Fighter 2 in some Arcade back in the 90s or emulating it nowadays with the numerous emulators for retro consoles.

Can you solve this kata? Suuure-You-Can!

UPDATE: a new kata's harder version is available here.

The Kata

You'll have to simulate the video game's character selection screen behaviour, more specifically the selection grid. Such screen looks like this:

alt text

Selection Grid Layout in text:

| Ryu  | E.Honda | Blanka  | Guile   | Balrog | Vega    |
| Ken  | Chun Li | Zangief | Dhalsim | Sagat  | M.Bison |
Input

the list of game characters in a 2x6 grid;
the initial position of the selection cursor (top-left is (0,0));
a list of moves of the selection cursor (which are up, down, left, right);
Output

the list of characters who have been hovered by the selection cursor after all the moves (ordered and with repetition, all the ones after a move, wether successful or not, see tests);
Rules

Selection cursor is circular horizontally but not vertically!

As you might remember from the game, the selection cursor rotates horizontally but not vertically; that means that if I'm in the leftmost and I try to go left again I'll get to the rightmost (examples: from Ryu to Vega, from Ken to M.Bison) and vice versa from rightmost to leftmost.

Instead, if I try to go further up from the upmost or further down from the downmost, I'll just stay where I am located (examples: you can't go lower than lowest row: Ken, Chun Li, Zangief, Dhalsim, Sagat and M.Bison in the above image; you can't go upper than highest row: Ryu, E.Honda, Blanka, Guile, Balrog and Vega in the above image).

Test

For this easy version the fighters grid layout and the initial position will always be the same in all tests, only the list of moves change.

Notice : changing some of the input data might not help you.

Examples

1.

fighters = [
    ["Ryu", "E.Honda", "Blanka", "Guile", "Balrog", "Vega"],
    ["Ken", "Chun Li", "Zangief", "Dhalsim", "Sagat", "M.Bison"]
]
initial_position = (0,0)
moves = ['up', 'left', 'right', 'left', 'left']

then I should get:

['Ryu', 'Vega', 'Ryu', 'Vega', 'Balrog']
as the characters I've been hovering with the selection cursor during my moves. Notice: Ryu is the first just because it "fails" the first up See test cases for more examples.

2.

fighters = [
    ["Ryu", "E.Honda", "Blanka", "Guile", "Balrog", "Vega"],
    ["Ken", "Chun Li", "Zangief", "Dhalsim", "Sagat", "M.Bison"]
]
initial_position = (0,0)
moves = ['right', 'down', 'left', 'left', 'left', 'left', 'right']
Result:

['E.Honda', 'Chun Li', 'Ken', 'M.Bison', 'Sagat', 'Dhalsim', 'Sagat']
 */

public class StreetFighter {
    public static String[] streetFighterSelection(String[][] fighters, int[] position, String[] moves) {
        FighterNode[][] fighterNodes = toGraph(fighters);
        String[] fightersVisited = new String[moves.length];
        FighterNode currentFighter = fighterNodes[position[0]][position[1]];

        for (int i = 0; i < moves.length; i++) {
            switch (moves[i]) {
                case "up":
                    currentFighter = (currentFighter.up != null) ? currentFighter.up : currentFighter;
                    break;
                case "down":
                    currentFighter = (currentFighter.down != null) ? currentFighter.down : currentFighter;
                    break;
                case "left":
                    currentFighter = currentFighter.left;
                    break;
                case "right":
                    currentFighter = currentFighter.right;
                    break;
            }
            fightersVisited[i] = currentFighter.name;
        }
        return fightersVisited;
    }


    private static FighterNode[][] toGraph(String[][] fighters) {
        FighterNode[][] fighterNodes = new FighterNode[fighters.length][fighters[0].length];

        for (int i = 0; i < fighters.length; i++) {
            for (int j = 0; j < fighters[0].length; j++) {
                fighterNodes[i][j] = new FighterNode(fighters[i][j]);
            }
        }

        for (int i = 0; i < fighterNodes.length; i++) {
            for (int j = 0; j < fighterNodes[0].length; j++) {
                FighterNode currentFighter = fighterNodes[i][j];
                currentFighter.up = (i > 0) ? fighterNodes[i - 1][j] : null;
                currentFighter.down = (i < fighterNodes.length - 1) ? fighterNodes[i + 1][j] : null;
                currentFighter.left = (j > 0) ? fighterNodes[i][j - 1] : fighterNodes[i][fighterNodes[0].length - 1];
                currentFighter.right = (j < fighterNodes[0].length - 1) ? fighterNodes[i][j + 1] : fighterNodes[i][0];
            }
        }
        return fighterNodes;
    }

    private static class FighterNode {
        private String name;
        private FighterNode up;
        private FighterNode down;
        private FighterNode left;
        private FighterNode right;

        private FighterNode(String name) {
            this(name, null, null, null, null);
        }

        private FighterNode(String name, FighterNode up, FighterNode down, FighterNode left, FighterNode right) {
            this.name = name;
            this.up = up;
            this.down = down;
            this.left = left;
            this.right = right;
        }

    }
}
