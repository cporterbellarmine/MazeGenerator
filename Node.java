public class Node {
    private int value;
    private int startHeight;
    private int startWidth;

    Node(int value, int startHeight, int startWidth){
        setValue(value);
        setStartHeight(startHeight);
        setStartWidth(startWidth);
    }

    public void setValue(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
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


}
