package graph.leetcode;

import java.util.ArrayList;
import java.util.List;

/*
547. Number of Provinces
There are n cities. Some of them are connected, while some are not. If city a is connected directly with city b, and city b is connected directly with city c, then city a is connected indirectly with city c.
A province is a group of directly or indirectly connected cities and no other cities outside of the group.
You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the ith city and the jth city are directly connected, and isConnected[i][j] = 0 otherwise.
Return the total number of provinces.

Example-1
Input: isConnected = [[1,1,0],[1,1,0],[0,0,1]]
Output: 2

Example-2
Input: isConnected = [[1,0,0],[0,1,0],[0,0,1]]
Output: 3

Constraints:

1 <= n <= 200
n == isConnected.length
n == isConnected[i].length
isConnected[i][j] is 1 or 0.
isConnected[i][i] == 1
isConnected[i][j] == isConnected[j][i]

Solution submitted - https://leetcode.com/problems/number-of-provinces/submissions/1918929836/
*/
public class Provinces547 {

    public static void main(String[] args) {
        List<int[][]> arrays = new ArrayList<>();
        arrays.add(new int[][]{{1, 0, 0}, {0, 1, 0}, {0, 0, 1}});
        arrays.add(new int[][]{{1, 1, 0}, {1, 1, 0}, {0, 0, 1}});

        arrays.forEach(x -> System.out.println(findCircleNum(x)));
    }

    public static int findCircleNum(int[][] isConnected) {
        boolean visitedArr[] = new boolean[isConnected.length + 1];
        return traverseOnGraphDFS(visitedArr, isConnected);
    }

    private static int traverseOnGraphDFS(boolean[] visitedArr, int[][] isConnected) {
        int count = 0;
        for (int row = 0; row < isConnected.length; row++) {
            if (!visitedArr[row + 1]) {
                count++;
                traverseOnGraphDFSRecursively(row, isConnected, visitedArr);
            }
        }
        return count;
    }

    private static void traverseOnGraphDFSRecursively(int row, int[][] isConnected, boolean[] visitedArr) {

        if (!visitedArr[row + 1]) {
            visitedArr[row + 1] = true;
            for (int col = 0; col < isConnected[row].length; col++) {
                if (col != row && isConnected[row][col] == 1) {
                    traverseOnGraphDFSRecursively(col, isConnected, visitedArr);
                }

            }
        }

    }


}