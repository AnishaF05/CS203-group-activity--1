import java.util.*;

public class TopologicalSortDFS {
    static class Graph {
        private int V; // Number of vertices
        private LinkedList<Integer>[] adj; // Adjacency list

        public Graph(int V) {
            this.V = V;
            adj = new LinkedList[V];
            for (int i = 0; i < V; i++) {
                adj[i] = new LinkedList<>();
            }
        }
        public void addEdge(int v, int w) {
            adj[v].add(w); // Directed edge v -> w
        }

        public void topologicalSort() {
            boolean[] visited = new boolean[V]; //keep track of visited nodes
            Stack<Integer> stack = new Stack<>(); //store the order
            for (int i = 0; i < V; i++){ //for every node/vertex
                if (!visited[i]){ //if it hasn't been visited call my helper method
                    topologicalSortHelper(i, visited, stack);
                }
            }
            //print out the order
            System.out.println("Topological order: ");
                while(!stack.isEmpty()){
                    System.out.print(stack.pop() + " ");
                }
        }
        private void topologicalSortHelper(int node, boolean[] visited, Stack<Integer> stack){
            visited[node] = true; //mark the current node as visited
            for (int neighbor : adj[node]){ //access the neighbors from the adj list
                if (!visited[neighbor]){ //if the neighbor hasn't been visited
                    topologicalSortHelper(neighbor, visited, stack); //mark the neighbor as visited, and go through it's neighbors
                }
            }
            stack.push(node); //push that neighbor onto the stack
        }
    }
    public static void main(String[] args) {
        // Example graph (DAG)
        Graph g = new Graph(6);
        g.addEdge(5, 2);
        g.addEdge(5, 0);
        g.addEdge(4, 0);
        g.addEdge(4, 1);
        g.addEdge(2, 3);
        g.addEdge(3, 1);
        System.out.println("Performing Topological Sort...");
        g.topologicalSort();
    }
}


