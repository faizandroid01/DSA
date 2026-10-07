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

class TrappingRainWater {
    public static int trap(int[] h) {

      int l=0 , r = h.length-1;
      int totalTrapArea = 0;
      int cth = 0; //calculatedtrapheight
      int maxleftHeight = 0, maxRightHeight = 0;

      while (l<r){

        // skipping for zero
        if(h[l] == 0 || h[l] <= cth || h[l]<=maxleftHeight){
            l++;
            continue;
        }
        // skipping for zero
        if(h[r]==0 || h[r] <= cth || h[r] <= maxRightHeight){
            r--;
            continue;
        }


        if(h[l] < h[r]){
            maxleftHeight = Math.max(h[l],maxleftHeight); maxRightHeight = Math.max(h[r],maxRightHeight);
        
        int gap = r-l-1;
        totalTrapArea += h[l] * gap;
        
      //calculate effective till height
        int tr = r-1;
        while(tr > l ){
            totalTrapArea  =  totalTrapArea - Math.min(h[tr],h[l])   ;
            tr--;
        }

            cth = h[l];
            l++;
        }else if (h[l] > h[r]) {
            maxleftHeight = Math.max(h[l],maxleftHeight); maxRightHeight = Math.max(h[r],maxRightHeight);
        int gap = r-l-1;
        totalTrapArea += h[r] * gap;

          //calculate effective till height
        int tr = r-1;
        while(tr > l ){
            totalTrapArea  =  totalTrapArea - Math.min(h[tr],h[r])  ;
            tr--;
        }

            cth = h[r];
            r--;
        }else {
            maxleftHeight = Math.max(h[l],maxleftHeight); maxRightHeight = Math.max(h[r],maxRightHeight);
        int gap = r-l-1;
        totalTrapArea += (h[r]) * gap; // can be any height both are same
       
        //calculate effective till height
        int tr = r-1;
        while(tr > l ){
            totalTrapArea  =  totalTrapArea -   ( (h[tr]<cth) ?  cth :  Math.min(h[tr],h[r]) );
            tr--;
        }

        cth = h[r]; //any height
        l++;
        r--;
        }
        
      }

    return totalTrapArea;
    }

    public static void main(String[] args) {
    	
    	System.out.println(trap(new int[] {0,1,0,2,1,0,1,3,2,1,2,1}));
    	System.out.println(trap(new int[] {4,2,0,3,2,5}));
    	System.out.println(trap(new int[] {5,5,1,7,1,1,5,2,7,6}));

    }
}