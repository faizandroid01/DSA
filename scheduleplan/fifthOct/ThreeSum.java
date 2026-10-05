/*

Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.

Notice that the solution set must not contain duplicate triplets.

 

Example 1:

Input: nums = [-1,0,1,2,-1,-4]
Output: [[-1,-1,2],[-1,0,1]]
Explanation: 
nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0.
nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0.
nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0.
The distinct triplets are [-1,0,1] and [-1,-1,2].
Notice that the order of the output and the order of the triplets does not matter.
Example 2:

Input: nums = [0,1,1]
Output: []
Explanation: The only possible triplet does not sum up to 0.
Example 3:

Input: nums = [0,0,0]
Output: [[0,0,0]]
Explanation: The only possible triplet sums up to 0.
 

Constraints:

3 <= nums.length <= 3000
-105 <= nums[i] <= 105
*/
package scheduleplan.fifthOct;
import java.util.*;
import java.lang.*;

class ThreeSum {
    public static List<List<Integer>> threeSum(int[] nums) {


		Set<List<Integer>> allElligibleDistinctTwoSumList = new HashSet();


    	for (int i=0; i<nums.length-1 ; i++){

    		int target = 0-nums[i];
    		int []numsModified = new int[nums.length -1 -i];
    		System.arraycopy(nums,i+1,numsModified,0,nums.length -1 -i);
    		Set<List<Integer>> allTwoSumList = twoSum(numsModified , target,nums[i]);

    		if(!allTwoSumList.isEmpty()){
    			allElligibleDistinctTwoSumList.addAll(twoSum(numsModified , target,nums[i]));
    		}

    	}

		List<List<Integer>> l = new ArrayList<>(allElligibleDistinctTwoSumList);

        return l;
    }


    public  static Set<List<Integer>> twoSum(int[] nums, int target, int twoSumFor) {

    	 Set<List<Integer>> allTwoSumList = new HashSet<>();
        
  
	    Map<Integer,Integer> mapOfValuesAndPosition = new HashMap<>();

	    for(int i=0; i<nums.length ; i++ ){

	    	if(mapOfValuesAndPosition.containsKey(target-nums[i])){
	    		List<Integer> list = Arrays.asList(nums[i],(target-nums[i]),twoSumFor);
	    		list.sort((a,b) -> a.compareTo(b));
	    		allTwoSumList.add(list);
	    	}

	    	mapOfValuesAndPosition.put(nums[i],i);

    	}

    	return allTwoSumList;

    }



    public static void main (String [] args ){

    	//System.out.println(threeSum(new int[]{-1,0,1,2,-1,-4}));
    	System.out.println(threeSum(new int[]{-100,-70,-60,110,120,130,160}));

    }

}