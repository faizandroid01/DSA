package graph.leetcode;

/*
* You are given an m x n grid where each cell can have one of three values:

0 representing an empty cell,
1 representing a fresh orange, or
2 representing a rotten orange.
Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.

Return the minimum number of minutes that must elapse until no cell has a fresh orange. If this is impossible, return -1.



Example 1:


Input: grid = [[2,1,1],[1,1,0],[0,1,1]]
Output: 4
Example 2:

Input: grid = [[2,1,1],[0,1,1],[1,0,1]]
Output: -1
Explanation: The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting only happens 4-directionally.
Example 3:

Input: grid = [[0,2]]
Output: 0
Explanation: Since there are already no fresh oranges at minute 0, the answer is just 0.


Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 10
grid[i][j] is 0, 1, or 2.
* */

/*
* Search for optimised solution next time .
* i dont think I have used Graph or dfs ,
* look for better options .
*
* */

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class RottenOranges994 {

    public static int orangesRotting(int[][] grid) {

        int r = grid.length;
        int c = grid[0].length;
        int timeTaken = -1;

        // make a visited arr
        int [][] visitedArray = new int[r][c];

        //check for no orange , then return 0
        boolean rottenOrangesExist = Arrays.stream(grid).flatMapToInt(Arrays::stream).anyMatch(x -> x ==2);
        boolean freshOrangesExist = Arrays.stream(grid).flatMapToInt(Arrays::stream).anyMatch(x -> x ==1);

        if(freshOrangesExist && !rottenOrangesExist){return -1;}
        if(!freshOrangesExist && !rottenOrangesExist){return 0;}


        // check for if it only contains fresh orange and no rotten orange then return -1

        Queue<Pair> queue = new LinkedList<>();

            for (int row = 0; row < r; row++) {
                for (int col = 0; col < c; col++) {

                    if (visitedArray[row][col] == 0 && grid[row][col] == 2) {
                        queue.add(new Pair(row, col));
                    }
                }
            }

        while (!queue.isEmpty()) {

            int len = queue.size();

            while (len > 0) {
                Pair pair = queue.poll();
                visitedArray[pair.row][pair.column] =1 ;
                traverseForTheRottenPair(grid, visitedArray, pair.row - 1, pair.column, queue);
                traverseForTheRottenPair(grid, visitedArray, pair.row + 1, pair.column, queue);
                traverseForTheRottenPair(grid, visitedArray, pair.row, pair.column + 1, queue);
                traverseForTheRottenPair(grid, visitedArray, pair.row, pair.column - 1, queue);
                len--;
            }

            timeTaken++;
        }


//        System.out.println(Arrays.deepToString(grid));
//        System.out.println(Arrays.deepToString(visitedArray));

        // check after the process of there's still left fresh oranges , if left return -1 implies ,
        // not all the mangoes were able to rot

        boolean ifFreshOrangesExist = Arrays.stream(grid).flatMapToInt(x -> Arrays.stream(x)).anyMatch(val -> val==1);

        return !ifFreshOrangesExist ? timeTaken : -1;
    }

    private static void traverseForTheRottenPair(int[][] grid, int[][] visitedArray, Integer row, Integer column, Queue<Pair> queue) {

        if(row < 0 || row >= grid.length || column < 0 || column >= grid[row].length || visitedArray[row][column]==1)
            return;

        if(grid[row][column] == 1) {
            visitedArray[row][column] =1 ;
            grid[row][column]=2;
            queue.add(new Pair(row,column));
        }
    }

    public record Pair(Integer row, Integer column) {}

    public static void main(String[] args) {
        int [][] grid = new int[][]{{2,1,1},{1,1,0},{0,1,1}};
        //int [][] grid = new int[][]{{0,2}};
        //int [][] grid = new int[][]{{2,1,1},{0,1,1},{1,0,1}};
        //System.out.println(Arrays.deepToString(grid));
        System.out.println(orangesRotting(grid));
    }
}
