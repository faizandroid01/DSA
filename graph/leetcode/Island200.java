package graph.leetcode;

/*
Number of Islands
Medium
Topics
premium lock icon
Companies
Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.

An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.



Example 1:

Input: grid = [
  ["1","1","1","1","0"],
  ["1","1","0","1","0"],
  ["1","1","0","0","0"],
  ["0","0","0","0","0"]
]

Output: 1
Example 2:

Input: grid = [
  ["1","1","0","0","0"],
  ["1","1","0","0","0"],
  ["0","0","1","0","0"],
  ["0","0","0","1","1"]
]
Output: 3


Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 300
grid[i][j] is '0' or '1'.


Solution submitted -
*/

/*
 * SUGGESTION B4 next solve -
 *
 * 1.
 *
 * */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Island200 {

    public static int numIslands(char[][] grid) {

        int n = grid.length;
        int m = grid[0].length;

        //initialising visited array
        char[][] visitedArr = new char[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                visitedArr[i][j] = '0';
            }
        }
        Arrays.stream(visitedArr).forEach(System.out::println);

        /* iterate on the matrix nodes to start the dfs for that cell and in turn it calls the DFS of
        all other near elements recursively on the basis of visited array/*
         */
        int numOfIslands = 0;
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {

                if (visitedArr[row][col] == '0') {
                    if (grid[row][col] == '1') {
                        visitedArr[row][col] = '1';
                        numOfIslands++;
                        dfsTraversalForOtherIslandsCheck(visitedArr, grid, row, col, m, n);
                    }
                }
            }
        }
        return numOfIslands;
    }

    private static void dfsTraversalForOtherIslandsCheck(char[][] visitedArr, char[][] grid, int row, int col, int m, int n) {

        //for same row
        for (int nCol : List.of(col - 1, col + 1)) {
            if (nCol >= 0 && nCol < m && grid[row][nCol] == '1' && visitedArr[row][nCol] == '0') {
                visitedArr[row][nCol] = '1';
                dfsTraversalForOtherIslandsCheck(visitedArr, grid, row, nCol, m, n);
            }
        }

        // for same col
        for (int nRow : List.of(row - 1, row + 1)) {
            if (nRow >= 0 && nRow < n && grid[nRow][col] == '1' && visitedArr[nRow][col] == '0') {
                visitedArr[nRow][col] = '1';
                dfsTraversalForOtherIslandsCheck(visitedArr, grid, nRow, col, m, n);
            }
        }

    }

    public static void main(String[] args) {

        List<char[][]> inputArrays = new ArrayList<>();
        inputArrays.add(new char[][]{
                {'1', '1', '1', '1', '0'}, {'1', '1', '0', '1', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '0', '0', '0'}
        });
        inputArrays.add(new char[][]{
                {'1', '1', '0', '0', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '1', '0', '0'}, {'0', '0', '0', '1', '1'}
        });

        inputArrays.forEach(x -> System.out.println(numIslands(x)));


    }

}
