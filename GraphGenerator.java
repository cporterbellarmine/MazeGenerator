import java.util.List;
import java.util.ArrayList;

public class GraphGenerator {
    private int startHeight;
    private int startWidth;
    private int endHeight;
    private int endWidth;
    private int height;
    private int width;
    List<Node> nodeList = new ArrayList<Node>();

    MazeGenerator mazeGenerator = new MazeGenerator();
    public static void main(String[] args){

        GraphGenerator graphGenerator = new GraphGenerator();
        MazeGenerator mazeGenerator = new MazeGenerator();
        int[][] maze = graphGenerator.generateMaze();
        int[][] nodeMaze = graphGenerator.findNodes(maze);
        mazeGenerator.printMaze(nodeMaze);


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
                    if(mazeBlock[i][j] == 0 && 
                    ((mazeBlock[i+1][j]==0 && mazeBlock[i][j-1]==0) || 
                    (mazeBlock[i+1][j]==0 && mazeBlock[i][j+1]==0) || 
                    (mazeBlock[i-1][j]==0 && mazeBlock[i][j-1]==0) || 
                    (mazeBlock[i-1][j]==0 && mazeBlock[i][j+1]==0) || 
                    (mazeBlock[i][j+1]==0 && mazeBlock[i+1][j]==0) || 
                    (mazeBlock[i][j+1]==0 && mazeBlock[i-1][j]==0) || 
                    (mazeBlock[i][j-1]==0 && mazeBlock[i+1][j]==0) || 
                    (mazeBlock[i][j-1]==0 && mazeBlock[i-1][j]==0)
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
                    if(mazeBlock[i][j] == 0 && 
                    (mazeBlock[i+1][j]==1 && mazeBlock[i][j+1]==1 && mazeBlock[i][j-1]==1) ||
                    (mazeBlock[i][j+1]==1 && mazeBlock[i+1][j]==1 && mazeBlock[i-1][j]==1) ||
                    (mazeBlock[i][j-1]==1 && mazeBlock[i+1][j]==1 && mazeBlock[i-1][j]==1) ||
                    (mazeBlock[i-1][j]==1 && mazeBlock[i][j+1]==1 && mazeBlock[i][j-1]==1)){
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
                    mazeBlock[i][j] = counter;
                    nodeList.add(setNode(counter, i, j));
                    counter++;
                }
            }
        }
        System.out.println(nodeList);
        return mazeBlock;
        
    }

    public Node setNode(int value, int startHeight, int startWidth){
        Node newNode = new Node(value, startHeight, startWidth);
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
