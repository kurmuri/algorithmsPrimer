import java.util.*;

public class Bfs {
    public static void main(String[] args) {
        int v = 7;
        List<List<Integer>> adj = new ArrayList<>();
        adj.add(Arrays.asList(1,2));
        adj.add(Arrays.asList(2,1,3,7));
        adj.add(Arrays.asList(3,2,5));
        adj.add(Arrays.asList(4,6));
        adj.add(Arrays.asList(5,3,7));
        adj.add(Arrays.asList(6,4));
        adj.add(Arrays.asList(7,2,5));
        List<Integer> bfs = doBfs(v, adj);
        bfs.forEach(System.out::println);
    }

    private static List<Integer> doBfs(int v, List<List<Integer>> adj) {
        boolean[] vis = new boolean[v];
        List<Integer> res = new ArrayList<>();

        for(int i = 1; i <= v ; i++) {
            if(!vis[i-1]) {
                // Do BFS
                Queue<Integer> q = new LinkedList<>();
                q.add(i);
                vis[i-1] = true;

                while (!q.isEmpty()) {
                    Integer node = q.poll();
                    res.add(node);

                    for(Integer it : adj.get(node-1)) {
                        if (!vis[it-1]) {
                            vis[it-1] = true;
                            q.add(it);
                        }
                    }
                }
            }
        }
        return res;
    }
}

// Time Complexity : O(N+E) : N for visiting all the nodes, E for traversing through adjacent nodes
// Space Complexity : O(N+E) + O(N) + O(N) : Space for adjacency list, Visited Array & the Queue
