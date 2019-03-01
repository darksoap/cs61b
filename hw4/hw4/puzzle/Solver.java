package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;

public class Solver {
    /**
     * Constructor which solves the puzzle, computing
     * everything necessary for moves() and solution() to
     * not have to solve the problem again. Solves the
     * puzzle using the A* algorithm. Assumes a solution exists.
     * @param initial
     */
    private Stack<WorldState> close = new Stack<>();

    public Solver(WorldState initial) {
        MinPQ<SearchNode> tracker = new MinPQ<>();
        tracker.insert(new SearchNode(initial, 0, null));

        SearchNode goal = null;
        while (!tracker.isEmpty()) {
            SearchNode min = tracker.delMin();
            WorldState world = min.current();
            SearchNode prev = min.prev();
            if (world.isGoal()) {
                goal = min;
                break;
            }
            for (WorldState w : world.neighbors()) {
                if (prev == null || (prev != null && !w.equals(prev.current()))) {
                    tracker.insert(new SearchNode(w, min.movedis() + 1, min));
                }
            }
        }

        while (goal != null) {
            close.push(goal.current());
            goal = goal.prev();
        }
    }

    private class SearchNode implements Comparable<SearchNode> {
        private WorldState current;
        private int move;
        private SearchNode previous;
        private int priority;

        SearchNode(WorldState world, int dis, SearchNode prev) {
            current = world;
            move = dis;
            previous = prev;
            priority = move + world.estimatedDistanceToGoal();
        }

        public int compareTo(SearchNode o) {
            return this.priority - o.priority;
        }

        public WorldState current() {
            return current;
        }

        public int movedis() {
            return move;
        }

        public SearchNode prev() {
            return previous;
        }
    }


    /**
     * Returns the minimum number of moves to solve the puzzle starting
     * at the initial WorldState.
     * @return
     */
    public int moves() {
        return close.size() - 1;
    }

    /**
     * Returns a sequence of WorldStates from the initial WorldState
     * to the solution.
     * @return
     */
    public Iterable<WorldState> solution() {
        return close;
    }
}
