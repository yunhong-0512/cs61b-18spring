package lab11.graphs;

/**
 *  @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */


    private Maze maze;

    public MazeCycles(Maze m) {
        super(m);
        maze = m;
    }

    @Override
    public void solve() {
        // TODO: Your code here!
        dfs(0);
    }

    // Helper methods go here
    private void dfs(int v) {
        if (v == maze.V()) {
            return;
        }
        for (int w : maze.adj(v)) {
            if (marked[w] && distTo[v] != w) {
                edgeTo[w] = v;
                announce();
                return;
            }
        }
        dfs(v + 1);
    }
}

