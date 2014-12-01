

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
    
    //the adjacency list
    private adjList array[];
    
    
    /**
     * 
     * @param v - the number of vertices in the graph
     */
    public DirectedGraph(int v){
        //Number of vertices
        this.V = v; 
        
        //Create an array of adjacency lists. Size of array will be V
        this.array = new adjList[this.V];
        
        // Initialize each adjacency list as empty by making each head null
        for(int ii = 0; ii < V; ii++){
            this.array[ii] = null;
        }
    }
    
    /**
     * Adds an edge to the graph
     * @param source - The source of the edge
     * @param destination - The ending point of the edge
     */
    public void addEdge(int source, int destination){
        adjListNode newNode = new adjListNode();
        newNode.dest = destination;
        
        //Add edge from source to destination
        newNode.next = this.array[source].head;
        this.array[source].head = newNode;
        
    }
    
    /*
     * To print the adjacency list in the representation of a graph
     */
    public void printGraph(){
        for(int v = 0; v < this.V; v++){
            adjListNode pCrawler = this.array[v].head;
            System.out.printf("\n Adjacency List of vertex %d\n head",v);
            while(pCrawler != null){
                System.out.printf("-> %d", pCrawler.dest);
                pCrawler = pCrawler.next;
            }
            System.out.println("\n");
        }
        
        
    }
    
    
}
