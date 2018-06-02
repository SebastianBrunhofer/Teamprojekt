import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class List implements Datastructure{
    public ListNode start;

    //max/min Variablen fürs zeichnen der Karte
    double maxX=0;
    double maxY =0;

    double minY =Integer.MAX_VALUE;
    double minX = Integer.MAX_VALUE;

    public List(String path){
        construct(path);
    }

    public void construct(String path){
        try(Scanner scn = new Scanner(new File(path),"UTF-8"))
        {
            start = new ListNode();
            if (scn.hasNextLine()){
                start.setValue(new TransportNode(scn.nextLine()));
                if(start.getValue().getxCoord()<minX)minX=start.getValue().getxCoord();
                if(start.getValue().getxCoord()>maxX)maxX=start.getValue().getxCoord();

                if(start.getValue().getyCoord()<minY)minY=start.getValue().getyCoord();
                if(start.getValue().getyCoord()>maxY)maxY=start.getValue().getyCoord();
            }
            ListNode current = start;
            while(scn.hasNextLine()){
                current.setNext(new ListNode(new TransportNode(scn.nextLine())));

                if(current.getNext().getValue().getxCoord()<minX)minX=current.getNext().getValue().getxCoord();
                if(current.getNext().getValue().getxCoord()>maxX)maxX=current.getNext().getValue().getxCoord();

                if(current.getNext().getValue().getyCoord()<minY)minY=current.getNext().getValue().getyCoord();
                if(current.getNext().getValue().getyCoord()>maxY)maxY=current.getNext().getValue().getyCoord();

                current = current.getNext();
            }
        } catch(FileNotFoundException e){
            System.out.println("File not found!");
            System.exit(1);
        }
    }


    //Berechnet die Anzahl der Bahnhöfe und Flughäfen in einem Radius von r um den Punkt (xThis, yThis)
    public int[] nodesInRadius(double r, double xThis, double yThis){
        ListNode current = start;
        int[] train_air = new int[2];
        while (current!= null) {
            double xCurrent = current.getValue().getxCoord();
            double yCurrent = current.getValue().getyCoord();
            if (Math.abs(xCurrent - xThis) <= r && Math.abs(yCurrent - yThis) <= r) {
                double distance = TransportNode.distance(current.getValue(), new TransportNode(xThis, yThis));
                if (distance <= r) {
                    if (current.getValue().getType() == Type.AIRPORT){
                        train_air[1]++;
                    } else {
                        train_air[0]++;
                    }
                }
            }
            current = current.getNext();
        }
        return train_air;
    }

    public int[] nodesInRadiusCOLOR(double r, double xThis, double yThis){
        ListNode current = start;
        int[] train_air = new int[2];
        while (current!= null) {
            double xCurrent = current.getValue().getxCoord();
            double yCurrent = current.getValue().getyCoord();
            if (Math.abs(xCurrent - xThis) <= r && Math.abs(yCurrent - yThis) <= r) {
                double distance = TransportNode.distance(current.getValue(), new TransportNode(xThis, yThis));
                if (distance <= r) {
                    if (current.getValue().getType() == Type.AIRPORT){
                        train_air[1]++;
                    } else {
                        train_air[0]++;
                    }
                    Color old = StdDraw.getPenColor();
                    StdDraw.setPenColor(Color.YELLOW);
                    StdDraw.point(xCurrent,yCurrent);
                    StdDraw.setPenColor(old);
                }
            }
            current = current.getNext();
        }
        return train_air;
    }

    public void add(TransportNode neu){
        add(new ListNode(neu));
    }

    public void add(ListNode neu){
        if(start!=null){
            ListNode curr = start;
            while(curr.getNext() !=null){
                curr = curr.getNext();
            }
            curr.setNext(neu);
        }
        else{
            start=neu;
        }
        if(neu.getValue().getxCoord()<minX)minX=neu.getValue().getxCoord();
        if(neu.getValue().getxCoord()>maxX)maxX=neu.getValue().getxCoord();

        if(neu.getValue().getyCoord()<minY)minY=neu.getValue().getyCoord();
        if(neu.getValue().getyCoord()>maxY)maxY=neu.getValue().getyCoord();
    }

    public void zeichnen(){
        StdDraw.setCanvasSize(1500,800);
        System.out.println("X: "+minX+" -> "+maxX);
        System.out.println("Y: "+minY+" -> "+maxY);
        StdDraw.setXscale(minX,maxX);
        StdDraw.setYscale(minY,maxY);
        StdDraw.enableDoubleBuffering();
        ListNode curr = start;
        while(curr.getNext() !=null){

            if(curr.getValue().getType()==Type.AIRPORT){
                StdDraw.setPenColor(StdDraw.RED);
                StdDraw.setPenRadius(0.002);
            }
            else{
                StdDraw.setPenColor(StdDraw.BLUE);
                StdDraw.setPenRadius(0.002);
            }

            StdDraw.point(curr.getValue().getxCoord(),curr.getValue().getyCoord());
            curr = curr.getNext();
        }

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
    //Diese Methode soll nodesInRadius verwenden um die Anzahl aller Airports zu berechnen die n-viele Bahnhöfe, in einem Umkreis von r um sich haben
    public int numAPTS(double r, int n){
        int erg=0;
        ListNode current = start;
        while(current!=null){

            if(current.getValue()==null){
                System.out.println("Value is NULL!");
            }
            else{
                if(current.getValue().getType()==null){
                    System.out.println("Type is NULL!");
                }
                else{
                    if(current.getValue().getType().equals(Type.AIRPORT)){
                        if(nodesInRadius(r,current.getValue().getxCoord(),current.getValue().getyCoord())[0]>=n){
                            erg++;
                            //System.out.println("Found!");
                        }
                        else{
                            //System.out.println("NOT!");
                        }
                    }
                }


            }

            current=current.getNext();
        }

        return erg;
    }
}

