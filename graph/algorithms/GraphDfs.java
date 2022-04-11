import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class GraphDfs {
    public static void main(String[] args) {
        List<List<Integer>> oneBasedGraph = createOneBasedGraph(7);

        /*List<Integer> result = doDfs(7, oneBasedGraph);
        result.forEach(System.out::println);*/
        doDfsIterative(7, oneBasedGraph);
    }

    private static List<Integer> doDfs(int v, List<List<Integer>> adj) {
        ArrayList<Integer> result = new ArrayList<>();
        boolean[] visited = new boolean[v+1];

        for(int i = 1 ; i <= v ; i++) {
            if(!visited[i]) {
                dfsRecursive(i, visited, adj, result);
            }
        }
        return result;
    }

    private static void dfsRecursive(int node, boolean[] vis, List<List<Integer>> adj, ArrayList<Integer> result) {
        result.add(node);
        vis[node] = true;

        for (Integer it : adj.get(node)) {
            if(!vis[it]) {
                dfsRecursive(it, vis, adj,result);
            }
        }
    }

    private static void doDfsIterative(int v, List<List<Integer>> adj) {
        boolean[] vis = new boolean[v+1];
        List<Integer> result = new ArrayList<>();

        for (int i = 1; i <= v; i++) {
            if (!vis[i]) {
                vis[i] = true;
                Stack<Integer> stack = new Stack<>();
                stack.push(i);

                while (!stack.isEmpty()) {
                    Integer node = stack.pop();
                    result.add(node);

                    for (Integer it : adj.get(node)) {
                        if (!vis[node]) {
                            vis[node] = true;
                            stack.push(it);
                        }
                    }
                }
            }
        }
        result.forEach(System.out::println);
    }

    private static List<List<Integer>> createOneBasedGraph(int vertices) {
        List<List<Integer>> adj = new ArrayList<>();

        for (int i = 0; i <= vertices; i++) {
            adj.add(new ArrayList<>());
        }

        addEdge(adj, 1, 2);
        addEdge(adj, 2, 4);
        addEdge(adj, 2, 7);
        addEdge(adj, 3, 5);
        addEdge(adj, 4, 6);
        addEdge(adj, 6, 7);

        return adj;
    }

    private static void addEdge(List<List<Integer>> adj, int from, int to) {
        adj.get(from).add(to);
    }
}

// Time Complexity : O(N+E)
// Space Complexity : O(N+E) + O(N) + O(N)
