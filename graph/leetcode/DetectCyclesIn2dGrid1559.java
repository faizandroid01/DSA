package graph.leetcode;

/*Given a 2D array of characters grid of size m x n, you need to find if there exists any cycle consisting of the same value in grid.

A cycle is a path of length 4 or more in the grid that starts and ends at the same cell. From a given cell, you can move to one of the cells adjacent to it - in one of the four directions (up, down, left, or right), if it has the same value of the current cell.

Also, you cannot move to the cell that you visited in your last move. For example, the cycle (1, 1) -> (1, 2) -> (1, 1) is invalid because from (1, 2) we visited (1, 1) which was the last visited cell.

Return true if any cycle of the same value exists in grid, otherwise, return false.



Example 1:



Input: grid = [["a","a","a","a"],["a","b","b","a"],["a","b","b","a"],["a","a","a","a"]]
Output: true
Explanation: There are two valid cycles shown in different colors in the image below:

Example 2:



Input: grid = [["c","c","c","a"],["c","d","c","c"],["c","c","e","c"],["f","c","c","c"]]
Output: true
Explanation: There is only one valid cycle highlighted in the image below:

Example 3:



Input: grid = [["a","b","b"],["b","z","b"],["b","b","a"]]
Output: false


Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 500
grid consists only of lowercase English letters.

*/
/*
* Solve using dfs for the same - see a better approach from the submission -
* https://leetcode.com/problems/detect-cycles-in-2d-grid/submissions/1922461898/
*
*  */






import java.util.LinkedList;
import java.util.Queue;

public class DetectCyclesIn2dGrid1559 {

    public static boolean containsCycle(char[][] grid) {
        boolean containsCycle = false;

        int noOfRows = grid.length;
        int noOfCols = grid[0].length;
        int[][] visitedArray = new int[noOfRows][noOfCols];

        Queue<Pair> q = new LinkedList<>();

        for (int r = 0; r < noOfRows; r++) {
            for (int c = 0; c < noOfCols; c++) {

                if (visitedArray[r][c] == 0) {
                    q.add(new Pair(r, c,-1,-1));
                    visitedArray[r][c] = 1;
                    containsCycle = traverseForTheCurrentCell(visitedArray, grid, q, noOfRows, noOfCols, grid[r][c]);
                    if (containsCycle) {
                        return containsCycle;
                    }
                }
            }
        }

        return containsCycle;
    }

    private static boolean traverseForTheCurrentCell(int[][] visitedArray, char[][] grid, Queue<Pair> q, int noOfRows, int noOfCols, char initialVal) {

        while (!q.isEmpty()) {
            Pair p = q.poll();
            if (insertRequiredintotheQueue(p.fromRow, p.fromCol,p.row,p.col,grid, visitedArray, noOfRows, noOfCols, p.row - 1, p.col, q, initialVal) ||
                    insertRequiredintotheQueue(p.fromRow, p.fromCol,p.row,p.col,grid, visitedArray, noOfRows, noOfCols, p.row, p.col + 1, q, initialVal) ||
                    insertRequiredintotheQueue(p.fromRow, p.fromCol,p.row,p.col,grid, visitedArray, noOfRows, noOfCols, p.row + 1, p.col, q, initialVal) ||
                    insertRequiredintotheQueue(p.fromRow, p.fromCol,p.row,p.col,grid, visitedArray, noOfRows, noOfCols, p.row, p.col - 1, q, initialVal)) {
                return true;
            }
        }

        return false;
    }

    private static boolean insertRequiredintotheQueue(int fromRow,int fromCol,int r, int c, char[][] grid, int[][] visitedArray, int noOfRows, int noOfCols, Integer row, Integer col, Queue<Pair> q, char initialVal) {
        if (row < 0 || col < 0 || row == noOfRows || col == noOfCols || initialVal != grid[row][col]) {
            return false;
        }

        if(row==fromRow && col==fromCol){
            visitedArray[row][col] = 1;
            return false;
        }

        if (visitedArray[row][col] == 1) {
            return true;
        }
        q.add(new Pair(row, col,r,c));
        visitedArray[row][col] = 1;
        return false;
    }

    public static void main(String[] args) {
        System.out.println(containsCycle(new char[][]{{'a','a','a','a'},{'a','b','b','a'},{'a','b','b','a'},{'a','a','a','a'}}));
        System.out.println(containsCycle(new char[][]{{'c','c','c','a'},{'c','d','c','c'},{'c','c','e','c'},{'f','c','c','c'}}));
        System.out.println(containsCycle(new char[][]{{'a','b','b'},{'b','z','b'},{'b','b','a'}}));

    }

    public record Pair(Integer row, Integer col,Integer fromRow, Integer fromCol) {
    }



/*    private void dfsTraversalOnGrid(char[][] grid, int[][] visitedArray, int noOfRows, int noOfCols, int r, int c, Queue<Pair> q, char initialVal) {

        visitedArray[r][c]=1;
        if (r<0 || c<0 || r==noOfRows|| c ==noOfCols || initialVal!=grid[r][c]){
            return;
        }

        bfsTraversalOnGrid(grid,visitedArray,noOfRows,noOfCols,r-1,c,q,initialVal);
        bfsTraversalOnGrid(grid,visitedArray,noOfRows,noOfCols,r,c+1,q,initialVal);
        bfsTraversalOnGrid(grid,visitedArray,noOfRows,noOfCols,r-1,c,q,initialVal);
        bfsTraversalOnGrid(grid,visitedArray,noOfRows,noOfCols,r-1,c-1,q,initialVal);



    }*/

}
