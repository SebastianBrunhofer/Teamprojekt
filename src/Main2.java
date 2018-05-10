import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        List list = new List();
        String path = "data/junctions.csv";
        long stTime = System.currentTimeMillis();
        try(Scanner scn = new Scanner(new File(path),"UTF-8"))
        {

            while(scn.hasNextLine()){
                //System.out.println(scn.nextLine());
                //String n=scn.nextLine();
                //System.out.println(n);
                list.add(new TransportNode(scn.nextLine()));
            }
            System.out.println("Finished reading");
        } catch(FileNotFoundException e){
            System.out.println("File not found!");
            System.exit(1);
        }
        //Testcases
        int[] a = list.nodesInRadius(100, 1818.54657, 5813.29982);
        System.out.println(a[0] + " " + a[1]);
        System.out.println("Number of Airports: "+list.numAPTS(30.0,20));
        System.out.println("Time: "+(System.currentTimeMillis()-stTime));
    }
}
