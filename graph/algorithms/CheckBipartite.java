import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CheckBipartite {
    public static void main(String[] args) {
        List<List<Integer>> bipartiteGraph = makeBipartiteGraph(10);
        List<List<Integer>> nonBipartiteGraph = makeNonBipartiteGraph(8);

        if (checkBipartite(bipartiteGraph, 10)) {
            System.out.println("Graph is bipartite");
        } else {
            System.out.println("Graph isn't bipartite");
        }

        if (checkBipartite(nonBipartiteGraph, 8)) {
            System.out.println("Graph is bipartite");
        } else {
            System.out.println("Graph isn't bipartite");
        }
    }

    // Check the bipartite behaviour for all the components
    private static boolean checkBipartite(List<List<Integer>> adj, int vertices) {
        int[] color = new int[vertices];
        for (int i = 0; i < vertices; i++) {
            color[i] = -1;
        }

        for (int i = 0; i < vertices; i++) {
            if (color[i] == -1) {
                if (!bfsCheck(adj, i, color)) {
                    return false;
                }
            }
        }
        return true;
    }

    // Check for the bipartite behavior of a component using BFS traversal
    private static boolean bfsCheck(List<List<Integer>> adj, int node, int[] color) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(node); // Add the starting node to the queue
        color[node] = 1; // Color the starting node with color 1

        // Usual BFS flow
        while (!queue.isEmpty()) {
            Integer polled = queue.poll(); // Take out one node from the queue

            // For all the nodes in neighbourhood of the current node
            for (Integer it : adj.get(polled)) {
                if (color[it]  == -1) { // If the current node isn't colored
                    color[it] = 1 - color[polled]; // Color with the opposite color
                    queue.add(it); // Add neighbor node to the queue
                } else if (color[it] == color[polled]) { // If the current node is already colored & is the same color as the polled node
                    return false; // Consecutive nodes are same color, thus graph isn't bipartite
                }
            }
        }
        return true;
    }
    
    // Check for the bipartite behavior of a component using DFS traversal
    private static boolean dfsCheck(List<List<Integer>> adj, int node, int[] color) {
        if (color[node] == -1) 
            color[node] = 1; // If the current node is not colored, color it
        
        for (Integer it : adj.get(node)) {
            if (color[it] == -1) {
                color[it] = 1 - color[node]; // If the current node is not colored, color it with the opposite color
                
                if (!dfsCheck(adj, it, color))
                    return false; // Call recursive to dfsCheck & return accordingly
            } else if (color[it] == color[node]) {
                return false; // If the neighbour node is already colored & is equal to the color of the node color return false 
            }
        }
        return true;
    }

    // 0 based node representation
    private static List<List<Integer>> makeBipartiteGraph(int vertices) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < vertices; i++) {
            adj.add(new ArrayList<>());
        }

        addEdge(adj, 0, 1);
        addEdge(adj, 1, 2);
        addEdge(adj, 2, 3);
        addEdge(adj, 2, 7);
        addEdge(adj, 3, 4);
        addEdge(adj, 7, 6);
        addEdge(adj, 4, 5);
        addEdge(adj, 6, 5);
        addEdge(adj, 5, 8);
        addEdge(adj, 8, 9);

        return adj;
    }

    // 0 based node representation
    private static List<List<Integer>> makeNonBipartiteGraph(int vertices) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < vertices; i++) {
            adj.add(new ArrayList<>());
        }

        addEdge(adj, 0, 1);
        addEdge(adj, 1, 2);
        addEdge(adj, 1, 5);
        addEdge(adj, 2, 3);
        addEdge(adj, 5, 4);
        addEdge(adj, 3, 4);
        addEdge(adj, 3, 6);
        addEdge(adj, 6, 7);

        return adj;
    }

    private static void addEdge(List<List<Integer>> adj, int from, int to) {
        adj.get(from).add(to);
    }
}
