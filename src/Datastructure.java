public interface Datastructure {

    public void add(TransportNode newNode);
    public void construct(String inputFilePath);

    public int[] nodesInRadius(double radius, double x, double y);
    public int numAPTS(double radius, int anzahl);

    public void zeichnen();
    public void drawRadius(double radius, double x, double y);
    public int[] nodesInRadiusCOLOR(double radius, double x, double y);
}
