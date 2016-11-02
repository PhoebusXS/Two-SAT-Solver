import java.util.Stack;
import java.util.Arrays;

public class Tarjan {

    private boolean[] visited;
    private int time; 
    private int count;
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
        visited[u] = true;
        lowlink[u] = time++;
        int min = lowlink[u];
        stack.push(u);
        for (int w : G.adjMat(u)) {
            if (!visited[w]) dfs(G, w);
            if (lowlink[w] < min) min = lowlink[w];
        }
        if (min < lowlink[u]) {
            lowlink[u] = min;
            return;
        }
        int w;
        do {
            w = stack.pop();
            index[w] = count;
            lowlink[w] = G.V();
        } while (w != u);
        count++;
    }

    public boolean isSC (int u, int v) {
        return index[u] == index[v];
    }

    public int[] index() {
        return this.index;
    }

    public int count() {
        return this.count;
    }

}
