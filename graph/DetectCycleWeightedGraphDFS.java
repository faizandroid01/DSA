package graph;

import java.util.*;

public class DetectCycleWeightedGraphDFS {
    public static void main(String[] args) {
        System.out.println(cycleExistInDirectedGraph(10,new int[][]{{1,2},{2,3},{3,4},{3,7},{4,5},{5,6},{7,5},{8,2},{8,9},{9,10},{10,8}}));
        System.out.println(cycleExistInDirectedGraph(5,new int[][]{{0,1},{1,2},{2,0},{3,4},{4,5}}));
    }

    public static boolean cycleExistInDirectedGraph(int numOfNodes, int[][] inputArray) {
        Map<Integer, List<Integer>> adjacencyListMap = new HashMap<>();
        Arrays.stream(inputArray).forEach(k -> {
                    adjacencyListMap.computeIfAbsent(k[0], m -> new ArrayList<>()).add(k[1]);
                }
        );
        int[] visitedArr = new int[numOfNodes + 1];
        int[] pathVisitedArr = new int[numOfNodes + 1];

        for (int i = 1; i <= numOfNodes; i++) {
            if (visitedArr[i] == 0) {
                if (dfsTraversalToCheckTheCycle(i, visitedArr, pathVisitedArr, adjacencyListMap)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean dfsTraversalToCheckTheCycle(int i, int[] visitedArr, int[] pathVisitedArr, Map<Integer, List<Integer>> adjacencyListMap) {

        visitedArr[i] = 1;
        pathVisitedArr[i] = 1;

        if (null == adjacencyListMap.get(i)) {
            return false;
        }

        for (Integer nNode : adjacencyListMap.get(i)) {
            if (visitedArr[nNode] == 0) {
                if(dfsTraversalToCheckTheCycle(nNode, visitedArr, pathVisitedArr, adjacencyListMap)){
                    return true;
                }
                pathVisitedArr[nNode] = 0;
            }
            if(visitedArr[nNode]==1 && pathVisitedArr[nNode]==1){
                //already visited on same path
                return true;
            }
        }

        return false;
    }


}
