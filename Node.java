public class Node {
    private int value;
    private int weight;

    Node(int value, int weight){
        setValue(value);
        setWeight(weight);
    }

    public void setValue(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }

    public void setWeight(int weight){
        this.weight = weight;
    }

    public int getWeight(){
        return weight;
    }
}
