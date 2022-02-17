import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.HashMap;

public class GraphGenerator {
    private int startHeight; //This will be used to store the height of my start node
    private int startWidth; //This will be used to store the width of my start node
    private int endHeight; //This will be used to store the height of my end node
    private int endWidth; //This will be used to store the width of my end node
    private int height; //This wil be used to store the total height of the maze
    private int width; //This will be used to store the total width of the maze
    private Node startNode; //This will be used to store the start node's information
    private Node endNode; //This will be used to store the end node's information

    private List<Node> nodeList = new ArrayList<Node>(); //This will be used to store the total list of Node objects
    private List<Edge> edges = new ArrayList<Edge>(); //This will be used to store the total list of Edge objects
    private HashMap<Node, List<Node>> adjacencyList = new HashMap<>(); //This hashmap will represent the adjacency list. Each node will be matched with nodes it is adjacent to.

    MazeGenerator mazeGenerator = new MazeGenerator(); //This is my maze generator and will be used to generate a maze.

    public static void main(String[] args){

        GraphGenerator graphGenerator = new GraphGenerator();
        MazeGenerator mazeGenerator = new MazeGenerator();

        int[][] maze = graphGenerator.generateMaze(20, 20);
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

    /**
     * Empty Constructor
     */
    public GraphGenerator(){

    }

    /**
     * This method will be used to generate a new maze block with the given height and width.
     */
    public int[][] generateMaze(int height, int width){
        int[][] mazeBlock = mazeGenerator.generateMazeBlock(height,width); //Uses the mazeGenerator to generate a maze block of the given height and width
        setHeight(mazeGenerator.getHeight()); //sets the general height to the given height
        setWidth(mazeGenerator.getWidth()); //sets the general width to the given width
        int[][] constructedMaze = mazeGenerator.generatePathways(mazeBlock, this.height, this.width); //generates the pathways in the maze
        setStartHeight(mazeGenerator.getCurrentStartHeight()); //sets the start height
        setStartWidth(mazeGenerator.getCurrentStartWidth()); //sets the start width
        setEndHeight(mazeGenerator.getCurrentEndHeight()); //sets the end height
        setEndWidth(mazeGenerator.getCurrentEndWidth()); //sets the end width
        return constructedMaze;
    }

    /**
     * This method iterates through the maze and creates node objects out of corner junctions and dead ends.
     * The nodes are then added to a list containing all nodes.
     */
    public int[][] findNodes(int[][] mazeBlock){

        System.out.println("Start Find Nodes...");

        /**
         * For the height and width of the maze, if there exists a corner of pathways in any direction, set the location to 2 to represent a node. 
         */
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
                    }//end else
                }else{
                    //System.out.println("false");
                    continue;
                }//end else
            }//end for j
        }//end for i

        /**
         * For the height and width of the maze, if there exists a dead end, set the location to 3 to represent a node. 
         */
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
                    }//end if
                }else{
                    continue;
                }//end else
            }//end for j
        }//end for i

        //Set the start node and end node to 2 to represent a node.
        mazeBlock[this.startHeight][this.startWidth]=2;
        mazeBlock[this.endHeight][this.endWidth]=2;

        /**
         * Through the entire height and width of the maze, set the nodes to a number greater than or equal to 100. 
         * Create a node object and designate whether it's a dead end depending on if the node is 2 or 3.
         * Add the node to the node list.
         */
        int counter = 100;
        for(int i = 0; i < this.height; i++){
            for(int j = 0; j < this.width; j++){
                if(mazeBlock[i][j] == 2 || mazeBlock[i][j]==3){
                    //Determine whether the node is not a dead end
                    if(mazeBlock[i][j] == 2){
                        nodeList.add(setNode(counter, i, j, false)); //creates a non-dead end node object and adds it to the node list
                    //Determine whether the node is a dead end
                    }else if(mazeBlock[i][j]==3){
                        nodeList.add(setNode(counter, i, j, true)); //creates a dead end node object and adds it to the node list
                    }//end if
                    mazeBlock[i][j] = counter; //sets the node to a number greater than or equal to 100 to designate we have already created the node object and added it to the list
                    counter++; //increases the node counter
                }//end if
            }//end for j
        }//end for i

        startNode = setNode(mazeBlock[getStartHeight()][getStartWidth()], startHeight, startWidth, false); //sets the start node

        /**
         * Prints the node list
         */
        /*for(Node node:nodeList){
            System.out.println(node.toString());
        }*/
        return mazeBlock;
    }

    /**
     * This method takes the start node and creates edges for any adjacent nodes to that start node. This method will also add any
     * adjacent nodes to the adjacency list.
     * @param mazeBlock
     * @param startNode
     * @return
     */
    public List<Edge> createEdges(int[][] mazeBlock, Node startNode){
        System.out.println("Entering Create Edges");

        List<Node> adjacentNodes = new LinkedList<Node>(); //This will be the list of adjacent nodes to the start node
        List<Edge> edgeList = new LinkedList<Edge>(); //This will be the list of edges created from a single node.

        /**
         * If the start node is not a dead end, set the start height and width and check the directions you can travel in.
         * Add the directions you can travel in to a queue.
         * For each direction you can travel in, move one space and check to see if this space is in bounds. If it is, then 
         * check to see if the space is a node. If it is not, keep traveling in that direction until it is. Then, set that node
         * to the end node, add the node as an adjacent node, create an edge, then add the edge to the adjacent list.
         */
        if(!startNode.getDeadEnd()){

            int startHeight = startNode.getStartHeight(); //sets the start height
            int startWidth = startNode.getStartWidth(); //sets the start width

            Queue<String> directionQueue = new LinkedList<String>(); //creates a new queue used to store the directions to be traveled

            /**
             * Checks to see which directions can be traveled in.
             */
            if(checkNorth(mazeBlock, startHeight, startWidth)){
                directionQueue.add("north");
            }//end if
            if(checkEast(mazeBlock, startHeight, startWidth)){
                directionQueue.add("east");
            }//end if
            if(checkSouth(mazeBlock, startHeight, startWidth)){
                directionQueue.add("south");
            }//end if
            if(checkWest(mazeBlock, startHeight, startWidth)){
                directionQueue.add("west");
            }//end if

            /**
             * While there are still directions that can be traveled in, go in that direction until you reach a different node
             */
            while(directionQueue.size() != 0){
                boolean continueDirection = true; //This is used to determine whether or not I should continue traveling in a direction or not.
                String directionToGo = directionQueue.remove(); //Remove the direction from the queue.
                int lengthCounter = 0;
                if(directionToGo.equals("north")){
                    System.out.println("Checking North...");
                    continueDirection = true;
                    int currentHeight = startHeight-1; //Sets the node we want to start traveling in
                    while(continueDirection){
                        //Bounds checking the next node we are traveling in
                        if(currentHeight>= 0 && currentHeight <= this.height-1){
                            //If the current node is a Node object
                            if(mazeBlock[currentHeight][startWidth] >= 100){
                                boolean deadEndValue = false;
                                for(Node node: nodeList){
                                    //System.out.println(node.toString());
                                    //Checks the Dead End value of the node
                                    if(node.getValue() == mazeBlock[currentHeight][startWidth]){
                                        deadEndValue = node.getDeadEnd();
                                    }else{
                                        continue;
                                    }//end if
                                }//end for
                                continueDirection = false; //
                                endNode = setNode(mazeBlock[currentHeight][startWidth], currentHeight, startWidth, deadEndValue); //Creates the end node value
                                adjacentNodes.add(endNode); //Adds the node to the adjacent list
                                Edge newEdgeNorth = new Edge(startNode, endNode, lengthCounter); //creates a new edge
                                edgeList.add(newEdgeNorth); //adds the edge to the edge list
                                //System.out.println(edgeList);
                                

                            }else{
                                currentHeight--;
                                lengthCounter++;
                            }//end if
                        }else{
                            break;
                        }//end if
                    }//end while
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
                                    }//end if
                                }//end for
                                continueDirection = false;
                                endNode = setNode(mazeBlock[startHeight][currentWidth], startHeight, currentWidth, deadEndValue);
                                adjacentNodes.add(endNode);
                                Edge newEdgeEast = new Edge(startNode, endNode, lengthCounter);
                                edgeList.add(newEdgeEast);
                                //System.out.println(edgeList);
                            }else{
                                //System.out.println("I'm reaching this point 2");
                                currentWidth++;
                                lengthCounter++;
                                continue;
                            }//end if
                        }else{
                            //System.out.println("I'm reaching this point 3");
                            break;
                        }//end if
                    }//end while
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
                                    }//end if
                                }//end for
                                continueDirection = false;
                                endNode = setNode(mazeBlock[currentHeight][startWidth], currentHeight, startWidth, deadEndValue);
                                adjacentNodes.add(endNode);
                                Edge newEdgeSouth = new Edge(startNode, endNode, lengthCounter);
                                edgeList.add(newEdgeSouth);
                                //ystem.out.println(edgeList);
                            }else{
                                currentHeight++;
                                lengthCounter++;
                            }//end if
                        }else{
                            break;
                        }//end if
                    }//end while
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
                                    }//end if
                                }//end for
                                continueDirection = false;
                                endNode = setNode(mazeBlock[startHeight][currentWidth], startHeight, currentWidth, deadEndValue);
                                adjacentNodes.add(endNode);
                                Edge newEdgeWest = new Edge(startNode, endNode, lengthCounter);
                                edgeList.add(newEdgeWest);
                                //System.out.println(edgeList);
                            }else{
                                currentWidth--;
                                System.out.println("Current Width" + currentWidth);
                                lengthCounter++;
                            }//end if
                        }else{
                            break;
                        }//end if
                    }//end while
                }//end else
                System.out.println(directionQueue.size());
            }//end while
            adjacencyList.put(startNode, adjacentNodes); //Adds the created adjacency list to the hashmap
            System.out.println("Exiting createEdges");
            return edgeList;
        }else{
            return edgeList;
        }//end else
    }//end createEdges

    /**
     * This method checks to see whether or not you can travel north of the start node.
     * @param mazeBlock
     * @param startHeight
     * @param startWidth
     * @return
     */
    public boolean checkNorth(int[][] mazeBlock, int startHeight, int startWidth){
        System.out.println("Entering Check North...");

        //If the current space is a node that is in bounds
        if(mazeBlock[startHeight][startWidth] >= 100){
            if(startHeight-1 >= 0 && startHeight-1 <= getHeight()-1){
                //Check to make sure the node to the north is either a pathway or a node.
                if(mazeBlock[startHeight-1][startWidth]==0 || mazeBlock[startHeight-1][startWidth] >= 100){
                    System.out.println("Exiting Check North...");
                    return true;
                }else{
                    System.out.println("Exiting Check North...");
                    return false;
                }//end else
            }else{
                System.out.println("Exiting Check North...");
                return false;
            }//end else
        }else{
            System.out.println("Exiting Check North...");
            return false;
        }//end else
    }//end checkNorth

    /**
     * This method checks to see whether or not you can travel east of the start node.
     * @param mazeBlock
     * @param startHeight
     * @param startWidth
     * @return
     */
    public boolean checkEast(int[][] mazeBlock, int startHeight, int startWidth){
        System.out.println("Entering Check East...");
        //If the current space is a node that is in bounds
        if(mazeBlock[startHeight][startWidth] >= 100){
            if(startWidth+1 >= 0 && startWidth+1 <= getWidth()-1){
                //Check to make sure the node to the east is either a pathway or a new node
                if(mazeBlock[startHeight][startWidth+1]==0 || mazeBlock[startHeight][startWidth+1] >= 100){
                    System.out.println("Exiting Check East...");
                    return true;
                }else{
                    System.out.println("Exiting Check East...");
                    return false;
                }//end else
            }else{
                System.out.println("Exiting Check East...");
                return false;
            }//end else
        }else{
            System.out.println("Exiting Check East...");
            return false;
        }//end else
    }//end checkEast

    /**
     * This method checks to see whether or not you can travel south of the start node.
     * @param mazeBlock
     * @param startHeight
     * @param startWidth
     * @return
     */
    public boolean checkSouth(int[][] mazeBlock, int startHeight, int startWidth){
        //If the current space is a node that is in bounds
        if(mazeBlock[startHeight][startWidth] >= 100){
            System.out.println("Entering Check South...");
            if(startHeight+1 >= 0 && startHeight+1 <= getWidth()-1){
                //Check to make sure the node to the south is a pathway or a new node
                if(mazeBlock[startHeight+1][startWidth]==0 || mazeBlock[startHeight+1][startWidth] >= 100){
                    System.out.println("Exiting Check South...");
                    return true;
                }else{
                    System.out.println("Exiting Check South...");
                    return false;
                }//end else
            }else{
                System.out.println("Exiting Check South...");
                return false;
            }//end else
        }else{
            System.out.println("Exiting Check South...");
            return false;
        }//end else
    }//end checkSouth
    
    /**
     * This method checks to see whether or not you can travel west of the start node.
     * @param mazeBlock
     * @param startHeight
     * @param startWidth
     * @return
     */
    public boolean checkWest(int[][] mazeBlock, int startHeight, int startWidth){
        System.out.println("Entering check west...");
        //If the current space is a node that is in bounds
        if(mazeBlock[startHeight][startWidth] >= 100){
            if(startWidth-1 >= 0 && startWidth-1 <= getWidth()-1){
                //Check to make sure the node to the west is a pathway or another node
                if(mazeBlock[startHeight][startWidth-1]==0 || mazeBlock[startHeight][startWidth-1] >= 100){
                    System.out.println("Exiting Check West...");
                    return true;
                }else{
                    System.out.println("Exiting Check West...");
                    return false;
                }//end else
            }else{
                System.out.println("Exiting Check West...");
                return false;
            }//end else
        }else{
            System.out.println("Exiting Check West...");
            return false;
        }//end else
    }//end checkWest

    /**
     * Returns the adjacency list
     * @return
     */
    public HashMap<Node, List<Node>> getAdjacencyList(){
        return adjacencyList;
    }//end getAdjacencyList

    /**
     * Creates an Edge object
     */
    public Edge createEdge(Node beginNode, Node destinationNode, int distance){
        Edge newEdge = new Edge(beginNode, destinationNode, distance);
        return newEdge;
    }//end createEdge

    /**
     * Sets the start node.
     */
    public void setStartNode(Node startNode){
        this.startNode = startNode;
    }//end setStartNode

    /**
     * Returns the start node
     * @return
     */
    public Node getStartNode(){
        return startNode;
    }//end getStartNode

    /**
     * Sets the end node
     */
    public void setEndNode(Node endNode){
        this.endNode = endNode;
    }//end setEndNode

    /**
     * Returns the end node
     * @return
     */
    public Node getEndNode(){
        return endNode;
    }//end getEndNode

    /**
     * Sets a node object
     * @param value
     * @param height
     * @param width
     * @param deadEnd
     * @return
     */
    public Node setNode(int value, int height, int width, boolean deadEnd){
        Node newNode = new Node(value, height, width, deadEnd);
        return newNode;
    }//end setNode

    /**
     * Sets the start height
     * @param startHeight
     */
    public void setStartHeight(int startHeight){
        this.startHeight = startHeight;
    }//end setStartHeight

    /**
     * Returns the start height
     * @return
     */
    public int getStartHeight(){
        return startHeight;
    }//end getStartHeight

    /**
     * Sets the start width
     */
    public void setStartWidth(int startWidth){
        this.startWidth = startWidth;
    }//end setStartWidth

    /**
     * Returns the start width
     * @return
     */
    public int getStartWidth(){
        return startWidth;
    }//end getStartWidth

    /**
     * Sets the end height
     */
    public void setEndHeight(int endHeight){
        this.endHeight = endHeight;
    }//end setEndHeight

    /**
     * Returns the end height
     * @return
     */
    public int getEndHeight(){
        return endHeight;
    }//end getEndHeight

    /**
     * Sets the end width
     * @param endWidth
     */
    public void setEndWidth(int endWidth){
        this.endWidth = endWidth;
    }//end setEndWidth

    /**
     * Returns the end width
     */
    public int getEndWidth(){
        return endWidth;
    }//end getEndWidth

    /**
     * Sets the height
     * @param height
     */
    public void setHeight(int height){
        this.height = height;
    }//end setHeight

    /**
     * Returns the height
     * @return
     */
    public int getHeight(){
        return height;
    }//end getHeight

    /**
     * Sets the width
     * @param width
     */
    public void setWidth(int width){
        this.width = width;
    }//end setWidth

    /**
     * Returns the width
     * @return
     */
    public int getWidth(){
        return width;
    }//end getWidth



}//end GraphGenerator
