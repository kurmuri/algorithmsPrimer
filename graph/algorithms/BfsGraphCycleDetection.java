import java.util.*;

public class BfsGraphCycleDetection {

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

    private static boolean isCyclic(int v, List<List<Integer>> adj) {
        boolean[] vis = new boolean[v];
        for(int node = 0 ; node < v ; node++) {
            if(!vis[node]) {
                if(checkCycle(adj, node, vis)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean checkCycle(List<List<Integer>> adj, int node, boolean[] vis) {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(node, -1));
        vis[node] = true;

        while (!q.isEmpty()) {
            Node poll = q.poll();
            int current = poll.node;
            int parent = poll.parent;

            for (Integer neighbour : adj.get(current)) {
                if (!vis[neighbour]) {
                    q.add(new Node(neighbour, current));
                    vis[neighbour] = true;
                } else if(parent != neighbour) {
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

class Node {
    int node;
    int parent;

    public Node(int node, int parent) {
        this.node = node;
        this.parent = parent;
    }
}
