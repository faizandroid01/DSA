package scheduleplan.fifthOct;/*
Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.

 

Example 1:


Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6
Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped.
Example 2:

Input: height = [4,2,0,3,2,5]
Output: 9
 

Constraints:

n == height.length
1 <= n <= 2 * 104
0 <= height[i] <= 105
*/

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

class TrappingRainWaterEffective {
    public static int trap(int[] h) {
        int l = 0 ;

        //find the first nonnegative
        int k = 0;
        while (k<=h.length-1){
            if (h[k]>0){
                l = k;
                break;
            }
            k++;
        }
        int trappedArea = 0;
        int r = h.length-1 ; int lmax = 0, rmax=0;

        while(l<=r){

            if (h[l] <= h[r]){

                if (h[l] >= lmax){
                    lmax = h[l];
                }else {
                    trappedArea+=lmax - h[l];
                }
                l++;

            }else{

                if (h[r] >= rmax){
                    rmax = h[r];
                }else {
                    trappedArea+=rmax - h[r];
                }
                r--;

            }

        }

        return trappedArea;
    }

    public static void main(String[] args) {
    	
    	System.out.println(trap(new int[] {0,1,0,2,1,0,1,3,2,1,2,1}));
    	System.out.println(trap(new int[] {4,2,0,3,2,5}));
    	System.out.println(trap(new int[] {5,5,1,7,1,1,5,2,7,6}));

    }
}