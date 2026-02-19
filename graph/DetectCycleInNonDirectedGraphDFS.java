package graph;

import java.util.*;

public class DetectCycleInNonDirectedGraphDFS {

    public static void main(String[] args) {
        System.out.println(cycleExistInDirectedGraph(9, new int[][]{
                {1, 2}, {2, 3}, {3, 4}, {4, 5}, {5, 6}, {6, 7}, {7, 8}, {8, 9}, {8, 1}
        }));
    }

    public static boolean cycleExistInDirectedGraph(int numOfNodes, int[][] inputArray) {

        boolean[] visitedArr = new boolean[numOfNodes + 1];
        Map<Integer, List<Integer>> adjacencyListMap = new HashMap<>(numOfNodes + 1);

        //parseTheInputArr
        Arrays.stream(inputArray).forEach(row -> {
            adjacencyListMap.computeIfAbsent(row[0], k -> new ArrayList<>()).add(row[1]);
            adjacencyListMap.computeIfAbsent(row[1], k -> new ArrayList<>()).add(row[0]);
        });

        List<Integer> resultOfLoop = new ArrayList<>(numOfNodes);

        //traverse for the nodes
        for (int node = 1; node <= numOfNodes; node++) {
            if (!visitedArr[node]) {
                resultOfLoop.add(node);
                if (dfsTraversalOfUndirectedGraphRecursively(node, -1, visitedArr, adjacencyListMap,resultOfLoop)) {
                    System.out.println(resultOfLoop.toString());
                    return true;
                }else{
                    resultOfLoop.remove((Integer)node);
                }
            }
        }
        return false;
    }

    private static boolean dfsTraversalOfUndirectedGraphRecursively(int node, int parent, boolean[] visitedArr, Map<Integer, List<Integer>> adjacencyListMap, List<Integer> resultOfLoop) {
        if (null == adjacencyListMap.get(node) || adjacencyListMap.get(node).isEmpty()) {
            visitedArr[node] = true;
            return false;
        }
        if (visitedArr[node]) {
            return true;
        }

        visitedArr[node] = true;
        for (Integer nNode : adjacencyListMap.get(node)) {
            if (nNode != parent ) {
                resultOfLoop.add(nNode);
                if (dfsTraversalOfUndirectedGraphRecursively(nNode, node, visitedArr, adjacencyListMap, resultOfLoop)) {
                    return true;
                }else{
                    resultOfLoop.remove((Integer)nNode);
                }
            }
        }



        return false;
    }


}
