import java.util.LinkedList;

public class DirectedAcyclicGraph2<T> {

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

        // Lists the number of prerequisite classes linked to this vertex
        private int numPreReqs;

        // Determines whether this has been used in the current list
        private boolean flag;

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
    public DirectedAcyclicGraph2() {
        // Number of vertices
        this.V = 0;

        // Create an array of adjacency lists. Size of array will be V
        this.top = null;
    }

    /**
     * Adds an additional vertex to this graph
     * 
     * @param source
     *            - the vertex value
     */
    public void addVertex(T source) {
        if (!hasVertex(source)) {
            adjList newAdjList = new adjList();
            newAdjList.head = null;
            newAdjList.flag = true;
            // Replace top of the linked list adjacency list
            newAdjList.down = this.top;
            this.top = newAdjList;
            newAdjList.vertex = source;
            newAdjList.numPreReqs = 0; // Set to just added/nuetral
            this.V++;
        } else {
        }

    }

    public boolean hasVertex(T source) {
        adjList pCrawler = new adjList();
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
        newNode.next = null;

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
            listCrawler.numPreReqs++;
        } else {
            listCrawler.numPreReqs++;
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

    /**
     * Remove specified vertex
     * 
     * @return
     */
    public T removeVertex(adjList vertexToDelete) {
        adjList pCrawler = new adjList();
        pCrawler = this.top;
        T returnVal = null;
        boolean top = false;
        // Edge Case
        if (pCrawler.vertex.equals(vertexToDelete.vertex)) {
            returnVal = pCrawler.vertex;
            this.top = pCrawler.down;
            top = true;
            this.V--;
        }

        if (!top) {
            while (pCrawler.down != null) {
                if (pCrawler.down.vertex.equals(vertexToDelete.vertex)) {
                    // Remove the vertex
                    returnVal = pCrawler.down.vertex;
                    if (pCrawler.down.down != null) {
                        pCrawler.down = pCrawler.down.down;
                    } else {
                        pCrawler.down = null;
                    }
                    this.V--;
                    break;

                } else {
                    pCrawler = pCrawler.down;
                }
            }
        }
        // Find the rest of the Vertices that have an edge to this vertex
        removeRelatedEdges(returnVal);
        return returnVal;
    }

    private void removeRelatedEdges(T dest) {
        adjList pCrawler = this.top;
        while (pCrawler != null) {
            if (pCrawler.head != null) {
                if (pCrawler.head.dest.equals(dest)) {
                    pCrawler.head = pCrawler.head.next;
                    pCrawler.flag = false;
                    pCrawler.numPreReqs--;
                } else {
                    adjListNode listCrawler = pCrawler.head;
                    while (listCrawler.next != null) {
                        if (listCrawler.next.dest.equals(dest)) {
                            // Delete
                            adjListNode temp = listCrawler.next;
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

    public boolean hasNext(T vertex) {
        // TODO
        return false;
    }

    public adjList findVertexNode(T vertex) {
        adjList pCrawler = new adjList();
        pCrawler = this.top;
        while (pCrawler != null) {
            if (pCrawler.vertex.equals(vertex)) {
                return pCrawler;
            }
            pCrawler = pCrawler.down;
        }

        return null;
    }

    public String getSemester() {
        // TODO
        String courses = "";
        // Iterate thru list
        adjList pCrawler = this.top;
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

    public int getNumberVertices() {
        return this.V;
    }

    /*
     * To print the adjacency list in the representation of a graph
     */
    public void printGraph() {
        adjList listCrawler = this.top;
        while (listCrawler != null) {
            adjListNode pCrawler = listCrawler.head;
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

    // // Driver to test functions in DirectedGraph
    // public static void main(String[] args) {
    // // create the graph given in above figure
    //
    // DirectedAcyclicGraph2 graph = new DirectedAcyclicGraph2();
    // graph.addVertex(0);
    // graph.addVertex(1);
    // graph.addVertex(2);
    // graph.addVertex(3);
    // graph.addVertex(4);
    //
    // graph.addEdge(0, 1);
    // graph.addEdge(0, 4);
    // graph.addEdge(1, 2);
    // graph.addEdge(1, 3);
    // graph.addEdge(1, 4);
    // graph.addEdge(2, 3);
    // graph.addEdge(3, 4);
    //
    // // print the adjacency list representation of the above graph
    // graph.printGraph();
    //
    // }
}
