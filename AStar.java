import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.HashMap;

public class AStar {

    private int startHeight;
    private int startWidth;
    private int endHeight;
    private int endWidth;
    private int height;
    private int width;
    private Node startNode;
    private Node endNode;

    public static void main(String args[]){
        AStar traversal = new AStar();
    }

    public AStar(){

        GraphGenerator graphGenerator = new GraphGenerator();
        MazeGenerator mazeGenerator = new MazeGenerator();

        int[][] maze = graphGenerator.generateMaze();
        mazeGenerator.printMaze(maze);

        int[][] nodeMaze = graphGenerator.findNodes(maze);
        mazeGenerator.printMaze(nodeMaze);

        Queue<Node> createEdgesFromList = new LinkedList<Node>();
        List<Edge> completeEdgeList = new ArrayList<Edge>();
        List<Node> visitedNodes = new ArrayList<Node>();

        Node startNode = graphGenerator.getStartNode();

        boolean alreadyIncluded;

        createEdgesFromList.add(startNode);
        while(createEdgesFromList.size() != 0){
            for(Node node: visitedNodes){
                if(createEdgesFromList.size() != 0){
                    if(node.getValue()==createEdgesFromList.peek().getValue()){
                        createEdgesFromList.remove();
                    }
                }else{
                    break;
                }
                
            }
            if(createEdgesFromList.size() != 0){
                visitedNodes.add(createEdgesFromList.peek());
                List<Edge> smallEdgeList = graphGenerator.createEdges(nodeMaze, createEdgesFromList.remove());
                for(Edge edge:smallEdgeList){
                    alreadyIncluded = false;
                    for(Edge visitedEdge: completeEdgeList){
                        if(edge.getSource().getValue() == visitedEdge.getSource().getValue() && edge.getDestination().getValue() == visitedEdge.getDestination().getValue()){
                            alreadyIncluded = true;
                        }
                    }
                    if(!alreadyIncluded){
                        completeEdgeList.add(edge);
                        if(!createEdgesFromList.contains(edge.getDestination())){
                            createEdgesFromList.add(edge.getDestination());
                        }
                    }
                }
            }
        }

        mazeGenerator.printMaze(maze);
        //mazeGenerator.printMaze(nodeMaze);
        System.out.println(completeEdgeList.size());
        System.out.println(graphGenerator.getAdjacencyList());
        System.out.println(graphGenerator.getAdjacencyList().size());

    }

    public void setStartNode(Node startNode){
        this.startNode = startNode;
    }

    public Node getStartNode(){
        return startNode;
    }

    public void setEndNode(Node endNode){
        this.endNode = endNode;
    }

    public Node getEndNode(){
        return endNode;
    }

    public void setStartHeight(int startHeight){
        this.startHeight = startHeight;
    }

    public int getStartHeight(){
        return startHeight;
    }

    public void setStartWidth(int startWidth){
        this.startWidth = startWidth;
    }

    public int getStartWidth(){
        return startWidth;
    }

    public void setEndHeight(int endHeight){
        this.endHeight = endHeight;
    }

    public int getEndHeight(){
        return endHeight;
    }

    public void setEndWidth(int endWidth){
        this.endWidth = endWidth;
    }

    public int getEndWidth(){
        return endWidth;
    }

    public void setHeight(int height){
        this.height = height;
    }

    public int getHeight(){
        return height;
    }

    public void setWidth(int width){
        this.width = width;
    }

    public int getWidth(){
        return width;
    }
}
