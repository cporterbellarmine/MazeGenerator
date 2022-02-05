public class Edge {

    private int source; //this will serve to be the source node of the edge
    private int destination; //this will be the destination node of the edge
    private int weight; //this will be the weight of the edge

    /**
     * Constructor
     * @param source
     * @param destination
     * @param weight
     */
    Edge(int source, int destination, int weight){

        setSource(source);
        setDestination(destination);
        setWeight(weight);

    }//end modified constructor

    /**
     * This will set the source node
     * @param source
     */
    public void setSource(int source){
        this.source = source;
    }//end setSource

    /**
     * this will get the source node
     * @return
     */
    public int getSource(){
        return source;
    }//end getSource

    /**
     * this will set the destination node of the edge
     * @param destination
     */
    public void setDestination(int destination){
        this.destination = destination;
    }//end setDestination

    /**
     * this will get the destination node of the edge
     * @return
     */
    public int getDestination(){
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
}//end Edge
