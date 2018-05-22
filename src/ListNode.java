
public class ListNode {

    private TransportNode value;
    private ListNode next;

    public ListNode(){}

    public TransportNode getValue() {
        return value;
    }
    public ListNode getNext() {
        return next;
    }

    public void setValue(TransportNode value) {
        this.value = value;
    }
    public void setNext(ListNode next) {
        this.next = next;
    }

    /*
     *  Berechnet die Anzahl der Bahnhöfe und Flughäfen in einem Radius von r um den Punkt (xThis, yThis)
     *
     *
     */
    public int[] nodesInRadius(double r, double xThis, double yThis){
        ListNode current = this;
        int[] train_air = new int[2];
        while (current.getValue() != null) {
            double xCurrent = current.getValue().getxCoord();
            double yCurrent = current.getValue().getyCoord();
            if (Math.abs(xCurrent - xThis) <= r && Math.abs(yCurrent - yThis) <= r) {
                double distance = TransportNode.distance(current.value, new TransportNode(xThis, yThis));
                if (distance <= r) {
                    if (current.getValue().getType() == Type.AIRPORT){
                        train_air[1]++;
                    } else {
                        train_air[0]++;
                    }
                }
            }
            current = current.next;
        }
        return train_air;
    }


    //Diese Methode soll nodesInRadius verwenden um die Anzahl aller Airports zu berechnen die n-viele Bahnhöfe, in einem Umkreis von r um sich haben
    public int numAPTS(double r, int n){
        int erg=0;
        ListNode current = this;
        while(current!=null){

            if(current.getValue()==null){
                System.out.println("Value is NULL!");
            }
            else{
                if(current.value.getType()==null){
                    System.out.println("Type is NULL!");
                }
                else{
                    if(current.getValue().getType().equals(Type.AIRPORT)){
                        if(nodesInRadius(r,current.value.getxCoord(),current.value.getyCoord())[0]>=n){
                            erg++;

                        }
                    }
                }
            }
            current=current.next;
        }
        return erg;
    }
}
