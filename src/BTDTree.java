import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BTDTree {
    BTDTreeNode root;
    double maxX=0;
    double maxY =0;

    double minY =Integer.MAX_VALUE;
    double minX = Integer.MAX_VALUE;

    int[] train_air = new int[2];

    int debCount=0;

    public BTDTree(){

    }
    public BTDTree(BTDTreeNode r){
        root=r;
    }
    public void add(TransportNode d){

        if(root!=null)
        {
            root.add(d);
        }
        else
        {
            root=new BTDTreeNode(d);
        }
    }



    public class BTDTreeNode{
        private TransportNode data;
        private BTDTreeNode left;
        private BTDTreeNode right;

        //immer abwechelnd in der höhe des Binärbaums die größe überprüfen einmal das x vergleichen dann das y dann wieder x...
        public BTDTreeNode(){

        }
        public BTDTreeNode(TransportNode d){
            data=d;
        }
        public BTDTreeNode(TransportNode d, BTDTreeNode l, BTDTreeNode r){
            data=d;
            left =l;
            right=r;
        }
        public void add(TransportNode neu){
            add(new BTDTreeNode(neu),true);

        }
        public void add(BTDTreeNode neu, boolean chk){
            if(chk){ //damit in jeder ebene des Baumes abwechselnd jeweils nur x oder die y koordinate verglichen wird

                if(neu.data.getxCoord()<data.getxCoord()){

                    if(left!=null){
                        left.add(neu,!chk);
                    }

                    else {
                        left = neu;
                        if(neu.data.getxCoord()>maxX) maxX=neu.data.getxCoord();
                        if(neu.data.getyCoord()>maxY) maxY=neu.data.getyCoord();

                        if(neu.data.getxCoord()<minX) minX=neu.data.getxCoord();
                        if(neu.data.getyCoord()<minY) minY=neu.data.getyCoord();
                    }
                }
                else{

                    if(right!=null){
                        right.add(neu,!chk);
                    }
                    else {
                        right = neu;
                        if(neu.data.getxCoord()>maxX) maxX=neu.data.getxCoord();
                        if(neu.data.getyCoord()>maxY) maxY=neu.data.getyCoord();

                        if(neu.data.getxCoord()<minX) minX=neu.data.getxCoord();
                        if(neu.data.getyCoord()<minY) minY=neu.data.getyCoord();
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
                        if(neu.data.getxCoord()>maxX) maxX=neu.data.getxCoord();
                        if(neu.data.getyCoord()>maxY) maxY=neu.data.getyCoord();

                        if(neu.data.getxCoord()<minX) minX=neu.data.getxCoord();
                        if(neu.data.getyCoord()<minY) minY=neu.data.getyCoord();
                    }
                }
                else{

                    if(right!=null){
                        right.add(neu,!chk);
                    }
                    else {
                        right = neu;
                        if(neu.data.getxCoord()>maxX) maxX=neu.data.getxCoord();
                        if(neu.data.getyCoord()>maxY) maxY=neu.data.getyCoord();

                        if(neu.data.getxCoord()<minX) minX=neu.data.getxCoord();
                        if(neu.data.getyCoord()<minY) minY=neu.data.getyCoord();
                    }

                }

            }
        }

        //nodesInRadius Methode mit globalem Array zum testen
        public void nodesInRadius(double r, double xThis, double yThis){

            if(left!=null){
                left.nodesInRadius( r,  xThis, yThis);
            }

            if(right!=null){
                right.nodesInRadius( r,  xThis, yThis);
            }
            //debCount++;
            if (Math.abs(data.getxCoord() - xThis) <= r && Math.abs(data.getyCoord() - yThis) <= r) {
                double distance = TransportNode.distance(data, new TransportNode(xThis, yThis));
                if (distance <= r) {
                    if (data.getType() == Type.AIRPORT){
                        train_air[1]++;
                    } else {
                        train_air[0]++;
                    }
                }
            }


        }
        //normale nodesInRadius Methode mit Rekursion und internen lokalen Arrays
        public int[] nodesInRadiusV2(double r, double xThis, double yThis){
            int[] train_air2 = new int[2];
            if(left!=null){
                int[] temp = left.nodesInRadiusV2( r,  xThis, yThis);
                train_air2[0]+=temp[0];
                train_air2[1]+=temp[1];
            }

            if (Math.abs(data.getxCoord() - xThis) <= r && Math.abs(data.getyCoord() - yThis) <= r) {
                double distance = TransportNode.distance(data, new TransportNode(xThis, yThis));debCount++;
                if (distance <= r) {
                    if (data.getType() == Type.AIRPORT){
                        train_air2[1]++;
                    } else {
                        train_air2[0]++;
                    }
                }
            }

            if(right!=null){
                int[] temp = right.nodesInRadiusV2( r,  xThis, yThis);
                train_air2[0]+=temp[0];
                train_air2[1]+=temp[1];
            }

            return train_air2;
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

                if(nodesInRadiusV2(r,data.getxCoord(),data.getyCoord())[0]>=n){
                    erg++;
                }

                //testversuch mit der nodesInRadius Methode die ein Globales Array verwendet
                /*train_air = new int[2];
                nodesInRadius(r,data.getxCoord(),data.getyCoord());
                if(train_air[0]>=n){
                    erg++;
                }*/

            }

            return erg;
        }

        //Hilfsmethode für die Methode zeichnen() die den ganzen Baum traversiert und bei jedem Knoten einen Punkt einzeichnet
        private void zeichR(){
            if(left!=null){
                left.zeichR();
            }
            if(data.getType()==Type.AIRPORT){
                StdDraw.setPenColor(StdDraw.RED);
                StdDraw.setPenRadius(0.005);
            }
            else{
                StdDraw.setPenColor(StdDraw.BLUE);
                StdDraw.setPenRadius(0.002);
            }

            StdDraw.point(data.getxCoord(),data.getyCoord());

            if(right!=null){
                right.zeichR();
            }
        }
    }

    //Methode um alle Flughäfen und Bahnhöfe auf einer Karte anzuzeigen
    public void zeichnen(){
        StdDraw.setCanvasSize(1500,800);
        StdDraw.setXscale(minX,maxX);
        StdDraw.setYscale(minY,maxY);
        StdDraw.enableDoubleBuffering();
        root.zeichR();
        StdDraw.show();

    }

    //Methode um einen Radius auf der Karte einzuzeichnen
    public void drawRadius(double r,double x, double y){
        Color h = StdDraw.getPenColor();
        StdDraw.setPenRadius(0.004);
        StdDraw.setPenColor(StdDraw.GREEN);

        StdDraw.circle(x,y,r);

        StdDraw.setPenRadius(0.002);
        StdDraw.setPenColor(h);
        StdDraw.show();
    }

    public static void main(String[] args) {
        BTDTree baum = new BTDTree();
        String path = "data/junctions.csv";
        try(Scanner scn = new Scanner(new File(path),"UTF-8"))
        {
            while(scn.hasNextLine()){
                baum.add(new TransportNode(scn.nextLine()));
            }
        } catch(FileNotFoundException e){
            System.out.println("File not found!");
            System.exit(1);
        }

        System.out.println("X: "+baum.minX+" -> "+baum.maxX);
        System.out.println("Y: "+baum.minY+" -> "+baum.maxY);
        baum.zeichnen();
        baum.drawRadius(100, 1818.54657, 5813.29982);
        baum.root.nodesInRadius(100000, 1818.54657, 5813.29982);
        //System.out.println(baum.train_air[0] + " " + baum.train_air[1]);

        int[] a = baum.root.nodesInRadiusV2(10000, 1818.54657, 5813.29982);
        //int[] a = baum.root.nodesInRadiusV2(20, 3000, 2000);
        System.out.println(a[0] + " : " + a[1]);

        a = baum.root.nodesInRadiusV2(10000, 1818.54657, 2000.29982);
        System.out.println(a[0] + " : " + a[1]);

        a = baum.root.nodesInRadiusV2(10000, 1818.54657, -300.29982);
        System.out.println(a[0] + " : " + a[1]);

        a = baum.root.nodesInRadiusV2(10000, -1818.54657, 5813.29982);
        System.out.println(a[0] + " : " + a[1]);

        a = baum.root.nodesInRadiusV2(10000, -1818.54657, -5813.29982);
        System.out.println(a[0] + " : " + a[1]);


        long start = System.currentTimeMillis();
        System.out.println("Number of Airports: "+baum.root.numAPTS(15,20));
        System.out.println(baum.debCount);
        baum.debCount=0;
        System.out.println("Number of Airports: "+baum.root.numAPTS(15,1));
        System.out.println(baum.debCount);
        baum.debCount=0;
        System.out.println("Number of Airports: "+baum.root.numAPTS(15,5));
        System.out.println(baum.debCount);
        baum.debCount=0;
        System.out.println("Number of Airports: "+baum.root.numAPTS(15,10));
        System.out.println(baum.debCount);
        baum.debCount=0;
        double time = (System.currentTimeMillis()-start);
        System.out.println(time);
    }
}
