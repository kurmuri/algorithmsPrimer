class Bfs {
  public List<Integer> doBfs(int v, List<List<Integer>> adjancency) {
    List<Integer> bfs = new ArrayList<>();
    // Have a visited array of size v+1 to accomodate nodes numbered from 1 to v
    boolean visited[] = new boolean[v+1];
    
    for(int i = 1; i <= v; i++) {
      // If the node in consideration is not visited then do it's bfs
      if(visited[i] == false) {
        Queue<Integer> q = new LinkedList<>();
        q.add(i); // Add the node to Queue
        visited[i] = true; // Mark the node as visited
        
        // Process till the queue is not empty
        while(!q.isEmpty()) {
          // Take out the first element from the Queue
          Integer node = q.poll();
          // Add it to the bfs list
          bfs.add(node);
          
          // Find the adjacent nodes of the node in consideration
          for(Integer i : adjacency.get(node)) {
            // If the node is not visited, Mark it as visited and add to Queue
            if(visited[i] == false) {
              visited[i] = true;
              q.add(i);
            }
          }
        }
      }
    }
    return bfs;
  }
}

// Time Complexity : O(N+E) : N for visiting all the nodes, E for traversing through adjacent nodes
// Space Complexity : O(N+E) + O(N) + O(N) : Space for adjacency list, Visited Array & the Queue
