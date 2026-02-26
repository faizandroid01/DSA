package graph.leetcode;

import java.util.List;

public class numberOfEnclavesDFS1020 {

    public static void main(String[] args) {
        System.out.println(numEnclaves(new int[][]{{0,0,0,0},{1,0,1,0},{0,1,1,0},{0,0,0,0}}));
    }

    private static void dfsTraversalToMarkAllRelatedToBoundary(int m, int n, int row, int col, int[][] grid, boolean[][] visitedArr) {

        if (row < 0 || col < 0 || row == m || col == n || visitedArr[row][col] || grid[row][col] == 0) {
            return;
        }

        visitedArr[row][col] = true;

        // cover in all 4 direction for a dfs
        dfsTraversalToMarkAllRelatedToBoundary(m, n, row - 1, col, grid, visitedArr);
        dfsTraversalToMarkAllRelatedToBoundary(m, n, row, col + 1, grid, visitedArr);
        dfsTraversalToMarkAllRelatedToBoundary(m, n, row + 1, col, grid, visitedArr);
        dfsTraversalToMarkAllRelatedToBoundary(m, n, row, col - 1, grid, visitedArr);

    }

    public static int numEnclaves(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visitedArr = new boolean[m][n];

        for (int row : List.of(0, m - 1)) {// horizontal
            for (int col = 0; col < n; col++) {
                if (grid[row][col] == 1) {
                    dfsTraversalToMarkAllRelatedToBoundary(m, n, row, col, grid, visitedArr);
                }
            }
        }


        for (int row = 0; row < m; row++) { //vertical
            for (int col : List.of(0, n - 1)) {
                if (grid[row][col] == 1) {
                    dfsTraversalToMarkAllRelatedToBoundary(m, n, row, col, grid, visitedArr);
                }
            }
        }
        int noOfLandCells = 0;
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {
                if (!visitedArr[r][c] && grid[r][c]==1) {
                   noOfLandCells++;
                }
            }
        }

        return noOfLandCells;

    }
}
