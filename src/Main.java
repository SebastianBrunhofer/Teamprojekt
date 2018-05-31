import java.util.Scanner;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        String path = "data/junctions.csv";
        menu(path);
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

    private static void menu( String path){
        System.out.println("Main Menu: " + '\n' + '\t' + "1:  run testcases" + '\n' + '\t' + "2:  test nodesInRadius" + '\n' + '\t' + "3:  test nodesInRadius (map Version) wip" + '\n' + '\t' + "4:  test numAPTS" + '\n' + '\t' + "0:  cancel");
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){
            if (sc.hasNextInt()){
                switch (sc.nextInt()){
                    case 0:
                        return;
                    case 1:
                        testcases(path);
                        break;
                    case 2:
                        testNIRMenu(path);
                        break;
                    case 3:
                        //Falls (zeitlich) machbar, ist hier Platz für eine Implementierung eines NIR-Tests mittels Eingabe auf der Karte. Falls nicht (mehr) möglich, bitte case entfernen und Zahlen dementsprechend anpassen.
                        break;
                    case 4:
                        testNumAPTSMenu(path);
                        break;
                    default:
                        System.out.println("Invalid entry");
                        break;
                }
            } else {
                sc.next();
            }
        }
    }
    private static void testcases(String path){
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
    }
    private static void testNIRMenu(String path){
        Datastructure datSt = null;
        System.out.println("NIR-Menu: " + '\n' + '\t' + "1: test List.nodesInRadius" + '\n' + '\t' + "2: test TDTree.nodesInradius" + '\n' + '\t' + "0: cancel");
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){
            if (sc.hasNextInt()){
                switch (sc.nextInt()){
                    case 0:
                        return;
                    case 1:
                        datSt = new List(path);
                        break;
                    case 2:
                        datSt = new BTDTree();
                        datSt.construct(path);
                        break;
                    default:
                        System.out.println("Invalid entry");
                        break;
                }
            } else {
                sc.next();
            }
            if (datSt != null){
                double radius = 0, xCoord = 0, yCoord = 0;
                System.out.println("Enter X-Coordinate:");
                if (sc.hasNextDouble()){
                    xCoord = sc.nextDouble();
                }
                System.out.println("Enter Y-Coordinate:");
                if (sc.hasNextDouble()){
                    yCoord = sc.nextDouble();
                }
                System.out.println("Enter radius:");
                if (sc.hasNextDouble()){
                    radius = sc.nextDouble();
                }
                int[] temp = datSt.nodesInRadius(radius, xCoord, yCoord);
                System.out.println("Airports: " + temp[1] + '\n' + "Trainstations: " + temp[0]);
                System.out.println("returning to Main Menu");
                return;
            }
        }
    }
    private static void testNumAPTSMenu(String path){
        Datastructure datSt = null;
        System.out.println("NumAPTS-Menu: " + '\n' + '\t' + "1: test List.numAPTS" + '\n' + '\t' + "2: test TDTree.numAPTS" + '\n' + '\t' + "0: cancel");
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){
            if (sc.hasNextInt()){
                switch (sc.nextInt()){
                    case 0:
                        return;
                    case 1:
                        datSt = new List(path);
                        break;
                    case 2:
                        datSt = new BTDTree();
                        datSt.construct(path);
                        break;
                    default:
                        System.out.println("Invalid entry");
                        break;
                }
            } else {
                sc.next();
            }
            if (datSt != null){
                double radius = 0;
                int num = 0;
                System.out.println("Enter minimum number of surrounding trainstations");
                if (sc.hasNextInt()){
                    num = sc.nextInt();
                }
                System.out.println("Enter radius:");
                if (sc.hasNextDouble()){
                    radius = sc.nextDouble();
                }
                int temp = datSt.numAPTS(radius, num);
                System.out.println(temp + " airports with at least " + num + " trainstations within a radius of " + radius + " units found.");
                System.out.println("returning to Main Menu");
                return;
            }
        }
    }
}
