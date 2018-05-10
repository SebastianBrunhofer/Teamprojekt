public class TransportNode {

    private String name;
    private double xCoord;
    private double yCoord;
    private Type type;

    public TransportNode(String s){
        String[] temp = s.split(";");
        name = temp[0];
        xCoord = Double.parseDouble(temp[1]);
        yCoord = Double.parseDouble(temp[2]);
        type = temp[3].equals("AIRPORT") ? Type.AIRPORT : Type.TRAINSTATION;
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
}

enum Type{
    TRAINSTATION, AIRPORT
}
