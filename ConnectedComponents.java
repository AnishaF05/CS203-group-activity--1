import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConnectedComponents {

    private int V; // Number of vertices
    private List<List<Integer>> adj; // Adjacency list
    private boolean[] visited; // To keep track of visited vertices

    public ConnectedComponents(int V) {
        this.V = V;
        adj = new ArrayList<>(V + 1); // Vertices are 1-indexed in problem description
        for (int i = 0; i <= V; i++) {
            adj.add(new ArrayList<>());
        }
        visited = new boolean[V + 1];
    }

    // Add an edge to the graph
    public void addEdge(int u, int v) {
        adj.get(u).add(v);
        adj.get(v).add(u); // For undirected graph
    }

    // DFS traversal
    private void DFS(int u, List<Integer> component) {
        visited[u] = true;
        component.add(u);
        for (int v : adj.get(u)) {
            if (!visited[v]) {
                DFS(v, component);
            }
        }
    }

    // Find and print connected components
    public void findConnectedComponents() {
        System.out.println("Connected Components:");
        for (int i = 1; i <= V; i++) { // Iterate through all vertices
            if (!visited[i]) {
                List<Integer> component = new ArrayList<>();
                DFS(i, component);
                System.out.println(component);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the input file name: ");
        String fileName = scanner.nextLine();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue; // Skip empty lines

                // Parse the line to extract graph information
                Pattern pattern = Pattern.compile("^(\\d+)\\s+(?:\\((\\d+),(\\d+)\\))*$");
                Matcher matcher = pattern.matcher(line.trim());

                if (matcher.matches()) {
                    int numVertices = Integer.parseInt(matcher.group(1));
                    ConnectedComponents graph = new ConnectedComponents(numVertices);

                    // Extract edges
                    Pattern edgePattern = Pattern.compile("\\((\\d+),(\\d+)\\)");
                    Matcher edgeMatcher = edgePattern.matcher(line.substring(String.valueOf(numVertices).length()));

                    while (edgeMatcher.find()) {
                        int u = Integer.parseInt(edgeMatcher.group(1));
                        int v = Integer.parseInt(edgeMatcher.group(2));
                        graph.addEdge(u, v);
                    }
                    System.out.println("\nGraph with " + numVertices + " vertices:");
                    graph.findConnectedComponents();
                } else {
                    System.err.println("Invalid input format for line: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}