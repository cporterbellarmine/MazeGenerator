public class Node {
    private int value;
    private int startHeight;
    private int startWidth;
    private boolean deadEnd;

    Node(int value, int startHeight, int startWidth, boolean deadEnd){
        setValue(value);
        setStartHeight(startHeight);
        setStartWidth(startWidth);
        setDeadEnd(deadEnd);
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

    public void setDeadEnd(boolean deadEnd){
        this.deadEnd = deadEnd;
    }

    public boolean getDeadEnd(){
        return deadEnd;
    }

    public String toString(){
        return this.value + "," + this.startHeight + "," + this.startWidth + "," + this.deadEnd;
    }


}
