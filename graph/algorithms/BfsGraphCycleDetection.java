import java.util.*;

public class BfsGraphCycleDetection {

    public static void main(String[] args) {

        List<List<Integer>> adj = new ArrayList<>();

        int v = 4;
        adj.add(Arrays.asList(1,2));
        adj.add(Arrays.asList(0,2));
        adj.add(Arrays.asList(0,1));
        adj.add(Collections.emptyList());

        // ===================================
        // An undirected graph with a Cycle ||
        // ===================================
        /*int v = 11;
        adj.add(Collections.singletonList(1)); //0
        adj.add(Arrays.asList(0,3)); //1
        adj.add(Collections.singletonList(4)); //2
        adj.add(Collections.singletonList(1)); //3
        adj.add(Arrays.asList(2,9,5)); //4
        adj.add(Arrays.asList(6,4)); //5
        adj.add(Arrays.asList(5,7)); //6
        adj.add(Arrays.asList(6,8,10)); //7
        adj.add(Arrays.asList(7,9)); //8
        adj.add(Arrays.asList(4,8)); //9
        adj.add(Collections.singletonList(7)); //10*/

        // ====================================
        // An undirected graph with no Cycle ||
        // ====================================
        /*int v = 7;
        adj.add(Collections.singletonList(1)); // Nodes adjacent to the 0th Node
        adj.add(Arrays.asList(0,2)); // Nodes adjacent to the 1st Node
        adj.add(Arrays.asList(1,4)); // Nodes adjacent to the 2nd Node
        adj.add(Collections.singletonList(5)); // Nodes adjacent to the 3rd Node
        adj.add(Arrays.asList(2,6)); // // Nodes adjacent to the 4th Node
        adj.add(Collections.singletonList(3)); // Nodes adjacent to the 5th Node
        adj.add(Collections.singletonList(4)); // Nodes adjacent to the 6th Node*/

        System.out.println(isCyclic(v, adj)); // Nodes adjacent to the 7th Node
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
}

class Node {
    int node;
    int parent;

    public Node(int node, int parent) {
        this.node = node;
        this.parent = parent;
    }
}
