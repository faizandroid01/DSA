package graph.leetcode;


import java.util.Arrays;
import java.util.List;

/*
* You are given an m x n matrix board containing letters 'X' and 'O', capture regions that are surrounded:

Connect: A cell is connected to adjacent cells horizontally or vertically.
Region: To form a region connect every 'O' cell.
Surround: A region is surrounded if none of the 'O' cells in that region are on the edge of the board. Such regions are completely enclosed by 'X' cells.
To capture a surrounded region, replace all 'O's with 'X's in-place within the original board. You do not need to return anything.



Example 1:

Input: board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]

Output: [["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]

Explanation:


In the above diagram, the bottom region is not captured because it is on the edge of the board and cannot be surrounded.

Example 2:

Input: board = [["X"]]

Output: [["X"]]



Constraints:

m == board.length
n == board[i].length
1 <= m, n <= 200
board[i][j] is 'X' or 'O'.
*
* */
/*PLAN -
 * Plan is to iterated over all the edges and mark the fs or bfs traversal over the 0's , THis will consume all the 0's connected to the boundary .
 * Mark others as X
 *
 * */
public class SurroundedRegion130 {

    public static void main(String[] args) {
        solve(new char[][]{{'X','X','X','X'},{'X','O','O','X'},{'X','X','O','X'},{'X','O','X','X'}});
    }

    public static void solve(char[][] board) {

        int m = board.length;
        int n = board[0].length;
        boolean[][] visitedArr = new boolean[m][n];

        for (int row : List.of(0, m - 1)) {// horizontal
            for (int col = 0; col < n; col++) {
                if(board[row][col]=='O'){
                    dfsTraversalToMarkAllRelatedToBoundary(m,n,row,col,board,visitedArr);
                }
            }
        }


        for (int row = 0; row < m; row++) { //vertical
            for (int col : List.of(0, n - 1)) {
                if(board[row][col]=='O'){
                    dfsTraversalToMarkAllRelatedToBoundary(m,n,row,col,board,visitedArr);
                }
            }
        }

        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[r].length; c++) {
                if(!visitedArr[r][c]){
                    board[r][c]='X';
                }
            }
        }

        Arrays.stream(board).forEach( k -> System.out.print(Arrays.toString(k)));

    }

    private static void dfsTraversalToMarkAllRelatedToBoundary(int m, int n, int row, int col, char[][] board, boolean[][] visitedArr) {

        if(row<0 || col < 0 || row==m || col==n || visitedArr[row][col] || board[row][col]=='X'){
            return ;
        }

        visitedArr[row][col]=true;

        // cover in all 4 direction for a dfs
        dfsTraversalToMarkAllRelatedToBoundary(m,n,row-1,col,board,visitedArr);
        dfsTraversalToMarkAllRelatedToBoundary(m,n,row,col+1,board,visitedArr);
        dfsTraversalToMarkAllRelatedToBoundary(m,n,row+1,col,board,visitedArr);
        dfsTraversalToMarkAllRelatedToBoundary(m,n,row,col-1,board,visitedArr);

    }


}
