import java.util.Stack;

    private boolean[] visited;
    private int time; 
    private int tmp;
    private int[] index;
    private int[] lowlink;
    private Stack<Integer> stack;

    public Tarjan (Graph G) {
        visited = new boolean[G.V()];
        stack = new Stack<Integer>();
        index = new int[G.V()];
        lowlink = new int[G.V()];
        for (int u = 0; u < G.V(); u++) {
            if (!visited[u]) {
                dfs(G, u);
            }
        }
    }
    
    public void dfs (Graph G, int u) {
        time++;
        visited[u] = true;
        int min = lowlink[u];
        stack.push(v);

        boolean[] adjVec = G.adjMat(u);
        for (int w = 0; w < G.V(); w++) {
            if (adjVec[w]) {
                if (!visited[w]) {
                    dfs(G, w);
                }
                if (lowlink[w] < min) {
                    min = low[w];
                }
            }
        }

        if (min < lowlink[u]) {
            lowlink[u] = min;
            return;
        }

        int w;

        do {
            w = stack.pop();
            index[w] = time;
            low[w] = G.V();
        } while (w != v);

        time++;
    }

    public int time() {
        return time;
    }

    public boolean isSC (int u, int v) {
        return index[u] == index[v];
    }

}
