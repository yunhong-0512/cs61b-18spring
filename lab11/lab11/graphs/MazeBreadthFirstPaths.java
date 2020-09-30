package lab11.graphs;

import java.util.LinkedList;
import java.util.Queue;

/**
 *  @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */

    private int s;
    private int t;
    private Maze maze;

    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        // Add more variables here!
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
    }

    /** Conducts a breadth first search of the maze starting at the source. */
    private void bfs() {
        marked[s] = true;
        announce();

        if (s == t) {
            return;
        }

        Queue<Integer> q = new LinkedList<>();
        q.offer(s);
        while (!q.isEmpty()) {
            int n = q.poll();
            if (n == t) {
                return;
            }
            for (int w : maze.adj(n)) {
                if (!marked[w]) {
                    edgeTo[w] = n;
                    distTo[w] = distTo[n] + 1;
                    marked[w] = true;
                    announce();
                    q.offer(w);
                }
            }
        }


    }


    @Override
    public void solve() {
        bfs();
    }
}

