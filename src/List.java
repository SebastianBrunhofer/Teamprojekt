import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class List{
    public ListNode start;

    public List(String path){
        try(Scanner scn = new Scanner(new File(path),"UTF-8"))
        {
            start = new ListNode();
            if (scn.hasNextLine()){
                start.setValue(new TransportNode(scn.nextLine()));
            }
            ListNode current = start;
            while(scn.hasNextLine()){
                current.setNext(new ListNode(new TransportNode(scn.nextLine())));
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

