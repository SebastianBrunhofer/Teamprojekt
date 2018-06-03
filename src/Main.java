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
    public static void map(String path,Datastructure d){
        Datastructure datSt = d;
        datSt.construct(path);
        datSt.zeichnen();

        boolean running = true;
        boolean chk = false;
        double stX=0;
        double stY=0;
        double endX=0;
        double endY=0;
        while (running){
            if(StdDraw.isMousePressed()){
                if(!chk){
                    chk=true;
                   stX = StdDraw.mouseX();
                   stY = StdDraw.mouseY();
                }
                endX = StdDraw.mouseX();
                endY = StdDraw.mouseY();
            }
            else if(chk){
                running=false;
            }

        }
        double rad = rad(stX,stY,endX,endY);
        datSt.drawRadius(rad, stX, stY);
        StdDraw.show();
        System.out.println("stX: "+stX+" stY: "+stY);
        System.out.println("endX: "+endX+" endY: "+endY);
        long start = System.currentTimeMillis();
        int[] a = datSt.nodesInRadiusCOLOR(rad, stX, stY);
        long time = (System.currentTimeMillis()-start);
        StdDraw.show();

        System.out.println("Trainstations: "+a[0] + "; Airports: " + a[1]);
        System.out.println(time+"ms");

    }
    static double rad(double x,double y,double x2, double y2){
        double xDist = Math.abs(x) - Math.abs(x2);
        double yDist = Math.abs(y) - Math.abs(y2);
        return Math.sqrt(Math.pow(xDist, 2) + Math.pow(yDist, 2));
    }
    private static void menu( String path){
        System.out.println("Main Menu: " + '\n' + '\t' + "1:  run testcases" + '\n' + '\t' + "2:  test nodesInRadius" + '\n' + '\t' + "3/4:  test nodesInRadius (map Version) 3 für Liste, 4 für Baum" + '\n' + '\t' + "5:  test numAPTS" + '\n' + '\t' + "0:  cancel");
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){
            if (sc.hasNextInt()){
                switch (sc.nextInt()){
                    case 0:
                        System.exit(1);
                    case 1:
                        testcases(path);
                        break;
                    case 2:
                        testNIRMenu(path);
                        break;
                    case 3:
                        map(path,new List(path));
                        break;

                    case 4:
                        map(path,new BTDTree(path));
                        break;

                    case 5:
                        testNumAPTSMenu(path);
                        break;
                    default:
                        System.out.println("Invalid entry");
                        break;
                }
            } else {
                sc.next();
            }
            System.out.println();
            System.out.println("Main Menu: " + '\n' + '\t' + "1:  run testcases" + '\n' + '\t' + "2:  test nodesInRadius" + '\n' + '\t' + "3/4:  test nodesInRadius (map Version) 3 für Liste, 4 für Baum" + '\n' + '\t' + "5:  test numAPTS" + '\n' + '\t' + "0:  cancel");
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
        datSt.zeichnen();
        //datSt.drawRadius(10000, 1818.54657, 5813.29982);
        //Testcases
        a = datSt.nodesInRadius(10000, 1818.54657, 5813.29982);
        System.out.println("Trainstations: "+a[0] + "; Airports: " + a[1]);
        StdDraw.show();
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
        datSt.zeichnen();
        datSt.drawRadius(100, 1818.54657, 5813.29982);
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
        TDTree tbaum = new TDTree();
        tbaum.construct(path);
        //Testcases
        a = tbaum.nodesInRadius(10000, 1818.54657, 5813.29982);
        System.out.println("Trainstations: "+a[0] + "; Airports: " + a[1]);

        a = tbaum.nodesInRadius(10000, 1818.54657, 2000.29982);
        System.out.println("Trainstations: "+a[0] + "; Airports: " + a[1]);

        a = tbaum.nodesInRadius(10000, 1818.54657, -300.29982);
        System.out.println("Trainstations: "+a[0] + "; Airports: " + a[1]);

        a = tbaum.nodesInRadius(10000, -1818.54657, 5813.29982);
        System.out.println("Trainstations: "+a[0] + "; Airports: " + a[1]);

        a = tbaum.nodesInRadius(10000, -1818.54657, -5813.29982);
        System.out.println("Trainstations: "+a[0] + "; Airports: " + a[1]);

        start = System.currentTimeMillis();
        System.out.println("Number of Airports: "+tbaum.numAPTS(15,20));
        time = (System.currentTimeMillis()-start);
        System.out.println(time+"ms");

        start = System.currentTimeMillis();
        System.out.println("Number of Airports: "+tbaum.numAPTS(15,1));
        time = (System.currentTimeMillis()-start);
        System.out.println(time+"ms");

        start = System.currentTimeMillis();
        System.out.println("Number of Airports: "+tbaum.numAPTS(15,5));
        time = (System.currentTimeMillis()-start);
        System.out.println(time+"ms");

        start = System.currentTimeMillis();
        System.out.println("Number of Airports: "+tbaum.numAPTS(15,10));
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
                        datSt = new BTDTree(path);
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
                System.out.println("returning to Main Menu...");
                System.out.println();
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
                        datSt = new BTDTree(path);
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
                System.out.println("returning to Main Menu...");
                System.out.println();
                return;
            }
        }
    }
}
