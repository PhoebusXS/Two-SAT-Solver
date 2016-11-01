public class Graph {

    private final int V;
    private boolean[][] adjMat;

    public Graph(int V) {
        this.adjMat = new boolean[V][V];
        this.V = V;
    }

    public void addEdge(int u, int v) {
        this.adjMat[u][v] = true;
    }

    public int V() {
        return this.V;
    }

    public boolean[] adjMat(int u) {
        return this.adjMat[u];
    }

}
