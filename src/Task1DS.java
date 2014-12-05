/**
 * @author Bertha bhu9
 * A directed graph data structure that is used for Task 1.
 */
public class Task1DS {

    /**
     * @author bhu9
     * A very simple node class used to created a linked list.
     */
    private class Node {

        /** Data stored in the node. */
        int data;
        /** A pointer to the next node. */
        Node next;

        /**
         * @author bhu9
         * Constructs a Node class, setting data to -1 next to null.
         */
        public Node() {
            this.data = -1;
            this.next = null;
        } // public Node()

        /**
         * @author bhu9
         * Constructs a Node class, setting data to the input and next to null.
         * @param value     the int value to be stored
         */
        public Node(int value) {
            this.data = value;
            this.next = null;
        } // public Node()

    } // private final class Node

    /**
     * @author bhu9
     * Linked list data structure.
     */
    private class LL {

        /** A sentinel node. */
        private Node head = new Node();
        /** The last node in the linked list. */
        private Node tail = this.head;

    } // private class LL

    /** Number of vertices. */
    private int v;
    /** The adjacency list. */
    private LL[] arrayLL;
    /** An array that notes the number of directed edges. */
    private int[] arrayInt;

    /**
     * @author bhu9
     * Constructs a directed graph given an array size.
     * @param num   the size of the array
     */
    public Task1DS(int num) {
        /** The number of vertices. */
        this.v = num;
        /** An array of linked lists of size v. */
        this.arrayLL = new LL[this.v];
        this.arrayInt = new int[this.v];
        for (int i = 0; i < num; i++) {
            this.arrayLL[i] = new LL();
            this.arrayInt[i] = 0;
        } // for (int i = 0; i < num; i++)

    } // public Task1DS

    /**
     * @author bhu9
     * Adds an edge to the graph. Cannot add edges to itself
     * or add multiple edges to the same destination.
     * @param source        the source of the edge
     * @param destination   the end point of the edge
     */
    public void addEdge(int source, int destination) {
        if (source == destination) {
            return;
        } // if (source == destination)
        Node pointer = this.arrayLL[source].head;
        while (pointer.next != null) {
            if (pointer.next.data == destination) {
                return;
            } // if (pointer.next.data == destination)
            pointer = pointer.next;
        } // while (pointer.next != null)
        this.arrayLL[source].tail.next = new Node(destination);
        this.arrayLL[source].tail.next = this.arrayLL[source].tail.next;
        this.arrayInt[destination]++;
    } // public void addEdge(int source, int destination)

    /**
     * @author bhu9
     * Checks if a linked list is empty.
     * @param vertex    the source to check
     * @return          true if the source has an edge; false otherwise
     */
    public boolean hasEdge(int vertex) {
        return (this.arrayLL[vertex].head.next != null);
    } // private boolean hasEdge(int source)

    /**
     * @author bhu9
     * Determines how many vertices point to the destination.
     * @param vertex    the source to check
     * @return          the number of directed edges towards the vertex
     */
    public int numEdges(int vertex) {
        return (this.arrayInt[vertex]);
    } // public int numEdges(int destination)

} // public class Task1DS
