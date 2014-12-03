
import java.util.LinkedList;


public class DirectedGraphPt2<T> {

    /**
     * 
     * @author atab7_000 Structure to represent an adjacency list node
     */
    private class VertexNode {
        /**
         *  The destination of an edge.
         */
        private T dest;
        /**
         *  Pointer to the next adjListNode.
         */
        private VertexNode next;

    }

    /**
     * 
     * @author atab7_000 Structure to represent an adjacency list
     */
    private class Vertex {
        /**
         *  Pointer to head node of the list.
         */
        private VertexNode head;
        /**
         *  Pointer to the next adjList.
         */
        private Vertex down;

        /**
         * The value of this vertex.
         */
        private T vertex;

        /**
         *  Lists the number of prerequisite classes linked to this vertex.
         */
        private int numPreReqs;

        /**
         *  Determines whether this has been used in the current list.
         */
        private boolean flag;

    }

    /**
     *  Number of Vertices.
     */
    private int numVerts;

    /**
     *  the adjacency list.
     */
    private Vertex top;

    /**
     * 
     * Creates an empty DirectedGraph.
     */
    public DirectedGraphPt2() {
        // Number of vertices
        this.numVerts = 0;

        // Create an array of adjacency lists. Size of array will be V
        this.top = null;
    }

    /**
     * Adds an additional vertex to this graph.
     * 
     * @param source
     *            - the vertex value
     */
    public void addVertex(T source) {
        if (!this.hasVertex(source)) {
            Vertex newAdjList = new Vertex();
            newAdjList.head = null;
            newAdjList.flag = true;
            // Replace top of the linked list adjacency list
            newAdjList.down = this.top;
            this.top = newAdjList;
            newAdjList.vertex = source;
            newAdjList.numPreReqs = 0; // Set to just added/nuetral
            this.numVerts++;
        } 
    }

    /**
     * 
     * @param source - Looks for the vertex with this value
     * @return - true if found, false if not found
     */
    public boolean hasVertex(T source) {
        Vertex pCrawler = new Vertex();
        pCrawler = this.top;
        while (pCrawler != null) {
            if (pCrawler.vertex.equals(source)) {
                return true;
            }
            pCrawler = pCrawler.down;
        }
        return false;
    }

    /**
     * Adds an edge to the graph.
     * 
     * @param source
     *            - The source of the edge
     * @param destination
     *            - The ending point of the edge
     */
    public void addEdge(T source, T destination) {
        VertexNode newNode = new VertexNode();
        newNode.dest = destination;
        newNode.next = null;

        // Add edge from source to destination

        // Search for source vertex
        Vertex listCrawler = this.top;
        while (listCrawler != null && !listCrawler.vertex.equals(source)) {
            listCrawler = listCrawler.down;
        }
        if (!this.hasEdge(listCrawler, destination)) {
            // Add to the beginning
            newNode.next = listCrawler.head;
            listCrawler.head = newNode;
            listCrawler.numPreReqs++;
        } else {
            listCrawler.numPreReqs++;
        }

    }

    /**
     *  Checks the given vertex if there is an edge to destination.
     * @param listCrawler - the vertex we are looking at
     * @param destination - the destination vertex
     * @return true if edge is present, otherwise false.
     */
    private boolean hasEdge(Vertex listCrawler, T destination) {
        VertexNode pCrawler = listCrawler.head;
        while (pCrawler != null) {
            if (pCrawler.dest.equals(destination)) {
                return true;
            }
            pCrawler = pCrawler.next;
        }
        return false;
    }

    
    
    /**
     * To print the adjacency list in the representation of a graph.
     */
    public void printGraph() {
        Vertex listCrawler = this.top;
        while (listCrawler != null) {
            VertexNode pCrawler = listCrawler.head;
            System.out.println("\n Adjacency List of vertex "
                    + listCrawler.vertex + "\n head");
            while (pCrawler != null) {
                System.out.println("-> " + pCrawler.dest);
                pCrawler = pCrawler.next;
            }
            System.out.println("\n");
            listCrawler = listCrawler.down;
        }

    }



 // Driver to test functions in DirectedGraph
    public static void main(String[] args) {
        // create the graph given in above figure
        int V = 5;
        DirectedGraphPt2 graph = new DirectedGraphPt2();
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
