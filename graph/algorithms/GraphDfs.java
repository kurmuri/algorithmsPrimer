import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GraphDfs {
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
        List<Integer> result = doDfs(v, adj);
        result.forEach(System.out::println);
    }

    private static List<Integer> doDfs(int v, List<List<Integer>> adj) {
        ArrayList<Integer> result = new ArrayList<>();
        boolean[] vis = new boolean[v];
        for(int i = 1 ; i <= v ; i++) {
            if(!vis[i-1]) {
                dfs(i, vis, adj, result);
            }
        }
        return result;
    }

    private static void dfs(int node, boolean[] vis, List<List<Integer>> adj, ArrayList<Integer> result) {
        result.add(node);
        vis[node-1] = true;
        for (Integer it : adj.get(node-1)) {
            if(!vis[it-1]) {
                dfs(it, vis, adj,result);
            }
        }
    }
}
// Time Complexity : O(N+E)
// Space Complexity : O(N+E) + O(N) + O(N)
