import java.util.LinkedList;

public class DirectedAcyclicGraph<T> {

    /**
     * 
     * @author atab7_000 Structure to represent an adjacency list node
     */
    private class adjListNode {
        // The destination of an edge
        private T dest;
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
        // Pointer to the next adjList
        private adjList down;

        private T vertex;

    }

    private class sourceListNode {
        // Pointer to destinations
        private adjListNode head;
    }

    // Number of Vertices
    private int V;

    // the adjacency list
    private adjList top;

    /**
     * 
     * Creates an empty DirectedGraph
     */
    public DirectedAcyclicGraph() {
        // Number of vertices
        this.V = 0;

        // Create an array of adjacency lists. Size of array will be V
        this.top = null;
    }

    /**
     * Adds an additional vertex to this graph
     * @param source - the vertex value
     */
    public void addVertex(T source) {
        if (!hasVertex(source)) {
            adjList newAdjList = new adjList();
            newAdjList.head = null;
            // Replace top of the linked list adjacency list
            newAdjList.down = this.top;
            this.top = newAdjList;
            newAdjList.vertex = source;
            this.V++;
        }

    }

    public boolean hasVertex(T source) {
        adjList pCrawler = new adjList();
        pCrawler = this.top;
        while (pCrawler != null) {
            if (pCrawler.vertex == source) {
                return true;
            }
            pCrawler = pCrawler.down;
        }
        return false;
    }

    /**
     * Adds an edge to the graph
     * 
     * @param source
     *            - The source of the edge
     * @param destination
     *            - The ending point of the edge
     */
    public void addEdge(T source, T destination) {
        adjListNode newNode = new adjListNode();
        newNode.dest = destination;

        // Add edge from source to destination

        // Search for source vertex
        adjList listCrawler = this.top;
        while (listCrawler != null && !listCrawler.vertex.equals(source)) {
            listCrawler = listCrawler.down;
        }

        if (!hasEdge(listCrawler, destination)) {
            // Add to the beginning
            newNode.next = listCrawler.head;
            listCrawler.head = newNode;
        }

    }

    private boolean hasEdge(adjList listCrawler, T destination) {
        adjListNode pCrawler = listCrawler.head;
        while (pCrawler != null) {
            if (pCrawler.dest.equals(destination)) {
                return true;
            }
            pCrawler = pCrawler.next;
        }
        return false;
    }

    /*
     * To print the adjacency list in the representation of a graph
     */
    public void printGraph() {
        adjList listCrawler = this.top;
        while (listCrawler != null) {
            adjListNode pCrawler = listCrawler.head;
            System.out.printf("\n Adjacency List of vertex "
                    + listCrawler.vertex + "\n head");
            while (pCrawler != null) {
                System.out.printf("-> %d", pCrawler.dest);
                pCrawler = pCrawler.next;
            }
            System.out.println("\n");
            listCrawler = listCrawler.down;
        }

    }

 // Driver to test functions in DirectedGraph
    public static void main(String[] args) {
        // create the graph given in above figure

        DirectedAcyclicGraph graph = new DirectedAcyclicGraph();
        graph.addVertex(0);
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        
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
