
public class ListNode {

    private TransportNode value;
    private ListNode next;

    public ListNode() {
    }

    public ListNode(TransportNode tn) {
        value = tn;
    }

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
}

