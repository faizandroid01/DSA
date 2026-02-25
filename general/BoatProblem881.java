package general;

import java.util.Arrays;
import java.util.Collections;

/*

881. Boats to Save People

avatar
Discuss Approach
arrow-up
Solved
Medium
Topics
premium lock icon
Companies
You are given an array people where people[i] is the weight of the ith person, and an infinite number of boats where each boat can carry a maximum weight of limit. Each boat carries at most two people at the same time, provided the sum of the weight of those people is at most limit.

Return the minimum number of boats to carry every given person.



Example 1:

Input: people = [1,2], limit = 3
Output: 1
Explanation: 1 boat (1, 2)
Example 2:

Input: people = [3,2,2,1], limit = 3
Output: 3
Explanation: 3 boats (1, 2), (2) and (3)
Example 3:

Input: people = [3,5,3,4], limit = 5
Output: 4
Explanation: 4 boats (3), (3), (4), (5)


Constraints:

1 <= people.length <= 5 * 104
1 <= people[i] <= limit <= 3 * 104
 * Example 1
 *  1 3  2  2  1 2   ,  limit 3
 * no of Boats required = 4
 *
 * * Example 2
 *  2 2 2 2 2 3  ,  limit 3
 * no of Boats required = 6
 *
 *
 *
 * */

/*Submission
*
* https://leetcode.com/problems/boats-to-save-people/submissions/1930975465/
*
* */
public class BoatProblem881 {
    public static void main(String[] args) {
        System.out.println(numRescueBoats(new int[]{1, 3, 2, 2, 1, 2}, 3));
        System.out.println(numRescueBoats(new int[]{2, 2, 2, 2, 2, 3}, 3));
    }

    public static int numRescueBoats(int[] people, int limit) {
        people = Arrays.stream(people).boxed().sorted(Collections.reverseOrder()).mapToInt(Integer::intValue).toArray();

        int boatCount = 0;
        for (int i = 0; i < people.length; ) {
            for (int j = people.length - 1; j >= i; ) {


                if (i == j) {
                    boatCount++;
                    i++;
                    j--;
                    break;

                }
                if (people[i] + people[j] <= limit) {
                    i++;
                    j--;
                    boatCount++;
                    continue;
                }

                if (people[i] == limit) {
                    i++;
                    boatCount++;
                    continue;
                }

                if (people[j] == limit) {
                    j--;
                    boatCount++;
                    continue;
                }

                if (people[i] + people[j] > limit) {
                    i++;
                    boatCount++;
                    continue;
                }


            }
            break;
        }

        return boatCount;
    }

}
