/**
 * 
 * @author atab7_000
 *
 * @param <T>
 *            - the type of the data stored
 */
public class DirectedAcyclicGraph2<T> {

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
    public DirectedAcyclicGraph2() {
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
     * Remove specified vertex.
     * @param vertexToDelete - the vertex we are deleting.
     * @return The value in the vertex
     */
    public T removeVertex(Vertex vertexToDelete) {
        Vertex pCrawler = new Vertex();
        pCrawler = this.top;
        T returnVal = null;
        boolean topDone = false;
        // Edge Case
        if (pCrawler.vertex.equals(vertexToDelete.vertex)) {
            returnVal = pCrawler.vertex;
            this.top = pCrawler.down;
            topDone = true;
            this.numVerts--;
        }

        if (!topDone) {
            while (pCrawler.down != null) {
                if (pCrawler.down.vertex.equals(vertexToDelete.vertex)) {
                    // Remove the vertex
                    returnVal = pCrawler.down.vertex;
                    if (pCrawler.down.down != null) {
                        pCrawler.down = pCrawler.down.down;
                    } else {
                        pCrawler.down = null;
                    }
                    this.numVerts--;
                    break;

                } else {
                    pCrawler = pCrawler.down;
                }
            }
        }
        // Find the rest of the Vertices that have an edge to this vertex
        this.removeRelatedEdges(returnVal);
        return returnVal;
    }

    /**
     * Deletes all edges related to dest.
     * @param dest - The value of the vertex we are looking for edges to delete.
     */
    private void removeRelatedEdges(T dest) {
        Vertex pCrawler = this.top;
        while (pCrawler != null) {
            if (pCrawler.head != null) {
                if (pCrawler.head.dest.equals(dest)) {
                    pCrawler.head = pCrawler.head.next;
                    pCrawler.flag = false;
                    pCrawler.numPreReqs--;
                } else {
                    VertexNode listCrawler = pCrawler.head;
                    while (listCrawler.next != null) {
                        if (listCrawler.next.dest.equals(dest)) {
                            // Delete
                            VertexNode temp = listCrawler.next;
                            listCrawler.next = listCrawler.next.next;
                            temp.next = null;
                            pCrawler.numPreReqs--;
                            pCrawler.flag = false;
                            break;
                        } else {
                            listCrawler = listCrawler.next;
                        }
                    }
                }
            }
            pCrawler = pCrawler.down;
        }
    }

    /**
     * 
     * @param vertex - the vertex with this value we are looking for 
     * @return - the linked list that this vertex starts, null if it DNE
     */
    public Vertex findVertexNode(T vertex) {
        Vertex pCrawler = new Vertex();
        pCrawler = this.top;
        while (pCrawler != null) {
            if (pCrawler.vertex.equals(vertex)) {
                return pCrawler;
            }
            pCrawler = pCrawler.down;
        }

        return null;
    }

    /**
     * 
     * @return - Gets all of the vertices with 0 edges to other vertices,
     *         compiles into string
     */
    public String getSemester() {
        // TODO
        String courses = "";
        // Iterate thru list
        Vertex pCrawler = this.top;
        while (pCrawler != null) {
            if (pCrawler.numPreReqs == 0) {
                if (pCrawler.flag) { // Check if ever previously edited
                    courses += (String) this.removeVertex(pCrawler) + "\n";
                }
            }
            pCrawler = pCrawler.down;
        }
        // Finished gathering courses, time to reset list
        pCrawler = this.top;
        while (pCrawler != null) {
            pCrawler.flag = true;
            pCrawler = pCrawler.down;
        }

        return courses;
    }

    /**
     * 
     * @return - the number of vertices in the graph.
     */
    public int getNumberVertices() {
        return this.numVerts;
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

}
