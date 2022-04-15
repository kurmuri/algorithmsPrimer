import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * This is also known as Kahn's Algorithm
 */
public class TopologicalSortBFS {
    public static void main(String[] args) {
        for (int i : doTopologicalSort(6, makeDAG())) {
            System.out.println(i);
        }
    }

    private static int[] doTopologicalSort(int vertices, List<List<Integer>> adj) {
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

        int index = 0;

        while (!queue.isEmpty()) {
            Integer polled = queue.poll();
            topo[index++] = polled;

            for (Integer it : adj.get(polled)) {
                indegree[it]--;
                if (indegree[it] == 0) {
                    queue.add(it);
                }
            }
        }
        return topo;
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
