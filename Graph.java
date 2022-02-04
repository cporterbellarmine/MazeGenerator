import java.util.ArrayList;
import java.util.List;

public class Graph {
    List<List<Node>> adjacencyList = new ArrayList<>();

    public Graph(List<Edge> edges){
        int n = 0;

        for(Edge edge: edges){
            n = Integer.max(n, Integer.max(edge.getSource(), edge.getDestination()))
        }

        for(int i = 0; i <= n; i++){
            adjacencyList.add(i, new ArrayList<>());
        }

        for(Edge edge: edges){
            adjacencyList.get(edge.getSource()).add(new Node(edge.getDestination(), edge.getWeight()));
        }
    }

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
