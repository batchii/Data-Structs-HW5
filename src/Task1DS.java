/**
 * @author Bertha bhu9
 * A directed graph data structure that is used for Task 1.
 */
public class Task1DS {

    /**
     * @author bhu9
     * A node class used to created a linked list.
     */
    private final class Node {

        /** The integer stored in the Node. */
        int data;
        /** A pointer to the next node. */
        Node next;

        /**
         * @author bhu9
         * Constructs a Node class, setting data and next to null.
         */
        public Node() {
            this.data = -1;
            this.next = null;
        } // public Node()

        /**
         * @author bhu9
         * Constructs a Node class.
         * @param integer   the number stored in the node
         */
        public Node(int integer) {
            this.data = integer;
            this.next = null;
        } // public Node(T data)

    } // private final class Node

    /**
     * @author bhu9
     * Linked list data structure.
     */
    private class LL {
        /** A sentinal node. */
        private Node head = new Node();
        /** The last node in the linked list. */
        private Node tail = this.head;
    } // private class LL

    /** Number of vertices. */
    private int v;
    /** The adjacency list. */
    private LL[] array;

    /**
     * @author bhu9
     * Constructs a directed graph given an array size.
     * @param num   the size of the array
     */
    public Task1DS(int num) {
        /** The number of vertices. */
        this.v = num;
        /** An array of linked lists of size v. */
        this.array = new LL[this.v];
        for (int i = 0; i < num; i++) {
            this.array[i] = new LL();
        } // for (int i = 0; i < num; i++)
    } // public Task1DS

    /**
     * @author bhu9
     * Adds an edge to the graph.
     * @param source        the source of the edge
     * @param destination   the end point of the edge
     */
    public void addEdge(int source, int destination) {
        this.array[source].tail.next = new Node(destination);
        this.array[source].tail.next = this.array[source].tail.next;
    } // public void addEdge(int source, int destination)

    /**
     * @author bhu9
     * Checks if a linked list is empty.
     * @param source    the source to check
     * @return          true if the source has an edge; false otherwise
     */
    public boolean hasEdge(int source) {
        return (this.array[source].head.next != null);
    } // private boolean hasEdge(int source)

} // public class Task1DS
