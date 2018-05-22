import java.util.Scanner;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        ListNode list = new ListNode();
        ListNode current = list;
        String path = "data/junctions.csv";
        try(Scanner scn = new Scanner(new File(path),"UTF-8"))
        {
            while(scn.hasNextLine()){
                current.setValue(new TransportNode(scn.nextLine()));
                current.setNext(new ListNode());
                current = current.getNext();
            }
        } catch(FileNotFoundException e){
            System.out.println("File not found!");
            System.exit(1);
        }
        //Testcases
        int[] a = list.nodesInRadius(10000, 1818.54657, 5813.29982);
        //int[] a = list.nodesInRadius(20, 2000, 3000);
        System.out.println(a[0] + " " + a[1]);

        a = list.nodesInRadius(10000, 1818.54657, 2000.29982);
        System.out.println(a[0] + " " + a[1]);

        a = list.nodesInRadius(10000, 1818.54657, -300.29982);
        System.out.println(a[0] + " " + a[1]);

        a = list.nodesInRadius(10000, -1818.54657, 5813.29982);
        System.out.println(a[0] + " " + a[1]);

        a = list.nodesInRadius(10000, -1818.54657, -5813.29982);
        System.out.println(a[0] + " " + a[1]);


        long start = System.currentTimeMillis();
        System.out.println("Number of Airports: "+list.numAPTS(15,20));
        System.out.println("Number of Airports: "+list.numAPTS(15,1));
        System.out.println("Number of Airports: "+list.numAPTS(15,5));
        System.out.println("Number of Airports: "+list.numAPTS(15,10));
        double time = (System.currentTimeMillis()-start)/1000;
        System.out.println(time);
    }
}
