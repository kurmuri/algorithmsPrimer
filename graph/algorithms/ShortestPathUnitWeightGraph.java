import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ShortestPathUnitWeightGraph {
    public static void main(String[] args) {
        shortestPath(createUnitWeightGraph(), 9, 0);
    }

    private static void shortestPath(List<List<Integer>> adj, int vertices, int source) {
        int[] distance = new int[vertices];
        for (int i = 0; i < vertices; i++) {
            distance[i] = Integer.MAX_VALUE;
        }

        Queue<Integer> queue = new LinkedList<>();

        distance[source] = 0;
        queue.add(source);

        while (!queue.isEmpty()) {
            int node = queue.poll();

            for (Integer it : adj.get(node)) {
                if (distance[node] + 1 < distance[it]) {
                    distance[it] = distance[node] + 1;
                    queue.add(it);
                }
            }
        }

        for (int i = 0; i < vertices; i++) {
            System.out.println(distance[i] + ", ");
        }
    }

    private static List<List<Integer>> createUnitWeightGraph() {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            adj.add(new ArrayList<>());
        }

        addEdge(adj, 0, 1);
        addEdge(adj, 0, 3);
        addEdge(adj, 1, 2);
        addEdge(adj, 1, 3);
        addEdge(adj, 2, 6);
        addEdge(adj, 3, 4);
        addEdge(adj, 4, 5);
        addEdge(adj, 5, 6);
        addEdge(adj, 6, 7);
        addEdge(adj, 6, 8);
        addEdge(adj, 7, 8);

        return adj;
    }

    private static void addEdge(List<List<Integer>> adj, int from, int to) {
        adj.get(from).add(to);
    }
}
