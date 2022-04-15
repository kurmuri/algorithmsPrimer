import java.util.*;

public class BfsTraversal {
    public static void main(String[] args) {
        List<List<Integer>> oneBasedGraph = createOneBasedGraph(7);
        doBfs(oneBasedGraph, 7);
    }

    private static void doBfs(List<List<Integer>> adj, int vertices) {
        boolean[] visited = new boolean[vertices+1];
        List<Integer> bfsResult = new ArrayList<>();

        for (int i = 1; i <= vertices; i++) {
            if (!visited[i]) {
                Queue<Integer> queue = new LinkedList<>();
                queue.add(i);
                visited[i] = true;

                while (!queue.isEmpty()) {
                    Integer node = queue.poll();
                    bfsResult.add(node);

                    for (Integer neighbour : adj.get(node)) {
                        if (!visited[neighbour]) {
                            visited[neighbour] = true;
                            queue.add(neighbour);
                        }
                    }
                }
            }
        }
        bfsResult.forEach(System.out::println);
    }

    private static List<List<Integer>> createOneBasedGraph(int vertices) {
        List<List<Integer>> adj = new ArrayList<>();

        for (int i = 0; i <= vertices; i++) {
            adj.add(new ArrayList<>());
        }

        addEdge(adj, 1, 2);
        addEdge(adj, 2, 3);
        addEdge(adj, 2, 7);
        addEdge(adj, 3, 5);
        addEdge(adj, 5, 7);
        addEdge(adj, 4, 6);

        return adj;
    }

    private static void addEdge(List<List<Integer>> adj, int from, int to) {
        adj.get(from).add(to);
    }
}
