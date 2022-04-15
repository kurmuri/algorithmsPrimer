import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Topological sorting is a liner ordering of vertices such that if there is an edge from U -> V, U appears
 * before V in that ordering
 */
public class TopologicalSortDFS {

    public static void main(String[] args) {
        doTopologicalSort(6, makeDAG()).forEach(System.out::println);
    }

    private static void findTopologicalSort(int node, int[] vis, List<List<Integer>> adj, Stack<Integer> stack) {
        vis[node] = 1;
        for (Integer i : adj.get(node)) {
            if (vis[i] == 0) {
                findTopologicalSort(i, vis, adj, stack);
            }
        }
        stack.push(node);
    }

    private static List<Integer> doTopologicalSort(int vertices, List<List<Integer>> adj) {
        Stack<Integer> stack = new Stack<>();
        List<Integer> topologicalSortResult = new ArrayList<>();

        int[] vis = new int[vertices];

        for (int i = 0; i < vertices; i++) {
            if (vis[i] == 0) {
                findTopologicalSort(i, vis, adj, stack);
            }
        }

        while (!stack.isEmpty()) {
            topologicalSortResult.add(stack.pop());
        }
        return topologicalSortResult;
    }

    private static List<List<Integer>> makeDAG() {
        List<List<Integer>> dag = new ArrayList<>();

        for (int i = 0; i < 6; i++) {
            dag.add(new ArrayList<>());
        }

        addEdge(dag, 5, 0);
        addEdge(dag, 4, 0);
        addEdge(dag, 4, 1);
        addEdge(dag, 3, 1);
        addEdge(dag, 2, 3);
        addEdge(dag, 5, 2);

        return dag;
    }

    private static void addEdge(List<List<Integer>> adj, Integer from, Integer to) {
        adj.get(from).add(to);
    }
}
