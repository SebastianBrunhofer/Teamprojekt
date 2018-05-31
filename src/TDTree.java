import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TDTree implements Datastructure {
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
    public void construct(String path){
        try(Scanner scn = new Scanner(new File(path),"UTF-8"))
        {
            while(scn.hasNextLine()){
                add(new TransportNode(scn.nextLine()));
            }
        } catch(FileNotFoundException e){
            System.out.println("File not found!");
            System.exit(1);
        }
    }

    public int[] nodesInRadius(double radius, double x, double y){
        return root.numNodes(radius,x,y);
    }

    public int numAPTS(double radius, int anzahl){
        return root.numAPTS(radius,anzahl);
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

        public int[] numNodes(double radius, double x, double y){
            int[] numAPTS = new int[2];
            numNodes(x, y, radius, true, numAPTS);
            return numAPTS;
        }
        public void numNodes(double x, double y, double radius, boolean chk, int[] numAPTS) {
            if (inRadius(x, y, radius)) {
                if (data.getType() == Type.AIRPORT) {
                    numAPTS[1]++;
                }
                if (data.getType() == Type.TRAINSTATION) {
                    numAPTS[0]++;
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
        }

        private boolean inRadius(double x, double y, double radius){
            return  TransportNode.distance(data, new TransportNode(x, y)) <= radius;
        }

        public int numAPTS(double r, int n){
            int erg=0;
            if(left!=null){
                erg+=left.numAPTS(r,n);
            }

            if(right!=null){
                erg+=right.numAPTS(r,n);
            }

            if(data.getType().equals(Type.AIRPORT))
            {

                if(root.numNodes(r, data.getxCoord(), data.getyCoord())[0]>=n){
                    erg++;
                }

            }
            return erg;
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
        int[] a = baum.root.numNodes(10000, 1818.54657, 5813.29982);
        //int[] a = baum.root.nodesInRadiusV2(20, 3000, 2000);
        System.out.println(a[0] + " : " + a[1]);

        a = baum.root.numNodes(10000, 1818.54657, 2000.29982);
        System.out.println(a[0] + " : " + a[1]);

        a = baum.root.numNodes(10000, 1818.54657, -300.29982);
        System.out.println(a[0] + " : " + a[1]);

        a = baum.root.numNodes(10000, -1818.54657, 5813.29982);
        System.out.println(a[0] + " : " + a[1]);

        a = baum.root.numNodes(10000, -1818.54657, -5813.29982);
        System.out.println(a[0] + " : " + a[1]);
        System.out.println("Number of Airports: "+baum.root.numAPTS(15,20));
    }
}