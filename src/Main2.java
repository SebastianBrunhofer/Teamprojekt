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
            list = new List(scn);
            System.out.println("Finished reading");
        } catch(FileNotFoundException e){
            System.out.println("File not found!");
            System.exit(1);
        }
        //Testcases
        System.out.println("Time: "+(System.currentTimeMillis()-stTime));
        int[] a = list.nodesInRadius(100, 1818.54657, 5813.29982);
        System.out.println(a[0] + " " + a[1]);
        System.out.println("Number of Airports: "+list.numAPTS(15.0,20));
    }
}
