import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * This Algorithm uses the reverse logic of kahn's algorithm to detect a cycle in the Digraph
 */
public class BfsDigraphCycleDetection {
    public static void main(String[] args) {
        if (isCyclic(9,createDigraphWithCycle())) {
            System.out.println("Graph is cyclic");
        } else {
            System.out.println("Graph is acyclic");
        }

        if (isCyclic(9, createDigraphWithoutCycle())) {
            System.out.println("Graph is cyclic");
        } else {
            System.out.println("Graph is acyclic");
        }
    }

    private static boolean isCyclic(int vertices, List<List<Integer>> adj) {
        int[] topo = new int[vertices];
        int[] indegree = new int[vertices];

        for (int i = 0 ; i < vertices ; i++) {
            for (Integer it : adj.get(i)) {
                indegree[it]++;
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0 ; i < vertices ; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }

        int count = 0;

        while (!queue.isEmpty()) {
            Integer node = queue.poll();
            count++;
            for (Integer i : adj.get(node)) {
                indegree[i]--;
                if (indegree[i] == 0) {
                    queue.add(i);
                }
            }
        }
        if (count == vertices)
            return false;
        return true;
    }

    private static List<List<Integer>> createDigraphWithCycle() {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            adj.add(new ArrayList<>());
        }

        addEdge(adj, 0, 1);
        addEdge(adj, 1, 2);
        addEdge(adj, 2, 3);
        addEdge(adj, 3, 4);
        addEdge(adj, 2, 5);
        addEdge(adj, 5, 4);
        addEdge(adj, 6, 1);
        addEdge(adj, 6, 7);
        addEdge(adj, 7, 8);
        addEdge(adj, 8, 6);

        return adj;
    }

    private static List<List<Integer>> createDigraphWithoutCycle() {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            adj.add(new ArrayList<>());
        }

        addEdge(adj, 0, 1);
        addEdge(adj, 1, 2);
        addEdge(adj, 2, 3);
        addEdge(adj, 3, 4);
        addEdge(adj, 2, 5);
        addEdge(adj, 5, 4);
        addEdge(adj, 6, 1);
        addEdge(adj, 6, 7);
        addEdge(adj, 6, 8);
        addEdge(adj, 7, 8);

        return adj;
    }

    private static void addEdge(List<List<Integer>> adj, Integer from, Integer to) {
        adj.get(from).add(to);
    }
}
