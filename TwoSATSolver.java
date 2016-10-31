public class TwoSATSolver {
	public static main (String[] args) {
	}
}


private class dfs
	private boolean[] visited;
    private int time;

	public void dfs (Graph G, int s) {
		visited = new boolean[G.V()];
		time = 0;
		dfsVisit(G, s);
	}

	public void dfsVisit (Graph G, int u) {
		time++;
		visited[u] = true;
		for (int w : G.adjMat(u)) {
			if !visited[w] {
				dfsVisit(G, w);
			}
		}
	}
}
