import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.PriorityQueue;

public class GraphGenerator {
    private int startHeight;
    private int startWidth;
    private int endHeight;
    private int endWidth;
    private int height;
    private int width;
    private Node startNode;
    private Node endNode;

    List<Node> nodeList = new ArrayList<Node>();
    List<Edge> edges = new ArrayList<Edge>();
    List<List<Node>> adjacencyList = new ArrayList<>();

    MazeGenerator mazeGenerator = new MazeGenerator();
    public static void main(String[] args){

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

        





    }

    public GraphGenerator(){

    }

    public int[][] generateMaze(){
        int[][] mazeBlock = mazeGenerator.generateMazeBlock(20,20);
        setHeight(mazeGenerator.getHeight());
        setWidth(mazeGenerator.getWidth());
        int[][] constructedMaze = mazeGenerator.generatePathways(mazeBlock, this.height, this.width);
        setStartHeight(mazeGenerator.getCurrentStartHeight());
        setStartWidth(mazeGenerator.getCurrentStartWidth());
        setEndHeight(mazeGenerator.getCurrentEndHeight());
        setEndWidth(mazeGenerator.getCurrentEndWidth());
        // System.out.println("Start width: " + this.startWidth);
        // System.out.println("End width: " + this.endWidth);
        // System.out.println("Start height: " + this.startHeight);
        // System.out.println("End height: " + this.endHeight);
        return constructedMaze;
    }

