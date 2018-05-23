import java.util.Scanner;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        String path = "data/junctions.csv";
        long start;
        double time;
        int[] a;
        Datastructure datSt;
        //List Test
        System.out.println("List Test...");
        datSt = new List(path);
        //((List)datSt).zeichnen();
        //((List)datSt).drawRadius(100, 1818.54657, 5813.29982);
        //Testcases
        a = datSt.nodesInRadius(10000, 1818.54657, 5813.29982);
        System.out.println("Trainstations: "+a[0] + "; Airports: " + a[1]);

        a = datSt.nodesInRadius(10000, 1818.54657, 2000.29982);
        System.out.println("Trainstations: "+a[0] + "; Airports: " + a[1]);

        a = datSt.nodesInRadius(10000, 1818.54657, -300.29982);
        System.out.println("Trainstations: "+a[0] + "; Airports: " + a[1]);

        a = datSt.nodesInRadius(10000, -1818.54657, 5813.29982);
        System.out.println("Trainstations: "+a[0] + "; Airports: " + a[1]);

        a = datSt.nodesInRadius(10000, -1818.54657, -5813.29982);
        System.out.println("Trainstations: "+a[0] + "; Airports: " + a[1]);

        start = System.currentTimeMillis();
        System.out.println("Number of Airports: "+datSt.numAPTS(15,20));
        time = (System.currentTimeMillis()-start);
        System.out.println(time+"ms");

        start = System.currentTimeMillis();
        System.out.println("Number of Airports: "+datSt.numAPTS(15,1));
        time = (System.currentTimeMillis()-start);
        System.out.println(time+"ms");

        start = System.currentTimeMillis();
        System.out.println("Number of Airports: "+datSt.numAPTS(15,5));
        time = (System.currentTimeMillis()-start);
        System.out.println(time+"ms");

        start = System.currentTimeMillis();
        System.out.println("Number of Airports: "+datSt.numAPTS(15,10));
        time = (System.currentTimeMillis()-start);
        System.out.println(time+"ms");

        System.out.println("List Test finished.");

        //BTDTree Test
        System.out.println("BTDTree Test...");
        datSt = new BTDTree();
        datSt.construct(path);
        ((BTDTree)datSt).zeichnen();
        ((BTDTree)datSt).drawRadius(100, 1818.54657, 5813.29982);
        //Testcases
        a = datSt.nodesInRadius(10000, 1818.54657, 5813.29982);
        System.out.println("Trainstations: "+a[0] + "; Airports: " + a[1]);

        a = datSt.nodesInRadius(10000, 1818.54657, 2000.29982);
        System.out.println("Trainstations: "+a[0] + "; Airports: " + a[1]);

        a = datSt.nodesInRadius(10000, 1818.54657, -300.29982);
        System.out.println("Trainstations: "+a[0] + "; Airports: " + a[1]);

        a = datSt.nodesInRadius(10000, -1818.54657, 5813.29982);
        System.out.println("Trainstations: "+a[0] + "; Airports: " + a[1]);

        a = datSt.nodesInRadius(10000, -1818.54657, -5813.29982);
        System.out.println("Trainstations: "+a[0] + "; Airports: " + a[1]);

        start = System.currentTimeMillis();
        System.out.println("Number of Airports: "+datSt.numAPTS(15,20));
        time = (System.currentTimeMillis()-start);
        System.out.println(time+"ms");

        start = System.currentTimeMillis();
        System.out.println("Number of Airports: "+datSt.numAPTS(15,1));
        time = (System.currentTimeMillis()-start);
        System.out.println(time+"ms");

        start = System.currentTimeMillis();
        System.out.println("Number of Airports: "+datSt.numAPTS(15,5));
        time = (System.currentTimeMillis()-start);
        System.out.println(time+"ms");

        start = System.currentTimeMillis();
        System.out.println("Number of Airports: "+datSt.numAPTS(15,10));
        time = (System.currentTimeMillis()-start);
        System.out.println(time+"ms");

        System.out.println("BTDTree Test finished.");

        System.out.println();

        //TDTree Test
        System.out.println("TDTree Test...");
        datSt = new TDTree();
        datSt.construct(path);
        //Testcases
        a = datSt.nodesInRadius(10000, 1818.54657, 5813.29982);
        System.out.println("Trainstations: "+a[0] + "; Airports: " + a[1]);

        a = datSt.nodesInRadius(10000, 1818.54657, 2000.29982);
        System.out.println("Trainstations: "+a[0] + "; Airports: " + a[1]);

        a = datSt.nodesInRadius(10000, 1818.54657, -300.29982);
        System.out.println("Trainstations: "+a[0] + "; Airports: " + a[1]);

        a = datSt.nodesInRadius(10000, -1818.54657, 5813.29982);
        System.out.println("Trainstations: "+a[0] + "; Airports: " + a[1]);

        a = datSt.nodesInRadius(10000, -1818.54657, -5813.29982);
        System.out.println("Trainstations: "+a[0] + "; Airports: " + a[1]);

        start = System.currentTimeMillis();
        System.out.println("Number of Airports: "+datSt.numAPTS(15,20));
        time = (System.currentTimeMillis()-start);
        System.out.println(time+"ms");

        start = System.currentTimeMillis();
        System.out.println("Number of Airports: "+datSt.numAPTS(15,1));
        time = (System.currentTimeMillis()-start);
        System.out.println(time+"ms");

        start = System.currentTimeMillis();
        System.out.println("Number of Airports: "+datSt.numAPTS(15,5));
        time = (System.currentTimeMillis()-start);
        System.out.println(time+"ms");

        start = System.currentTimeMillis();
        System.out.println("Number of Airports: "+datSt.numAPTS(15,10));
        time = (System.currentTimeMillis()-start);
        System.out.println(time+"ms");

        System.out.println("TDTree Test finished.");

        //DEBUG
/*
        BTDTree bbaum = new BTDTree();
        bbaum.construct(path);
        bbaum.zeichnen();
        //bbaum.drawRadius(100, 1818.54657, 5813.29982);

        List mylist = new List(path);

        while(true){
            if(StdDraw.hasNextKeyTyped()&&(StdDraw.nextKeyTyped()=='k')){
                double x= StdDraw.mouseX();
                double y= StdDraw.mouseY();
                int[] z;
                System.out.println("List...");
                mylist.drawRadius(400, x, y);
                z=mylist.nodesInRadius(400,x,y);
                System.out.println("Trainstations: "+z[0] + "; Airports: " + z[1]);
                //System.out.println("Number of Airports List: "+mylist.numAPTS(100,20));
                System.out.println("List done.");

                System.out.println("Baum...");
                bbaum.drawRadius(400, x, y);
                z=bbaum.nodesInRadius(400,x,y);
                System.out.println("Trainstations: "+z[0] + "; Airports: " + z[1]);
                //System.out.println("Number of Airports BTDTree: "+bbaum.numAPTS(100,20));
                System.out.println("Baum done.");
            }
        }*/
    }
}
