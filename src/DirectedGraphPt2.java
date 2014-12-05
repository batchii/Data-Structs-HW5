/** A directed graph data structure used for Task 2.
 * @param <T>   generic of type T
 **/
public class DirectedGraphPt2<T> {

    /**
     *
     * @author atab7_000 Structure to represent an adjacency list node
     */
    private class VertexNode {
        /**
         * The destination of an edge.
         */
        private T dest;
        /**
         * Pointer to the next adjListNode.
         */
        private VertexNode next;

    }

    /**
     *
     * @author atab7_000 Structure to represent an adjacency list
     */
    private class Vertex {
        /**
         * Pointer to head node of the list.
         */
        private VertexNode head;
        /**
         * Pointer to the next adjList.
         */
        private Vertex down;

        /**
         * The value of this vertex.
         */
        private T vertex;

    }

    /**
     * Number of Vertices.
     */
    private int numVerts;

    /**
     * the adjacency list.
     */
    private Vertex top;

    /**
     * The bottom pointer of the adjacency list, used for inserts.
     */
    private Vertex bot;

    /**
     *
     * Creates an empty DirectedGraph.
     */
    public DirectedGraphPt2() {
        // Number of vertices
        this.numVerts = 0;

        // Create an array of adjacency lists. Size of array will be V
        this.top = null;
        this.bot = null;
    }

    /**
     * Adds an additional vertex to this graph.
     *
     * @param source
     *            - the vertex value
     */
    public void addVertex(T source) {
        if (this.numVerts != 0) {
            if (!this.hasVertex(source)) {
                Vertex newAdjList = new Vertex();
                newAdjList.head = null;
                // Replace top of the linked list adjacency list
                this.bot.down = newAdjList;
                this.bot = newAdjList;
                newAdjList.vertex = source;
                this.numVerts++;
            }
        } else {
            // No verts currently in the list, time to start adding
            Vertex newAdjList = new Vertex();
            newAdjList.head = null;
            this.top = newAdjList;
            this.bot = newAdjList;
            newAdjList.vertex = source;
            this.numVerts++;
        }
    }

    /**
     *
     * @param source
     *            - Looks for the vertex with this value
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
        // Replace current edge
        listCrawler.head = newNode;

    }

    /**
     * Iterates through the data structure to produce the ordered elements.
     * @return     a character array of the ordered items.*/
    public char[] returnOrder() {
        String[] builder;
        builder = this.getPairs();
        String ordered = "";
        // Repeat until entire string is built
        while (builder.length > 0) {
            // Build the string
            for (int ii = 0; ii < builder.length; ii++) {
                if (ordered.length() > 0) {
                    if (builder[ii].length() != 1) {

                        if (ordered.contains(String.valueOf(builder[ii]
                                .charAt(0)))) {
                            // Does it contain the vertex already?
                            ordered += builder[ii].charAt(1); // Add to end b/c
                            // only
                            // way
                            // Delete what was added from array
                            String[] temp = new String[builder.length - 1];
                            System.arraycopy(builder, 0, temp, 0, ii);
                            if (builder.length != ii) {
                                System.arraycopy(builder, ii + 1, temp, ii,
                                        builder.length - ii - 1);
                            }
                            builder = temp;

                        } else if (ordered.contains(String.valueOf(builder[ii]
                                .charAt(1)))) {
                            // Does it contain the edge already?
                            ordered = builder[ii].charAt(0)
                                    + ordered.substring(1);
                            // Delete what was added from array
                            String[] temp = new String[builder.length - 1];
                            System.arraycopy(builder, 0, temp, 0, ii);
                            if (builder.length != ii) {
                                System.arraycopy(builder, ii + 1, temp, ii,
                                        builder.length - ii - 1);
                            }
                            builder = temp;
                        }
                    } else {
                        if (ordered.contains(String.valueOf(builder[ii]
                                .charAt(0)))) {
                            // Remove from array
                            String[] temp = new String[builder.length - 1];
                            System.arraycopy(builder, 0, temp, 0, ii);
                            if (builder.length != ii) {
                                System.arraycopy(builder, ii + 1, temp, ii,
                                        builder.length - ii - 1);
                            }
                            builder = temp;
                        }

                        // Do nothing otherwise
                    }
                } else {
                    // Ordered has nothing in it yet, gotta put something in it
                    ordered += builder[ii];
                    String[] temp = new String[builder.length - 1];
                    System.arraycopy(builder, 1, temp, 0, builder.length - 1);
                    builder = temp;
                }
            }
        }

        return ordered.toCharArray();
    }

    /**
     *
     * @return Vertex,Edge pairs, or if there is no pair, just the vertex.
     */
    private String[] getPairs() {
        String[] builder = new String[this.numVerts];
        Vertex pCrawler = this.top;
        int ii = 0;
        while (pCrawler != null) {
            builder[ii] = (String) pCrawler.vertex;
            if (pCrawler.head != null) {
                builder[ii] += pCrawler.head.dest;
            }
            pCrawler = pCrawler.down;
            ii++;
        }
        return builder;
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
