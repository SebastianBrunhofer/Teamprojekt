public class List{
    public ListNodeV2 start;


    public void add(TransportNode neu){
        add(new ListNodeV2(neu));
    }

    public void add(ListNodeV2 neu){
        if(start!=null){
            ListNodeV2 curr = start;
            while(curr.getNext() !=null){
                curr = curr.getNext();
            }
            curr.setNext(neu);
        }
        else{
            start=neu;
        }
    }

    /*
     *  Berechnet die Anzahl der Bahnhöfe und Flughäfen in einem Radius von r um den Punkt (xThis, yThis)
     *
     *  TODO ich glaube diese Methode liefert "zu viele Werte" als ich mit radius 30.0 und mit 20 Bahnhöfen in der nähe, ausprobiert habe sind immernoch 838 Bahnhöfe rausgekommen (also mit numAPTS) is nur so ne late night überlegung
     */
    public int[] nodesInRadius(double r, double xThis, double yThis){
        ListNodeV2 current = start;
        int[] train_air = new int[2];
        while (current!= null) {
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

    //TODO temp nur so ein test zum debuggen meiner numAPTS methode
    public int[] nodesInRadiusV2(double r, double xThis, double yThis){
        ListNodeV2 current = start;
        int[] train_air = new int[2];
        while (current!= null) {
            double xCurrent = current.getValue().getxCoord();
            double yCurrent = current.getValue().getyCoord();
            if ((Math.abs(xCurrent - xThis)) <= r && (Math.abs(yCurrent - yThis)) <= r) {
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
        ListNodeV2 current = start;
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
                        if(nodesInRadiusV2(r,current.value.getxCoord(),current.value.getyCoord())[0]>=n){
                            erg++;
                            //System.out.println("Found!");
                        }
                        else{
                            //System.out.println("NOT!");
                        }
                    }
                }


            }

            current=current.next;
        }

        return erg;
    }

    public class ListNodeV2 {

        private TransportNode value;
        private ListNodeV2 next;

        public ListNodeV2(){

        }
        public ListNodeV2(TransportNode tn){
            value = tn;
        }

        public TransportNode getValue() {
            return value;
        }
        public ListNodeV2 getNext() {
            return next;
        }

        public void setValue(TransportNode value) {
            this.value = value;
        }
        public void setNext(ListNodeV2 next) {
            this.next = next;
        }


    }


}

