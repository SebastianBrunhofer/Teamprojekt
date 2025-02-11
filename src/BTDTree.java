import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class BTDTree implements Datastructure {
    BTDTreeNode root;
    double maxX = 0;
    double maxY = 0;

    double minY = Integer.MAX_VALUE;
    double minX = Integer.MAX_VALUE;

    public BTDTree() {

    }

    public BTDTree(String p) {
        construct(p);
    }

    public void add(TransportNode d) {
        if (root != null) {
            root.add(d);
        } else {
            root = new BTDTreeNode(d);
        }
    }

    public void construct(String path) {
        ArrayList<TransportNode> arr = new ArrayList<TransportNode>();
        try (Scanner scn = new Scanner(new File(path), "UTF-8")) {
            while (scn.hasNextLine()) {
                String temp = scn.nextLine();
                arr.add(new TransportNode(temp));
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
            System.exit(1);
        }
        construct(arr, true);
    }

    private void construct(ArrayList<TransportNode> arr, boolean chk) {
        if (arr.size() != 0) {
            int median = arr.size() / 2;
            if (chk) {
                arr.sort(Comparator.comparingDouble(TransportNode::getxCoord));
            } else {
                arr.sort(Comparator.comparingDouble(TransportNode::getyCoord));
            }
            add(arr.get(median));
            if (median > 0) {
                ArrayList<TransportNode> left = splitAL(arr, 0, median);
                construct(left, !chk);
            }
            if (median < arr.size()) {
                ArrayList<TransportNode> right = splitAL(arr, median + 1, arr.size());
                construct(right, !chk);
            }
        }
    }

    private static ArrayList splitAL(ArrayList old, int indexSt, int indexEnd) {
        ArrayList neu = new ArrayList();
        for (int i = indexSt; i < indexEnd; i++) {
            neu.add(old.get(i));
        }
        return neu;
    }

    public int[] nodesInRadius(double r, double xThis, double yThis) {
        return root.nodesInRadiusOPT(xThis, yThis, r, true);
        //return root.nodesInRadius(r,xThis,yThis);
    }

    public int[] nodesInRadiusCOLOR(double r, double xThis, double yThis) {
        return root.nodesInRadiusOPTColor(xThis, yThis, r, true);
        //return root.nodesInRadius(r,xThis,yThis);
    }

    public int numAPTS(double r, int n) {
        return root.numAPTS(r, n);
    }

    //Methode um alle Flughäfen und Bahnhöfe auf einer Karte anzuzeigen
    public void zeichnen() {
        StdDraw.setCanvasSize(1500, 800);
        StdDraw.setXscale(minX, maxX);
        StdDraw.setYscale(minY, maxY);
        StdDraw.enableDoubleBuffering();
        root.zeichR();
        StdDraw.show();

    }

    //Methode um einen Radius auf der Karte einzuzeichnen
    public void drawRadius(double r, double x, double y) {
        Color h = StdDraw.getPenColor();
        StdDraw.setPenRadius(0.004);
        StdDraw.setPenColor(StdDraw.GREEN);

        StdDraw.circle(x, y, r);

        StdDraw.setPenRadius(0.002);
        StdDraw.setPenColor(h);
        StdDraw.show();
    }

    public class BTDTreeNode {
        private TransportNode data;
        private BTDTreeNode left;
        private BTDTreeNode right;

        public BTDTreeNode(TransportNode d) {
            data = d;
        }

        public void add(TransportNode neu) {
            add(new BTDTreeNode(neu), true);

        }

        public void add(BTDTreeNode neu, boolean chk) {
            if (chk) { //damit in jeder ebene des Baumes abwechselnd jeweils nur x oder die y koordinate verglichen wird

                if (neu.data.getxCoord() < data.getxCoord()) {

                    if (left != null) {
                        left.add(neu, !chk);
                    } else {
                        left = neu;
                        if (neu.data.getxCoord() > maxX) maxX = neu.data.getxCoord();
                        if (neu.data.getyCoord() > maxY) maxY = neu.data.getyCoord();

                        if (neu.data.getxCoord() < minX) minX = neu.data.getxCoord();
                        if (neu.data.getyCoord() < minY) minY = neu.data.getyCoord();
                    }
                } else {

                    if (right != null) {
                        right.add(neu, !chk);
                    } else {
                        right = neu;
                        if (neu.data.getxCoord() > maxX) maxX = neu.data.getxCoord();
                        if (neu.data.getyCoord() > maxY) maxY = neu.data.getyCoord();

                        if (neu.data.getxCoord() < minX) minX = neu.data.getxCoord();
                        if (neu.data.getyCoord() < minY) minY = neu.data.getyCoord();
                    }

                }

            } else {

                if (neu.data.getyCoord() < data.getyCoord()) {

                    if (left != null) {
                        left.add(neu, !chk);
                    } else {
                        left = neu;
                        if (neu.data.getxCoord() > maxX) maxX = neu.data.getxCoord();
                        if (neu.data.getyCoord() > maxY) maxY = neu.data.getyCoord();

                        if (neu.data.getxCoord() < minX) minX = neu.data.getxCoord();
                        if (neu.data.getyCoord() < minY) minY = neu.data.getyCoord();
                    }
                } else {

                    if (right != null) {
                        right.add(neu, !chk);
                    } else {
                        right = neu;
                        if (neu.data.getxCoord() > maxX) maxX = neu.data.getxCoord();
                        if (neu.data.getyCoord() > maxY) maxY = neu.data.getyCoord();

                        if (neu.data.getxCoord() < minX) minX = neu.data.getxCoord();
                        if (neu.data.getyCoord() < minY) minY = neu.data.getyCoord();
                    }

                }

            }
        }

        //normale nodesInRadius Methode mit Rekursion und internen lokalen Arrays
//        public int[] nodesInRadius(double r, double xThis, double yThis){
//            int[] train_air2 = new int[2];
//            if(left!=null){
//                int[] temp = left.nodesInRadius( r,  xThis, yThis);
//                train_air2[0]+=temp[0];
//                train_air2[1]+=temp[1];
//            }
//
//            if (Math.abs(data.getxCoord() - xThis) <= r && Math.abs(data.getyCoord() - yThis) <= r) {
//                double distance = TransportNode.distance(data, new TransportNode(xThis, yThis));debCount++;
//                if (distance <= r) {
//                    if (data.getType() == Type.AIRPORT){
//                        train_air2[1]++;
//                        Color old = StdDraw.getPenColor();
//                        StdDraw.setPenColor(Color.YELLOW);
//                        StdDraw.point(data.getxCoord(),data.getyCoord());
//                        StdDraw.setPenColor(old);
//                    } else {
//                        train_air2[0]++;
//                        Color old = StdDraw.getPenColor();
//                        StdDraw.setPenColor(Color.YELLOW);
//                        StdDraw.point(data.getxCoord(),data.getyCoord());
//                        StdDraw.setPenColor(old);
//                    }
//
//                }
//            }
//
//            if(right!=null){
//                int[] temp = right.nodesInRadius( r,  xThis, yThis);
//                train_air2[0]+=temp[0];
//                train_air2[1]+=temp[1];
//            }
//
//            return train_air2;
//        }

        public int[] nodesInRadiusOPTColor(double x, double y, double r, boolean chk) {
            int[] numAPTS = new int[2];
            if (inRadius(x, y, r)) {
                if (data.getType() == Type.AIRPORT) {
                    numAPTS[1]++;

                }
                if (data.getType() == Type.TRAINSTATION) {
                    numAPTS[0]++;

                }
                Color old = StdDraw.getPenColor();
                StdDraw.setPenColor(Color.YELLOW);
                StdDraw.point(data.getxCoord(), data.getyCoord());
                StdDraw.setPenColor(old);
            }
            if (chk) {
                switch (relToInterval(x, data.getxCoord(), r)) {
                    case 1:
                        if (left != null) {
                            int[] temp = left.nodesInRadiusOPTColor(x, y, r, !chk);
                            numAPTS[0] += temp[0];
                            numAPTS[1] += temp[1];
                        }
                        break;
                    case 0:
                        if (left != null) {
                            int[] temp = left.nodesInRadiusOPTColor(x, y, r, !chk);
                            numAPTS[0] += temp[0];
                            numAPTS[1] += temp[1];
                        }
                        if (right != null) {
                            int[] temp = right.nodesInRadiusOPTColor(x, y, r, !chk);
                            numAPTS[0] += temp[0];
                            numAPTS[1] += temp[1];
                        }
                        break;
                    case -1:
                        if (right != null) {
                            int[] temp = right.nodesInRadiusOPTColor(x, y, r, !chk);
                            numAPTS[0] += temp[0];
                            numAPTS[1] += temp[1];
                        }
                        break;
                }
            } else {
                switch (relToInterval(y, data.getyCoord(), r)) {
                    case 1:
                        if (left != null) {
                            int[] temp = left.nodesInRadiusOPTColor(x, y, r, !chk);
                            numAPTS[0] += temp[0];
                            numAPTS[1] += temp[1];
                        }
                        break;
                    case 0:
                        if (left != null) {
                            int[] temp = left.nodesInRadiusOPTColor(x, y, r, !chk);
                            numAPTS[0] += temp[0];
                            numAPTS[1] += temp[1];
                        }
                        if (right != null) {
                            int[] temp = right.nodesInRadiusOPTColor(x, y, r, !chk);
                            numAPTS[0] += temp[0];
                            numAPTS[1] += temp[1];
                        }
                        break;
                    case -1:
                        if (right != null) {
                            int[] temp = right.nodesInRadiusOPTColor(x, y, r, !chk);
                            numAPTS[0] += temp[0];
                            numAPTS[1] += temp[1];
                        }
                        break;
                }
            }
            return numAPTS;
        }

        public int[] nodesInRadiusOPT(double x, double y, double r, boolean chk) {
            int[] numAPTS = new int[2];
            if (inRadius(x, y, r)) {
                if (data.getType() == Type.AIRPORT) {
                    numAPTS[1]++;
                    /*Color old = StdDraw.getPenColor();
                    StdDraw.setPenColor(Color.YELLOW);
                    StdDraw.point(data.getxCoord(),data.getyCoord());
                    StdDraw.setPenColor(old);*/
                }
                if (data.getType() == Type.TRAINSTATION) {
                    numAPTS[0]++;
                    /*Color old = StdDraw.getPenColor();
                    StdDraw.setPenColor(Color.YELLOW);
                    StdDraw.point(data.getxCoord(),data.getyCoord());
                    StdDraw.setPenColor(old);*/
                }

            }
            if (chk) {
                switch (relToInterval(x, data.getxCoord(), r)) {
                    case 1:
                        if (left != null) {
                            int[] temp = left.nodesInRadiusOPT(x, y, r, !chk);
                            numAPTS[0] += temp[0];
                            numAPTS[1] += temp[1];
                        }
                        break;
                    case 0:
                        if (left != null) {
                            int[] temp = left.nodesInRadiusOPT(x, y, r, !chk);
                            numAPTS[0] += temp[0];
                            numAPTS[1] += temp[1];
                        }
                        if (right != null) {
                            int[] temp = right.nodesInRadiusOPT(x, y, r, !chk);
                            numAPTS[0] += temp[0];
                            numAPTS[1] += temp[1];
                        }
                        break;
                    case -1:
                        if (right != null) {
                            int[] temp = right.nodesInRadiusOPT(x, y, r, !chk);
                            numAPTS[0] += temp[0];
                            numAPTS[1] += temp[1];
                        }
                        break;
                }
            } else {
                switch (relToInterval(y, data.getyCoord(), r)) {
                    case 1:
                        if (left != null) {
                            int[] temp = left.nodesInRadiusOPT(x, y, r, !chk);
                            numAPTS[0] += temp[0];
                            numAPTS[1] += temp[1];
                        }
                        break;
                    case 0:
                        if (left != null) {
                            int[] temp = left.nodesInRadiusOPT(x, y, r, !chk);
                            numAPTS[0] += temp[0];
                            numAPTS[1] += temp[1];
                        }
                        if (right != null) {
                            int[] temp = right.nodesInRadiusOPT(x, y, r, !chk);
                            numAPTS[0] += temp[0];
                            numAPTS[1] += temp[1];
                        }
                        break;
                    case -1:
                        if (right != null) {
                            int[] temp = right.nodesInRadiusOPT(x, y, r, !chk);
                            numAPTS[0] += temp[0];
                            numAPTS[1] += temp[1];
                        }
                        break;
                }
            }
            return numAPTS;
        }

        private boolean inRadius(double x, double y, double radius) {
            return (Math.abs(data.getxCoord() - x) <= radius && Math.abs(data.getyCoord() - y) <= radius) && (TransportNode.distance(data, new TransportNode(x, y)) <= radius);
        }

        private int relToInterval(double center, double value, double radius) {
            if (value < (center - radius)) {
                return -1;
            } else if (value > (center + radius)) {
                return 1;
            } else {
                return 0;
            }
        }

        public int numAPTS(double r, int n) {
            int erg = 0;
            if (left != null) {
                erg += left.numAPTS(r, n);
            }

            if (right != null) {
                erg += right.numAPTS(r, n);
            }

            if (data.getType().equals(Type.AIRPORT)) {

                if (root.nodesInRadiusOPT(data.getxCoord(), data.getyCoord(), r, true)[0] >= n) {
                    erg++;
                }
                /*if(root.nodesInRadius(r,data.getxCoord(),data.getyCoord())[0]>=n){
                    erg++;
                }*/
            }

            return erg;
        }

        //Hilfsmethode für die Methode zeichnen() die den ganzen Baum traversiert und bei jedem Knoten einen Punkt einzeichnet
        private void zeichR() {
            if (left != null) {
                left.zeichR();
            }
            if (data.getType() == Type.AIRPORT) {
                StdDraw.setPenColor(StdDraw.RED);
                StdDraw.setPenRadius(0.002);
            } else {
                StdDraw.setPenColor(StdDraw.BLUE);
                StdDraw.setPenRadius(0.002);
            }

            StdDraw.point(data.getxCoord(), data.getyCoord());

            if (right != null) {
                right.zeichR();
            }
        }

    }//TreeNode ende
}
