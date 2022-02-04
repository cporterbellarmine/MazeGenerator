public class Edge {

    private int source;
    private int destination;
    private int weight;

    Edge(int source, int destination, int weight){

        setSource(source);
        setDestination(destination);
        setWeight(weight);

    }

    public void setSource(int source){
        this.source = source;
    }

    public int getSource(){
        return source;
    }

    public void setDestination(int destination){
        this.destination = destination;
    }

    public int getDestination(){
        return destination;
    }

    public void setWeight(int weight){
        this.weight = weight;
    }

    public int getWeight(){
        return weight;
    }


}
