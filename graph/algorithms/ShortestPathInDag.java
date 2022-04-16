import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ShortestPathInDag {

    public static void main(String[] args) {
        findShortestPath(0, createWeightedDag(), 6);
    }

    private static void topoSortUtil(int node, Boolean[] visited, Stack<Integer> stack, List<List<Pair<Integer, Integer>>> dag) {
        visited[node] =true;

        for (Pair<Integer, Integer> p : dag.get(node)) {
            if (!visited[p.getKey()]) {
                topoSortUtil(p.getKey(), visited, stack, dag);
            }
        }
        stack.add(node);
    }

    private static void findShortestPath(int source, List<List<Pair<Integer, Integer>>> dag, int vertices) {
         Stack<Integer> stack = new Stack<>();
         int[] distance = new int[vertices];

         Boolean[] visited = new Boolean[vertices];
        for (int i = 0; i < vertices; i++) {
            visited[i] = false;
        }

        for (int i = 0; i < vertices; i++) {
            if (!visited[i]) {
                topoSortUtil(i, visited, stack, dag);
            }
        }

        for (int i = 0; i < vertices; i++) {
            distance[i] = Integer.MAX_VALUE;
        }
        distance[source] = 0;

        while (!stack.isEmpty()) {
            int node = stack.pop();
            if (distance[node] != Integer.MAX_VALUE) {
                for (Pair<Integer, Integer> p : dag.get(node)) {
                    if (distance[node] + p.getValue() < distance[p.getKey()]) {
                        distance[p.getKey()] = distance[node] + p.getValue();
                    }
                }
            }
        }

        for (int i = 0; i < vertices; i++) {
            if (distance[i] == Integer.MAX_VALUE)
                System.out.println("Unreachable! ");
            else
                System.out.println(distance[i] + " ");
        }
    }

    private static List<List<Pair<Integer, Integer>>> createWeightedDag() {
        List<List<Pair<Integer, Integer>>> dag = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            dag.add(new ArrayList<>());
        }

        addEdge(dag, 0, new Pair<>(1, 2));
        addEdge(dag, 0, new Pair<>(4, 1));
        addEdge(dag, 1, new Pair<>(2, 3));
        addEdge(dag, 2, new Pair<>(3, 6));
        addEdge(dag, 4, new Pair<>(2, 2));
        addEdge(dag, 4, new Pair<>(5, 4));
        addEdge(dag, 5, new Pair<>(3, 1));

        return dag;
    }
    
    private static void addEdge(List<List<Pair<Integer, Integer>>> adj, Integer from, Pair<Integer, Integer> to) {
        adj.get(from).add(to);
    }
}
