import java.util.Scanner;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        ListNode list = new ListNode();
        ListNode current = list;
        String path = "C:/Users/Sebastian/Desktop/Teamaufgabe/data/junctions.csv";
        try(Scanner scn = new Scanner(new File(path),"UTF-8"))
        {
            while(scn.hasNextLine()){
                //System.out.println(scn.nextLine());
                current.setValue(new TransportNode(scn.nextLine()));
                current.setNext(new ListNode());
                current = current.getNext();
            }
        } catch(FileNotFoundException e){
            System.out.println("File not found!");
            System.exit(1);
        }
    }
}
