public class TransportNode {

    private String name;
    private double xCoord;
    private double yCoord;
    private Type type;

    public TransportNode(String s){
        String[] temp = s.split(";");
        int invCount = 0;
        if (temp.length == 4) {
            for (int i = 0; i < temp.length; i++) {
                temp[i] = temp[i].trim();
            }
            name = temp[0];
            xCoord = Double.parseDouble(temp[1]);
            yCoord = Double.parseDouble(temp[2]);
            type = temp[3].equals("AIRPORT") ? Type.AIRPORT : Type.TRAINSTATION;
        } else {
            invCount++;
        }
        if (invCount > 0){
            System.out.println(invCount);
        }
    }
    public TransportNode(double xCoord, double yCoord){
        this.xCoord = xCoord;
        this.yCoord = yCoord;
    }

    public double getxCoord() {
        return xCoord;
    }
    public double getyCoord() {
        return yCoord;
    }
    public Type getType() {
        return type;
    }

    public void print() {
        System.out.println("Name: " + name + " x = " + xCoord + " y = " + yCoord + " Typ: " + type);
    }

    /*
     *  Berechnet die Distanz zwischen zwei Transportknotenpunkten
     */
    public static double distance(TransportNode a, TransportNode b){
        double xDist = Math.abs(a.getxCoord()) - Math.abs(b.getxCoord());
        double yDist = Math.abs(a.getyCoord()) - Math.abs(b.getyCoord());
        return Math.sqrt(Math.pow(xDist, 2) + Math.pow(yDist, 2));
    }
}

enum Type{
    TRAINSTATION, AIRPORT
}
