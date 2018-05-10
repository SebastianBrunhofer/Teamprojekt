public class TDTree {
    TDTreeNode root;

    public TDTree(){

    }
    public TDTree(TDTreeNode r){
        root=r;
    }
    public void add(TransportNode d){

        if(root!=null)
        {
            root.add(d);
        }
        else
        {
            root=new TDTreeNode(d);
        }
    }



    public class TDTreeNode{
        private TransportNode data;
        private TDTreeNode left;
        private TDTreeNode right;

        //immer abwechelnd in der höhe des Binärbaums die größe überprüfen einmal das x vergleichen dann das y dann wieder x...
        public TDTreeNode(){

        }
        public TDTreeNode(TransportNode d){
            data=d;
        }
        public TDTreeNode(TransportNode d, TDTreeNode l, TDTreeNode r){
            data=d;
            left =l;
            right=r;
        }
        public void add(TransportNode neu){
            add(new TDTreeNode(neu),true);

        }
        public void add(TDTreeNode neu, boolean chk){
            if(chk){ //damit in jeder ebene des Baumes abwechselnd jeweils nur x oder die y koordinate verglichen wird

                if(neu.data.getxCoord()<data.getxCoord()){

                    if(left!=null){
                        left.add(neu,!chk);
                    }
                    else {
                        left = neu;
                    }
                }
                else{

                    if(right!=null){
                        right.add(neu,!chk);
                    }
                    else {
                        right = neu;
                    }

                }

            }
            else{

                if(neu.data.getyCoord()<data.getyCoord()){

                    if(left!=null){
                        left.add(neu,!chk);
                    }
                    else {
                        left = neu;
                    }
                }
                else{

                    if(right!=null){
                        right.add(neu,!chk);
                    }
                    else {
                        right = neu;
                    }

                }

            }
        }


    }

    public static void main(String[] args) {
        TDTree baum = new TDTree();

    }
}
