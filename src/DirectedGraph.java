
import java.util.LinkedList;

public class DirectedGraph {

    /**
     * 
     * @author atab7_000 Structure to represent an adjacency list node
     */
    private class adjListNode {
        // The destination of an edge
        private int dest;
        // Pointer to the next adjListNode
        private adjListNode next;

    }

    /**
     * 
     * @author atab7_000 Structure to represent an adjacency list
     */
    private class adjList {
        // Pointer to head node of the list
        private adjListNode head;

    }

    // Number of Vertices
    private int V;

    // the adjacency list
    private adjList array[];

    /**
     * 
     * @param v
     *            - the number of vertices in the graph
     */
    public DirectedGraph(int v) {
        // Number of vertices
        this.V = v;

        // Create an array of adjacency lists. Size of array will be V
        this.array = new adjList[this.V];

        // Initialize each adjacency list as empty by making each head null
        for (int ii = 0; ii < V; ii++) {
            this.array[ii] = new adjList();
            this.array[ii].head = null;
        }
    }

    /**
     * Adds an edge to the graph
     * 
     * @param source
     *            - The source of the edge
     * @param destination
     *            - The ending point of the edge
     */
    public void addEdge(int source, int destination) {
        adjListNode newNode = new adjListNode();
        newNode.dest = destination;

        // Add edge from source to destination
        if (!this.hasEdge(source, destination)) {
            newNode.next = this.array[source].head;
            this.array[source].head = newNode;
        }

    }

    /**
     * To print the adjacency list in the representation of a graph.
     */
    public void printGraph() {
        for (int v = 0; v < this.V; v++) {
            adjListNode pCrawler = this.array[v].head;
            System.out.printf("\n Adjacency List of vertex %d\n head", v);
            while (pCrawler != null) {
                System.out.printf("-> %d", pCrawler.dest);
                pCrawler = pCrawler.next;
            }
            System.out.println("\n");
        }

    }

    private boolean hasEdge(int source, int destination) {
        adjListNode pCrawler = this.array[source].head;
        while (pCrawler != null) {
            if (pCrawler.dest == destination) {
                return true;
            }
            pCrawler = pCrawler.next;
        }
        return false;
    }

 // Driver to test functions in DirectedGraph
    public static void main(String[] args) {
        // create the graph given in above figure
        int V = 5;
        DirectedGraph graph = new DirectedGraph(V);
        graph.addEdge(0, 1);
        graph.addEdge(0, 4);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);

        // print the adjacency list representation of the above graph
        graph.printGraph();

    }
}
