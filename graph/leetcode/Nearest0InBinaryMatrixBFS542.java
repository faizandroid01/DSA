package graph.leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
* Given an m x n binary matrix mat, return the distance of the nearest 0 for each cell.

The distance between two cells sharing a common edge is 1.



Example 1:


Input: mat = [[0,0,0],[0,1,0],[0,0,0]]
Output: [[0,0,0],[0,1,0],[0,0,0]]
Example 2:


Input: mat = [[0,0,0],[0,1,0],[1,1,1]]
Output: [[0,0,0],[0,1,0],[1,2,1]]


Constraints:

m == mat.length
n == mat[i].length
1 <= m, n <= 104
1 <= m * n <= 104
mat[i][j] is either 0 or 1.
There is at least one 0 in mat.


Note: This question is the same as 1765: https://leetcode.com/problems/map-of-highest-peak/
* */
/*Submission -
* https://leetcode.com/problems/01-matrix/submissions/1930373101/?envType=problem-list-v2&envId=array
*
* Check for DFS , why its tough ?
* Solve this - LC-1765
*
*
* */

public class Nearest0InBinaryMatrixBFS542 {
    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(updateMatrix(new int[][]{{0, 0, 0}, {0, 1, 0}, {1, 1, 1}})));
    }

    public static int[][] updateMatrix(int[][] mat) {

        int m = mat.length;
        int n = mat[0].length;
        boolean[][] visitedArr = new boolean[m][n];
        int[][] distanceMat = new int[m][n];
        Queue<Pair> q = new LinkedList<>();

        for (int r = 0; r < mat.length; r++) {
            for (int col = 0; col < mat[r].length; col++) {
                if(mat[r][col] == 0){
                q.add(new Pair(r, col, 0));
                distanceMat[r][col] = 0;}
            }
        }

        while (!q.isEmpty()) {
            Pair pair = q.poll();
            if (!visitedArr[pair.row][pair.col]) {
                visitedArr[pair.row][pair.col] = true;
                distanceMat[pair.row][pair.col] = pair.distance ;
                bfsTraversalToFindThe0sDistance(pair.row - 1, pair.col, pair.distance, q, visitedArr, distanceMat, mat, m, n);
                bfsTraversalToFindThe0sDistance(pair.row, pair.col + 1, pair.distance, q, visitedArr, distanceMat, mat, m, n);
                bfsTraversalToFindThe0sDistance(pair.row + 1, pair.col, pair.distance, q, visitedArr, distanceMat, mat, m, n);
                bfsTraversalToFindThe0sDistance(pair.row, pair.col - 1, pair.distance, q, visitedArr, distanceMat, mat, m, n);

            }
        }

        return distanceMat;
    }

    private static int bfsTraversalToFindThe0sDistance(int row, int col, int distance, Queue<Pair> q, boolean[][] visitedArr, int[][] distanceMat, int[][] mat, int m, int n) {
        if (row < 0 || row == m || col < 0 || col == n || visitedArr[row][col]) {
            return distance;
        }

        if (mat[row][col] == 1) {
            distance = distance + 1;
            q.add(new Pair(row, col, distance));
        }

        return distance;
    }

    public record Pair(Integer row, Integer col, Integer distance) {
    }


}
