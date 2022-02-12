/**
 * This class will represent my graph node.
 * @author JavaTPoint
 * @author Christina Porter
 */

import java.util.ArrayList;
import java.util.List;

public class Graph {
     //This is a list of lists that represets my adjacency list.
    List<List<Node>> adjacencyList = new ArrayList<>();

    public Graph(List<Edge> edges){
        int n = 0;

        /**
         * for each edge in edges, find the highest numbered node and get the source and destination nodes.
         */
        for(Edge edge: edges){
            //n = Integer.max(n, Integer.max(edge.getSource(), edge.getDestination()));
        }

        /**
         * reserves the space for the node in the adjacency list
         */
        for(int i = 0; i <= n; i++){
            adjacencyList.add(i, new ArrayList<>());
        }

        /**
         * For all edges, get the edge source and add a new node with the destination and weight
         */
        for(Edge edge: edges){
            //adjacencyList.get(edge.getSource()).add(new Node(edge.getSource(), edge.getDestination();
        }
    }

    /**
     * Method to print the graph
     * @param graph
     */
    public static void printGraph(Graph graph){
        int source = 0;

        int size = graph.adjacencyList.size();

        while(source < size){
            for(Node edge: graph.adjacencyList.get(source)){

                System.out.printf("%d --> %s\t", source, edge);

                System.out.println();

                source++;
            }
        }

    }
}
