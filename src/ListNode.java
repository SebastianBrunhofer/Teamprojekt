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

    public double nodesInRadius(double r, ListNode list){
        ListNode current = list;
        double counter = 0;
        if (current.next.getValue() != null){
            current = current.next;
        }
        double xCurrent = current.getValue().getxCoord();
        double yCurrent = current.getValue().getyCoord();
        double xThis = this.value.getxCoord();
        double yThis = this.value.getyCoord();
        if (Math.abs(xCurrent - xThis) <= r && Math.abs(yCurrent - yThis) <= r){
            double distance = TransportNode.distance(current.value, this.value);
            if (distance <= r){
                counter++;
            }
        }
        return counter;
    }
}
