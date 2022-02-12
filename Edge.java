public class Edge {

    private Node source; //this will serve to be the source node of the edge
    private Node destination; //this will be the destination node of the edge
    private int weight; //this will be the weight of the edge

    /**
     * Constructor
     * @param source
     * @param destination
     * @param weight
     */
    Edge(Node source, Node destination, int weight){

        setSource(source);
        setDestination(destination);
        setWeight(weight);

    }//end modified constructor

    /**
     * This will set the source node
     * @param source
     */
    public void setSource(Node source){
        this.source = source;
    }//end setSource

    /**
     * this will get the source node
     * @return
     */
    public Node getSource(){
        return source;
    }//end getSource

    /**
     * this will set the destination node of the edge
     * @param destination
     */
    public void setDestination(Node destination){
        this.destination = destination;
    }//end setDestination

    /**
     * this will get the destination node of the edge
     * @return
     */
    public Node getDestination(){
        return destination;
    }//end getDestination

    /**
     * this will set the weight of the edge
     * @param weight
     */
    public void setWeight(int weight){
        this.weight = weight;
    }//end setWeight

    /**
     * This will get the weight of the edge
     * @return
     */
    public int getWeight(){
        return weight;
    }//end getWeight

    public String toString(){
        return this.source + ", " + this.destination + ", " + this.weight;
    }
}//end Edge
