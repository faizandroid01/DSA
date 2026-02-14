package graph;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/*
-> Array : visited / unvisited arr initialized with 0
-> List<List<Integer> > : form the adjacency list
-> Queue : to run with the traversal

*/

/*
 * n nodes : 9
 * m edges : 9 {(1,2),(2,3),(2,4),(4,5),(5,8),(8,7),(7,6),(6,9),(6,1)}
 *
 * represented by :
 * */

public class Graph {
    private static Map<Integer, List<Integer>> parseTheInputToAdjacencyList(String inputRepresentation, int m, int startingPoint) {
        Map<Integer, List<Integer>> graphList = new LinkedHashMap<>(m);

        //parse the input string to graph List
        Pattern pattern = Pattern.compile("\\((\\d+),(\\d+)\\)");
        Matcher matcher = pattern.matcher(inputRepresentation);

        while (matcher.find()) {
            int from = Integer.parseInt(matcher.group(1));
            int to = Integer.parseInt(matcher.group(2));
            graphList.computeIfAbsent(from, k -> new ArrayList<>()).add(to);
            graphList.computeIfAbsent(to, k -> new ArrayList<>()).add(from);
        }

        //rebuilding of adjacency list is required, if starting point is not 1.
        Map<Integer, List<Integer>> rebuildList = new LinkedHashMap<>();

        rebuildList.put(startingPoint, graphList.get(startingPoint));
        graphList.forEach((k, v) -> {
            if (k != startingPoint) {
                rebuildList.put(k, v);
            }
        });

        return rebuildList;
    }

    private static void traverseGraphInBFS(boolean[] visitedArr, Map<
            Integer, List<Integer>> graphList, Queue<Integer> queue) {
        //This method helps to traverse all the graph node irrespective of the fact that they might be disjoint/ connected components.

        for (Integer node : graphList.keySet()) {
            if (!visitedArr[node]) {
                queue.add(node);
                traverseAllSubNodeForNode(graphList, queue, visitedArr);
            }
        }
    }

    private static void traverseAllSubNodeForNode
            (Map<Integer, List<Integer>> graphList, Queue<Integer> queue, boolean[] visitedArr) {

        while (!queue.isEmpty()) {
            int var = queue.poll();

            for (Integer i : graphList.get(var)) {
                if (!visitedArr[i] && !queue.contains(i)) {
                    queue.add(i);
                }
            }

            visitedArr[var] = true;
            System.out.println(var);
            /*System.out.println(queue);
            System.out.println(Arrays.toString(visitedArr));*/
        }

    }

    public static void main(String args[]) {
        int n = 9, m = 9;
        String inputRepresentation = "(1,2),(2,3),(2,4),(4,5),(5,8),(8,7),(7,6),(6,9),(6,1)";

        int startingPoint = 1; // this affects the adjacency List
        boolean[] visitedArr = new boolean[n + 1];
        Map<Integer, List<Integer>> graphList = parseTheInputToAdjacencyList(inputRepresentation, m, startingPoint);
        System.out.println(graphList);
        System.out.println("=============================");
        Queue<Integer> queue = new LinkedList<Integer>();
        traverseGraphInBFS(visitedArr, graphList, queue);

    }

}