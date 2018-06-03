public interface Datastructure {

    void add(TransportNode newNode);

    void construct(String inputFilePath);

    int[] nodesInRadius(double radius, double x, double y);

    int numAPTS(double radius, int anzahl);

    void zeichnen();

    void drawRadius(double radius, double x, double y);

    int[] nodesInRadiusCOLOR(double radius, double x, double y);
}