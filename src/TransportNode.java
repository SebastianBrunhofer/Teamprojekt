public class TransportNode {

    private String name;
    private double xCoord;
    private double yCoord;
    private Type type;

    public TransportNode(String s){
        String[] temp = s.split(";");
        for (int i = 0; i < temp.length; i++) {
            temp[i] = temp[i].trim();
        }
        name = temp[0];
        xCoord = Double.parseDouble(temp[1]);
        yCoord = Double.parseDouble(temp[2]);
        type = temp[3].equals("AIRPORT") ? Type.AIRPORT : Type.TRAINSTATION;
    }
    public TransportNode(double xCoord, double yCoord){
        this.xCoord = xCoord;
        this.yCoord = yCoord;
    }

    public String getName() {
        return name;
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

    public static double distance(TransportNode a, TransportNode b){
        double xDist = a.getxCoord() - b.getxCoord();
        double yDist = a.getyCoord() - b.getyCoord();
        return Math.sqrt(Math.pow(xDist, 2) + Math.pow(yDist, 2));
    }
}

enum Type{
    TRAINSTATION, AIRPORT
}
