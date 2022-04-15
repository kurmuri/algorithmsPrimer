import java.util.ArrayList;
import java.util.List;

public class DfsGraphCycleDetection {

    public static void main(String[] args) {
        List<List<Integer>> cyclicGraph = createCyclicGraph();
        List<List<Integer>> acyclicGraph = createAcyclicGraph();

        if (isCyclic(11, cyclicGraph)) {
            System.out.println("Graph is cyclic");
        } else {
            System.out.println("Graph is acyclic");
        }

        if (isCyclic(3, acyclicGraph)) {
            System.out.println("Graph is cyclic");
        } else {
            System.out.println("Graph is acyclic");
        }
    }

    private static boolean checkForCycle(int node, int parent, boolean[] vis, List<List<Integer>> adj) {
        vis[node] = true;

        for (Integer i : adj.get(node)) {
            if (!vis[i]) {
                if (checkForCycle(i, node, vis, adj))
                    return true;
            } else if (i != parent) {
                return true;
            }
        }
        return false;
    }

    private static boolean isCyclic(int vertices, List<List<Integer>> adj) {
        boolean[] vis = new boolean[vertices];

        for (int i = 0 ; i < vertices ; i++) {
            if (!vis[i]) {
                if (checkForCycle(i, -1, vis, adj)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static List<List<Integer>> createCyclicGraph() {
        List<List<Integer>> adj = new ArrayList<>();

        for (int i = 0; i < 11; i++) {
            adj.add(new ArrayList<>());
        }

        addEdge(adj, 0, 1);
        addEdge(adj, 1, 3);
        addEdge(adj, 2, 4);
        addEdge(adj, 4, 5);
        addEdge(adj, 4, 9);
        addEdge(adj, 5, 6);
        addEdge(adj, 6, 7);
        addEdge(adj, 9, 8);
        addEdge(adj, 8, 7);
        addEdge(adj, 7, 10);

        return adj;
    }

    private static List<List<Integer>> createAcyclicGraph() {
        List<List<Integer>> adj = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            adj.add(new ArrayList<>());
        }

        addEdge(adj, 0, 1);
        addEdge(adj, 1, 2);

        return adj;
    }

    private static void addEdge(List<List<Integer>> adj, int from, int to) {
        adj.get(from).add(to);
    }
}

class Nodee {
    int node;
    int parent;

    public Nodee(int node, int parent) {
        this.node = node;
        this.parent = parent;
    }
}