    public int[][] findNodes(int[][] mazeBlock){

        System.out.println("Start Find Nodes...");

        for(int i = 0; i < this.height; i++){
            for(int j = 0; j < this.width; j++){
                if(i+1 >= 0 && i+1 <= this.height-1 && i-1 >= 0 && i-1 <= this.height-1 && j+1 >= 0 && j+1 <= this.width-1 && j-1 >= 0 && j-1 <= this.width-1){
                    if((mazeBlock[i][j] == 0 || mazeBlock[i][j] == 2) && 
                    ((mazeBlock[i+1][j]==0 && mazeBlock[i][j-1]==0) || 
                    (mazeBlock[i+1][j]==0 && mazeBlock[i][j+1]==0) || 
                    (mazeBlock[i-1][j]==0 && mazeBlock[i][j-1]==0) || 
                    (mazeBlock[i-1][j]==0 && mazeBlock[i][j+1]==0) || 
                    (mazeBlock[i][j+1]==0 && mazeBlock[i+1][j]==0) || 
                    (mazeBlock[i][j+1]==0 && mazeBlock[i-1][j]==0) || 
                    (mazeBlock[i][j-1]==0 && mazeBlock[i+1][j]==0) || 
                    (mazeBlock[i][j-1]==0 && mazeBlock[i-1][j]==0) ||
                    (mazeBlock[i+1][j]==2 && mazeBlock[i][j-1]==2) || 
                    (mazeBlock[i+1][j]==2 && mazeBlock[i][j+1]==2) || 
                    (mazeBlock[i-1][j]==2 && mazeBlock[i][j-1]==2) || 
                    (mazeBlock[i-1][j]==2 && mazeBlock[i][j+1]==2) || 
                    (mazeBlock[i][j+1]==2 && mazeBlock[i+1][j]==2) || 
                    (mazeBlock[i][j+1]==2 && mazeBlock[i-1][j]==2) || 
                    (mazeBlock[i][j-1]==2 && mazeBlock[i+1][j]==2) || 
                    (mazeBlock[i][j-1]==2 && mazeBlock[i-1][j]==2) ||
                    (mazeBlock[i+1][j]==0 && mazeBlock[i][j-1]==2) || 
                    (mazeBlock[i+1][j]==0 && mazeBlock[i][j+1]==2) || 
                    (mazeBlock[i-1][j]==0 && mazeBlock[i][j-1]==2) || 
                    (mazeBlock[i-1][j]==0 && mazeBlock[i][j+1]==2) || 
                    (mazeBlock[i][j+1]==0 && mazeBlock[i+1][j]==2) || 
                    (mazeBlock[i][j+1]==0 && mazeBlock[i-1][j]==2) || 
                    (mazeBlock[i][j-1]==0 && mazeBlock[i+1][j]==2) || 
                    (mazeBlock[i][j-1]==0 && mazeBlock[i-1][j]==2) ||
                    (mazeBlock[i+1][j]==2 && mazeBlock[i][j-1]==0) || 
                    (mazeBlock[i+1][j]==2 && mazeBlock[i][j+1]==0) || 
                    (mazeBlock[i-1][j]==2 && mazeBlock[i][j-1]==0) || 
                    (mazeBlock[i-1][j]==2 && mazeBlock[i][j+1]==0) || 
                    (mazeBlock[i][j+1]==2 && mazeBlock[i+1][j]==0) || 
                    (mazeBlock[i][j+1]==2 && mazeBlock[i-1][j]==0) || 
                    (mazeBlock[i][j-1]==2 && mazeBlock[i+1][j]==0) || 
                    (mazeBlock[i][j-1]==2 && mazeBlock[i-1][j]==0)
                    )){
                        //System.out.println("True");
                        
                        mazeBlock[i][j] = 2;
                    }else{
                        //System.out.println("Not a match");
                    }
                }else{
                    //System.out.println("false");
                    continue;
                }
            }
        }

        for(int i = 0; i < this.height; i++){
            for(int j = 0; j < this.width; j++){
                if(i+1 >= 0 && i+1 <= this.height-1 && i-1 >= 0 && i-1 <= this.width-1 && j+1 >= 0 && j+1 <= this.height-1 && j-1 >= 0 && j-1 <= this.width-1){
                    if((mazeBlock[i][j] == 0) && 
                    ((mazeBlock[i+1][j]==1 && mazeBlock[i][j+1]==1 && mazeBlock[i][j-1]==1) ||
                    (mazeBlock[i][j+1]==1 && mazeBlock[i+1][j]==1 && mazeBlock[i-1][j]==1) ||
                    (mazeBlock[i][j-1]==1 && mazeBlock[i+1][j]==1 && mazeBlock[i-1][j]==1) ||
                    (mazeBlock[i-1][j]==1 && mazeBlock[i][j+1]==1 && mazeBlock[i][j-1]==1)
                    )){
                        mazeBlock[i][j] = 3;
                    }
                }else{
                    continue;
                }
            }
        }

        mazeBlock[this.startHeight][this.startWidth]=2;
        mazeBlock[this.endHeight][this.endWidth]=2;

        int counter = 100;
        for(int i = 0; i < this.height; i++){
            for(int j = 0; j < this.width; j++){
                if(mazeBlock[i][j] == 2 || mazeBlock[i][j]==3){
                    if(mazeBlock[i][j] == 2){
                        nodeList.add(setNode(counter, i, j, false));
                    }else if(mazeBlock[i][j]==3){
                        nodeList.add(setNode(counter, i, j, true));
                    }
                    mazeBlock[i][j] = counter;
                    counter++;
                }
            }
        }

        startNode = setNode(mazeBlock[getStartHeight()][getStartWidth()], startHeight, startWidth, false);

        for(Node node:nodeList){
            System.out.println(node.toString());
        }
        return mazeBlock;
        
    }

