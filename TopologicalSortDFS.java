import java.util.*;

// creating the class that  sorts the graphs in topological order
public class TopologicalSortDFS {

    // each graph should have the number of vertices and an adj list
    static class Graph {
        private int V; // Number of vertices, should come from text file
        private LinkedList<Integer>[] adj; // Adjacency list

        // starting at each vertex, create a linked list for each vertex
        public Graph(int V) {
            this.V = V;
            adj = new LinkedList[V];
            for (int i = 0; i < V; i++) {
                adj[i] = new LinkedList<>();
            }
        }
        // build the graph using the pairs in the text file
        public void addEdge(int v, int w) {
            adj[v].add(w);
        }

        // 
        public void findConnectedComponentsDFS() {
       ///needs outer loop that goes through      
            boolean[] visited = new boolean[V]; //keep track of visited nodes
            Stack<Integer> stack = new Stack<>(); //store the order
            for (int i = 0; i < V; i++){ //for every node/vertex
                if (!visited[i]){ //if it hasn't been visited call my helper method
                    findConnectedComponentsDFSHelper(i, visited, stack);

                    // i think print statement for each needs to go in here to print 
                    // for each connected component instead of just one big list
                }
            }
            //print out all connected components for this vertex (order doesnt matter)
            System.out.println("Connected components: ");
                while(!stack.isEmpty()){
                    System.out.print(stack.pop() + " ");
                }
        }
        private void findConnectedComponentsDFSHelper(int node, boolean[] visited, Stack<Integer> stack){
            visited[node] = true; //mark the current node as visited
            for (int neighbor : adj[node]){ //access the neighbors from the adj list
                if (!visited[neighbor]){ //if the neighbor hasn't been visited
                    findConnectedComponentsDFSHelper(neighbor, visited, stack); //mark the neighbor as visited, and go through it's neighbors
                }
            }
            stack.push(node); //push that neighbor onto the stack
        }
    }

    // where we build the graphs using the text file
    public static void main(String[] args) {
        Graph g = new Graph(6); ?? //the number of vertices will be determined using info from file and also need to loop through file for # of graphs
        
        //this part should be transferred into some loop from the file as well.    
        g.addEdge(5, 2);
        g.addEdge(5, 0);
        g.addEdge(4, 0);
        g.addEdge(4, 1);
        g.addEdge(2, 3);
        g.addEdge(3, 1);
        
        System.out.println("Connected components..."); // change format based on how many graphs were in file
        // ex: for each graph in graph: 
            // system.print "graph #: /n connected components {connected component 1} {connected component 2} {etc}"
        g.topologicalSort();
    }
}


