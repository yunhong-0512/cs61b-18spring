package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;

import java.util.*;

public class Solver {
    private MinPQ<SearchNode> pq;
    private Stack<WorldState> solution;


    private class SearchNode implements Comparable<SearchNode> {
        private int moveSofar;
        private SearchNode prev;
        private WorldState state;

        public SearchNode(int move, SearchNode prev, WorldState state) {
            this.moveSofar = move;
            this.prev = prev;
            this.state = state;
        }

        public int priority() {
            return moveSofar + state.estimatedDistanceToGoal();
        }

        public int compareTo(SearchNode node) {
            return this.priority() - node.priority();
        }

    }
    /**
     * Constructor which solves the puzzle, computing
     * everything necessary for moves() and solution() to
     * not have to solve the problem again. Solves the
     * puzzle using the A* algorithm. Assumes a solution exists.
     * @param initial
     */
    public Solver(WorldState initial) {

        pq = new MinPQ<>();
        SearchNode initialNode = new SearchNode(0, null, initial);
        pq.insert(initialNode);
        solution = new Stack<>();

        SearchNode goal = null;
        while (!pq.isEmpty()) {
            SearchNode removeNode = pq.delMin();
            if (removeNode.state.isGoal()) {
                goal = removeNode;
                break;
            } else {
                for (WorldState state : removeNode.state.neighbors()) {
                    if (removeNode.prev == null || !removeNode.prev.state.equals(state)) {
                        pq.insert(new SearchNode(removeNode.moveSofar + 1, removeNode, state));
                    }

                }
            }
        }

        while (goal != null) {
            solution.push(goal.state);
            goal = goal.prev;
        }

    }




    /**
     * Returns the minimum number of moves to solve the puzzle starting
     * at the initial WorldState.
     * @return
     */
    public int moves() {
        return solution.size() - 1;
    }

    /**
     * Returns a sequence of WorldStates from the initial WorldState
     * to the solution.
     * @return
     */
    public Iterable<WorldState> solution() {
        return solution;
    }

}