    public List<Edge> createEdges(int[][] mazeBlock, Node startNode){
        System.out.println("Entering Create Edges");

        List<Edge> edgeList = new LinkedList<Edge>();

        if(!startNode.getDeadEnd()){
            int startHeight = startNode.getStartHeight();
            int startWidth = startNode.getStartWidth();

            Queue<String> directionQueue = new LinkedList<String>();

            if(checkNorth(mazeBlock, startHeight, startWidth)){
                directionQueue.add("north");
            }
            if(checkEast(mazeBlock, startHeight, startWidth)){
                directionQueue.add("east");
            }
            if(checkSouth(mazeBlock, startHeight, startWidth)){
                directionQueue.add("south");
            }
            if(checkWest(mazeBlock, startHeight, startWidth)){
                directionQueue.add("west");
            }

            while(directionQueue.size() != 0){
                boolean continueDirection = true;
                String directionToGo = directionQueue.remove();
                int lengthCounter = 0;
                if(directionToGo.equals("north")){
                    System.out.println("Checking North...");
                    continueDirection = true;
                    int currentHeight = startHeight-1;
                    while(continueDirection){
                        if(currentHeight>= 0 && currentHeight <= this.height-1){
                            if(mazeBlock[currentHeight][startWidth] >= 100){
                                boolean deadEndValue = false;
                                for(Node node: nodeList){
                                    //System.out.println(node.toString());
                                    if(node.getValue() == mazeBlock[currentHeight][startWidth]){
                                        deadEndValue = node.getDeadEnd();
                                    }else{
                                        continue;
                                    }
                                }
                                continueDirection = false;
                                endNode = setNode(mazeBlock[currentHeight][startWidth], currentHeight, startWidth, deadEndValue);
                                Edge newEdgeNorth = new Edge(startNode, endNode, lengthCounter);
                                edgeList.add(newEdgeNorth);
                                //System.out.println(edgeList);
                                

                            }else{
                                currentHeight--;
                                lengthCounter++;
                            }
                        }else{
                            break;
                        }
                    }
                }else if(directionToGo.equals("east")){
                    System.out.println("Checking East...");
                    continueDirection = true;
                    int currentWidth = startWidth+1;
                    while(continueDirection){
                        if(currentWidth>= 0 && currentWidth <= this.width-1){
                            //System.out.println("I'm reaching this point");
                            if(mazeBlock[startHeight][currentWidth] >= 100){
                                //System.out.println("I'm reaching this point 2");
                                boolean deadEndValue = false;
                                for(Node node: nodeList){
                                    if(node.getValue() == mazeBlock[startHeight][currentWidth]){
                                        deadEndValue = node.getDeadEnd();
                                    }else{
                                        continue;
                                    }
                                }
                                continueDirection = false;
                                endNode = setNode(mazeBlock[startHeight][currentWidth], startHeight, currentWidth, deadEndValue);
                                Edge newEdgeEast = new Edge(startNode, endNode, lengthCounter);
                                edgeList.add(newEdgeEast);
                                //System.out.println(edgeList);
                            }else{
                                //System.out.println("I'm reaching this point 2");
                                currentWidth++;
                                lengthCounter++;
                                continue;
                            }
                        }else{
                            //System.out.println("I'm reaching this point 3");
                            break;
                        }
                    }
                }else if(directionToGo.equals("south")){
                    System.out.println("Checking South...");
                    continueDirection = true;
                    int currentHeight = startHeight+1;
                    while(continueDirection){
                        if(currentHeight>= 0 && currentHeight <= this.height-1){
                            //System.out.println("I'm reaching this point");
                            if(mazeBlock[currentHeight][startWidth] >= 100){
                                boolean deadEndValue = false;
                                for(Node node: nodeList){
                                    if(node.getValue() == mazeBlock[currentHeight][startWidth]){
                                        //System.out.println(node.toString());
                                        deadEndValue = node.getDeadEnd();
                                    }else{
                                        continue;
                                    }
                                }
                                continueDirection = false;
                                endNode = setNode(mazeBlock[currentHeight][startWidth], currentHeight, startWidth, deadEndValue);
                                Edge newEdgeSouth = new Edge(startNode, endNode, lengthCounter);
                                edgeList.add(newEdgeSouth);
                                //ystem.out.println(edgeList);
                            }else{
                                currentHeight++;
                                lengthCounter++;
                            }
                        }else{
                            break;
                        }
                    }
                }else if(directionToGo.equals("west")){
                    System.out.println("Checking West...");
                    continueDirection = true;
                    int currentWidth = startWidth-1;
                    while(continueDirection){
                        if(currentWidth>= 0 && currentWidth <= this.width-1){
                            if(mazeBlock[startHeight][currentWidth] >= 100){
                                boolean deadEndValue = false;
                                for(Node node: nodeList){
                                    //System.out.println(node.toString());
                                    if(node.getValue() == mazeBlock[startHeight][currentWidth]){
                                        deadEndValue = node.getDeadEnd();
                                    }else{
                                        continue;
                                    }
                                }
                                continueDirection = false;
                                endNode = setNode(mazeBlock[startHeight][currentWidth], startHeight, currentWidth, deadEndValue);
                                Edge newEdgeWest = new Edge(startNode, endNode, lengthCounter);
                                edgeList.add(newEdgeWest);
                                //System.out.println(edgeList);
                            }else{
                                currentWidth--;
                                System.out.println("Current Width" + currentWidth);
                                lengthCounter++;
                            }
                        }else{
                            break;
                        }
                    }
                }
                System.out.println(directionQueue.size());
            }

            System.out.println("Exiting createEdges");
            return edgeList;
        }else{
            return edgeList;
        }
    }

