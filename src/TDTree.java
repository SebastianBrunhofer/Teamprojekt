import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

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

        public void print(){
            if(left!=null){
                left.print();
            }
            data.print();
            if(right!=null){
                right.print();
            }
        }

        public int[] numNodes(double r, double x, double y){
            return numNodes(x, y, r, true, new int[2]);
        }
        public int[] numNodes(double x, double y, double radius, boolean chk, int[] numAPTS) {
            if (inRadius(x, y, radius)) {
                if (data.getType() == Type.AIRPORT) {
                    numAPTS[0]++;
                }
                if (data.getType() == Type.TRAINSTATION) {
                    numAPTS[1]++;
                }
            }
            if (chk) {
                switch (relToInterval(x, data.getxCoord(), radius)){
                    case 1:
                        if (left != null) {
                            left.numNodes(x, y, radius, !chk, numAPTS);
                        }
                        break;
                    case 0:
                        if (left != null) {
                            left.numNodes(x, y, radius, !chk, numAPTS);
                        }
                        if (right != null) {
                            right.numNodes(x, y, radius, !chk, numAPTS);
                        }
                        break;
                    case -1:
                        if (right != null) {
                            right.numNodes(x, y, radius, !chk, numAPTS);
                        }
                            break;
                }
            } else {
                switch (relToInterval(y, data.getyCoord(), radius)) {
                    case 1:
                        if (left != null) {
                            left.numNodes(x, y, radius, !chk, numAPTS);
                        }
                        break;
                    case 0:
                        if (left != null) {
                            left.numNodes(x, y, radius, !chk, numAPTS);
                        }
                        if (right != null) {
                            right.numNodes(x, y, radius, !chk, numAPTS);
                        }
                        break;
                    case -1:
                        if (right != null) {
                            right.numNodes(x, y, radius, !chk, numAPTS);
                        }
                        break;
                }
            }
            return numAPTS;
        }

        private boolean inRadius(double x, double y, double radius){
            return  Math.sqrt(Math.pow(data.getxCoord() - x, 2) + Math.pow(data.getyCoord() - y, 2)) <= radius;
        }
        /*
        return = -1 wenn außerhalb des Intervalls (kleiner)
        return = 0 wenn in Intervall
        return = 1 wenn außerhalb des Intervalls (größer)
         */
        private int relToInterval(double center, double value, double radius){
            if (value < center - radius){
                return -1;
            } else if (value > center + radius){
                return 1;
            } else {
                return 0;
            }
        }

    }

    public static void main(String[] args) {
        TDTree baum = new TDTree();
        String path = "data/junctions.csv";

        try(Scanner scn = new Scanner(new File(path),"UTF-8"))
        {
            while(scn.hasNextLine()){
                //System.out.println(scn.nextLine());
                TransportNode neu = new TransportNode(scn.nextLine());
                //neu.print();
                //System.out.println(neu.getName());
                baum.add(neu);
            }
        } catch(FileNotFoundException e){
            System.out.println("File not found!");
            System.exit(1);
        }
        //baum.root.print();
        System.out.println(baum.root.numNodes(100, 5813.29982, 1818.54657)[0]);
    }
}
