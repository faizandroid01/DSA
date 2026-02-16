package graph.leetcode;

/*
* You are given an image represented by an m x n grid of integers image, where image[i][j] represents the pixel value of the image. You are also given three integers sr, sc, and color. Your task is to perform a flood fill on the image starting from the pixel image[sr][sc].

To perform a flood fill:

Begin with the starting pixel and change its color to color.
Perform the same process for each pixel that is directly adjacent (pixels that share a side with the original pixel, either horizontally or vertically) and shares the same color as the starting pixel.
Keep repeating this process by checking neighboring pixels of the updated pixels and modifying their color if it matches the original color of the starting pixel.
The process stops when there are no more adjacent pixels of the original color to update.
Return the modified image after performing the flood fill.
*
*
* Input: image = [[1,1,1],[1,1,0],[1,0,1]], sr = 1, sc = 1, color = 2

Output: [[2,2,2],[2,2,0],[2,0,1]]

Explanation:
*
*
* From the center of the image with position (sr, sc) = (1, 1) (i.e., the red pixel), all pixels connected by a path of the same color as the starting pixel (i.e., the blue pixels) are colored with the new color.

Note the bottom corner is not colored 2, because it is not horizontally or vertically connected to the starting pixel.

Example 2:

Input: image = [[0,0,0],[0,0,0]], sr = 0, sc = 0, color = 0

Output: [[0,0,0],[0,0,0]]

Explanation:

The starting pixel is already colored with 0, which is the same as the target color. Therefore, no changes are made to the image.



Constraints:

m == image.length
n == image[i].length
1 <= m, n <= 50
0 <= image[i][j], color < 216
0 <= sr < m
0 <= sc < n


*
* */

/*
* Solution:
*
* https://leetcode.com/problems/flood-fill/submissions/1920675971/
*
* Suggestion b4 next solve - check to print array efficiently
* */

import java.util.Arrays;
import java.util.List;

public class FloodFill733 {
    public static int[][] floodFill(int[][] image, int sr, int sc, int color) {

        int[][] visitedArr = new int[image.length][image[0].length];

        int colorToBeReplaced = image[sr][sc];
        int n = image.length;
        int m = image[0].length;
        return traverseTheMatrixInDfsRecursively(image,visitedArr,sr,sc,color,colorToBeReplaced,n,m);
    }

    private static int[][] traverseTheMatrixInDfsRecursively(int[][] image, int[][] visitedArr, int sr, int sc, int color, int colorToBeReplaced,int n, int m) {

        visitedArr[sr][sc]=1;
        image[sr][sc]=color;

        for(int nCol : List.of(sc-1,sc+1)){
            if(nCol < 0 || nCol >= m || image[sr][nCol]!=colorToBeReplaced || visitedArr[sr][nCol]!=0 ){
                continue;
            }
            traverseTheMatrixInDfsRecursively(image,visitedArr,sr,nCol,color,colorToBeReplaced,n,m);
        }
        for(int nRow : List.of(sr-1,sr+1)){
            if(nRow < 0 || nRow >= n || image[nRow][sc]!=colorToBeReplaced || visitedArr[nRow][sc]!=0 ){
                continue;
            }
            traverseTheMatrixInDfsRecursively(image,visitedArr,nRow,sc,color,colorToBeReplaced,n,m);
        }

        return image;
    }

    public static void main(String[] args) {

        List<int [][]> inputArrs =  Arrays.asList(new int[][]{{1,1,1},{1,1,0},{1,0,1}},new int[][]{{0,0,0},{0,0,0}});

        System.out.println(Arrays.deepToString(floodFill(inputArrs.get(0),1,1,2)));
        System.out.println(Arrays.deepToString(floodFill(inputArrs.get(1),0,0,0)));



    }

}