    public boolean checkNorth(int[][] mazeBlock, int startHeight, int startWidth){
        System.out.println("Entering Check North...");
        if(mazeBlock[startHeight][startWidth] >= 100){
            if(startHeight-1 >= 0 && startHeight-1 <= getHeight()-1){
                if(mazeBlock[startHeight-1][startWidth]==0 || mazeBlock[startHeight-1][startWidth] >= 100){
                    System.out.println("Exiting Check North...");
                    return true;
                }else{
                    System.out.println("Exiting Check North...");
                    return false;
                }
            }else{
                System.out.println("Exiting Check North...");
                return false;
            }
        }else{
            System.out.println("Exiting Check North...");
            return false;
        }
    }

    public boolean checkEast(int[][] mazeBlock, int startHeight, int startWidth){
        System.out.println("Entering Check East...");
        if(mazeBlock[startHeight][startWidth] >= 100){
            if(startWidth+1 >= 0 && startWidth+1 <= getWidth()-1){
                if(mazeBlock[startHeight][startWidth+1]==0 || mazeBlock[startHeight][startWidth+1] >= 100){
                    System.out.println("Exiting Check East...");
                    return true;
                }else{
                    System.out.println("Exiting Check East...");
                    return false;
                }
            }else{
                System.out.println("Exiting Check East...");
                return false;
            }
        }else{
            System.out.println("Exiting Check East...");
            return false;
        }
    }

    public boolean checkSouth(int[][] mazeBlock, int startHeight, int startWidth){
        if(mazeBlock[startHeight][startWidth] >= 100){
            System.out.println("Entering Check South...");
            if(startHeight+1 >= 0 && startHeight+1 <= getWidth()-1){
                if(mazeBlock[startHeight+1][startWidth]==0 || mazeBlock[startHeight+1][startWidth] >= 100){
                    System.out.println("Exiting Check South...");
                    return true;
                }else{
                    System.out.println("Exiting Check South...");
                    return false;
                }
            }else{
                System.out.println("Exiting Check South...");
                return false;
            }
        }else{
            System.out.println("Exiting Check South...");
            return false;
        }
    }
    
    public boolean checkWest(int[][] mazeBlock, int startHeight, int startWidth){
        System.out.println("Entering check west...");
        if(mazeBlock[startHeight][startWidth] >= 100){
            if(startWidth-1 >= 0 && startWidth-1 <= getWidth()-1){
                if(mazeBlock[startHeight][startWidth-1]==0 || mazeBlock[startHeight][startWidth-1] >= 100){
                    System.out.println("Exiting Check West...");
                    return true;
                }else{
                    System.out.println("Exiting Check West...");
                    return false;
                }
            }else{
                System.out.println("Exiting Check West...");
                return false;
            }
        }else{
            System.out.println("Exiting Check West...");
            return false;
        }
    }

    public Edge createEdge(Node beginNode, Node destinationNode, int distance){
        Edge newEdge = new Edge(beginNode, destinationNode, distance);
        return newEdge;
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

    public Node setNode(int value, int height, int width, boolean deadEnd){
        Node newNode = new Node(value, height, width, deadEnd);
        return newNode;
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
