/* Anisha Fertil, Cleshaun Moorer
   CS 203
   Fall 25
   Group Activity #1
 */

import java.util.*;

public class ConnectedComponentsFinder {
    static class Graph {
        public int graphNum;
        private int V; // Number of vertices
        private LinkedList<Integer>[] adj; // Adjacency list

        public Graph(int V, int graphNum) {
            this.V = V;
            this.graphNum = graphNum;
            adj = new LinkedList[V];
            for (int i = 0; i < V; i++) {
                adj[i] = new LinkedList<>();
            }
        }
        public void addEdge(int v, int w) {
            //add edge going both ways for undirected graphs
            adj[v].add(w);
            adj[w].add(v);
        }

        // Method that keeps track of the connected components
        public void findConnectedComponentsDFS() {
            boolean[] visited = new boolean[V]; //keep track of visited nodes
            Stack<Integer> stack = new Stack<>(); //store the order
            int componentNum = 1; //keep track of subgraph

            System.out.println("Graph " + graphNum + ":");
            for (int i = 1; i < V; i++){ //for every node/vertex
                if (!visited[i]){ //if it hasn't been visited call my helper method
                    findConnectedComponentsDFSHelper(i, visited, stack);

                    //print out the connected components for each unvisited vertex
                    System.out.print("Component " + componentNum + " -> ");
                    while(!stack.isEmpty()){
                        System.out.print(stack.pop() + " ");
                    }
                    System.out.println();
                    componentNum++;

                }
            }
            System.out.println();
        }

        // Recursive method that performs DFS to find all neighbors (or connected components)
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
    public static void main(String[] args) {
        System.out.println("Performing Program 1... \n");
        // Vertices + 1 because arrays are 0-based and vertices start at 1

        //graph.txt

        Graph g1 = new Graph(9, 1);
        g1.addEdge(1, 2);
        g1.addEdge(1, 7);
        g1.addEdge(6, 7);
        g1.addEdge(7, 5);
        g1.addEdge(4, 7);
        g1.addEdge(3, 8);
        g1.findConnectedComponentsDFS();

        Graph g2 = new Graph (14 , 2);
        g2.addEdge(1,2);
        g2.addEdge(1, 7);
        g2.addEdge(6, 7);
        g2.addEdge(7, 5);
        g2.addEdge(4, 7);
        g2.addEdge(3, 8);
        g2.addEdge(8, 9);
        g2.addEdge(9, 10);
        g2.addEdge(11, 12);
        g2.addEdge(12, 13);
        g2.addEdge(13, 11);
        g2.findConnectedComponentsDFS();


        Graph g6 = new Graph(2, 6);
        g6.addEdge(1, 1);
        g6.findConnectedComponentsDFS();
    }
}

/*OUTPUT
Performing Program 1...

Graph 1:
Component 1 -> 1 7 4 5 6 2 
Component 2 -> 3 8 

Graph 2:
Component 1 -> 1 7 4 5 6 2 
Component 2 -> 3 8 9 10 
Component 3 -> 11 12 13 

Graph 6:
Component 1 -> 1
*/

