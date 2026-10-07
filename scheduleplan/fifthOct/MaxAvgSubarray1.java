/*
You are given an integer array nums consisting of n elements, and an integer k.

Find a contiguous subarray whose length is equal to k that has the maximum average value and return this value. Any answer with a calculation error less than 10-5 will be accepted.

 

Example 1:

Input: nums = [1,12,-5,-6,50,3], k = 4
Output: 12.75000
Explanation: Maximum average is (12 - 5 - 6 + 50) / 4 = 51 / 4 = 12.75
Example 2:

Input: nums = [5], k = 1
Output: 5.00000
 

Constraints:

n == nums.length
1 <= k <= n <= 105
-104 <= nums[i] <= 104
 
Seen this question in a real interview before?
1/6


*/

class MaxAvgSubarray1 {
    public double findMaxAverage(int[] nums, int k) {
        

    	
        
            int effectiveLen = nums.length-k ;
            double maxAvg = Double.NEGATIVE_INFINITY;
            System.out.println(""+maxAvg);
            for (int i=0; i<=effectiveLen; i++){
                
                double sum =0;
                int count = k;
                int m = i;
                while(count>0){
                    sum+=nums[m];
                    m++;
                    count--;
                }

                double avg = sum/k;
                maxAvg = Math.max(avg,maxAvg);

            }

            return maxAvg;


    }
}