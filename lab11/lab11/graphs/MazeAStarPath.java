package lab11.graphs;


import edu.princeton.cs.algs4.MinPQ;

/**
 *  @author Josh Hug
 */
public class MazeAStarPath extends MazeExplorer {
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;
    private MinPQ<Node> pq;

    public MazeAStarPath(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
        pq = new MinPQ<>();
    }

    private class Node implements Comparable<Node> {
        private int index;
        private int priority;

        public int compareTo(Node o) {
            return this.priority - o.priority;
        }

        public Node(int index, int priority) {
            this.index = index;
            this.priority = priority;
        }
    }

    /** Estimate of the distance from v to the target. */
    private int h(int v) {
        return Math.abs(maze.toX(v) - maze.toX(t)) + Math.abs(maze.toY(v) - maze.toY(t));
    }

    /** Finds vertex estimated to be closest to target. */
    private int findMinimumUnmarked() {
        return -1;
        /* You do not have to use this method. */
    }

    /** Performs an A star search from vertex s. */
    private void astar(int s) {
        // TODO
        marked[s] = true;
        announce();

        if (s == t) {
            return;
        }

        pq.insert(new Node(s, h(s)));
        while (!pq.isEmpty()) {
            Node n = pq.delMin();
            if (n.index == t) {
                return;
            }
            for (int v : maze.adj(n.index)) {
                if (!marked[v]) {
                    marked[v] = true;
                    distTo[v] = distTo[n.index] + 1;
                    edgeTo[v] = n.index;
                    announce();
                    pq.insert(new Node (v, h(v)));
                }

            }
        }


    }

    @Override
    public void solve() {
        astar(s);
    }

}

