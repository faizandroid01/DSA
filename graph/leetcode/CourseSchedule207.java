package graph.leetcode;


import java.util.*;

/*

Course Schedule
* There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.

For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
Return true if you can finish all courses. Otherwise, return false.



Example 1:

Input: numCourses = 2, prerequisites = [[1,0]]
Output: true
Explanation: There are a total of 2 courses to take.
To take course 1 you should have finished course 0. So it is possible.
Example 2:

Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
Output: false
Explanation: There are a total of 2 courses to take.
To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.


Constraints:

1 <= numCourses <= 2000
0 <= prerequisites.length <= 5000
prerequisites[i].length == 2
0 <= ai, bi < numCourses
All the pairs prerequisites[i] are unique.
* */

/*
* Suggestion before next solve .Solve it using BFS
* No matter what you will need  parent node along int the loop to detect the cycle
*
* */
public class CourseSchedule207 {

    public static void main(String[] args) {

        System.out.println(canFinish(2, new int[][]{{1, 0}}));
        System.out.println(canFinish(2, new int[][]{{1, 0}, {0, 1}}));
        System.out.println(canFinish(3, new int[][]{{1, 0}, {2, 1}}));
        System.out.println(canFinish(6, new int[][]{ {1, 0}, {1, 2}, {3, 1}, {2, 3}, {2, 4}, {4, 5}, {2, 5}}));
        System.out.println(canFinish(3, new int[][]{ {0, 1}, {0, 2}, {1, 2}}));
        System.out.println(canFinish(20, new int[][]{ {0, 10}, {3, 18}, {5, 5}, {6, 11}, {11, 14}, {13, 1}, {15, 1}, {17, 4}})); //self loop
        System.out.println(canFinish(7, new int[][]{ {1, 0}, {0, 3}, {0, 2}, {3, 2}, {2, 5}, {4, 5}, {5, 6}, {2, 4}})); // diff loops in directed graph


    }

    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        boolean canFinish = true;
        boolean selfLoop = false;
        int[] visitedArr = new int[numCourses];

        //create adjacency List
        Map<Integer, List<Integer>> adjacencyListMap = new HashMap<>();
        Map<Integer, List<Integer>> nodeParentsMap = new HashMap<>();

        for (int[] x : prerequisites) {
            if (x[0] == x[1]) {
                selfLoop = true;
            }
            nodeParentsMap.computeIfAbsent(x[0], a -> new ArrayList<>()).add(x[1]);
            adjacencyListMap.computeIfAbsent(x[1], a -> new ArrayList<>()).add(x[0]);
        }

        if (selfLoop) {
            return false;
        }

//        System.out.println(Arrays.deepToString(prerequisites));
//        System.out.println(adjacencyListMap.toString());

        for (int node = 0; node < numCourses; node++) {
            visitedArr = new int[numCourses];
            if (traversalOfCoursesViaDFSToDetectCycle(node,-1, visitedArr, adjacencyListMap,nodeParentsMap)) {
                //if this comes out to be true , then there exist cycle and courses cant be completed
                return false;
            }
        }

        return canFinish;
    }

    private static boolean traversalOfCoursesViaDFSToDetectCycle(int node, int fromNode, int[] visitedArr, Map<Integer, List<Integer>> adjacencyListMap, Map<Integer, List<Integer>> nodeParentsMap) {
        if (null == adjacencyListMap.get(node) || adjacencyListMap.get(node).isEmpty()) {
            return false;
        }

        if (visitedArr[node] == 1) {
            // incase of visited true : check if it was done via parent , then it cant be a cycle .
            Optional<Integer> visitedParent = nodeParentsMap.get(node).stream().filter(x -> x !=fromNode).filter(x -> visitedArr[x] == 1).findFirst();
            return visitedParent.isEmpty();
        }

        visitedArr[node] = 1;

        for (int index = 0; index < adjacencyListMap.get(node).size(); index++) {
            visitedArr[node] = 1;
          if (traversalOfCoursesViaDFSToDetectCycle(adjacencyListMap.get(node).get(index), node, visitedArr, adjacencyListMap, nodeParentsMap)){
              return true;
          }

        }
        return false;
    }

}
