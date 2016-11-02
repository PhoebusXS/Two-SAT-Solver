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

    public int[] adjMat(int u) {
        int count = 0;
        for (boolean v : this.adjMat[u]) {
            if (v) count++;
        }
        int[] adjVec = new int[count];
        int j = 0;
        for (int i = 0; i < this.V; i++) {
            if (this.adjMat[u][i]) {
                adjVec[j++] = i;
            }
        }
        return adjVec;
    }

    public boolean[][] adjMat() {
        return this.adjMat;
    }

}
