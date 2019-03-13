package lab11.graphs;

import edu.princeton.cs.algs4.MinPQ;

/**
 *  @author Josh Hug
 */
public class MazeAStarPath extends MazeExplorer {
    private int s;
    private int t;
    private boolean targetFound = false;
    private MinPQ<SearchNode> q = new MinPQ<>();
    private Maze maze;

    public MazeAStarPath(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
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
        q.insert(new SearchNode(s, null, 0, h(s)));
        while (!q.isEmpty()) {
            SearchNode currentNode = q.delMin();
            int vertice = currentNode.vertice();
            SearchNode pre = currentNode.preVertice();
            marked[vertice] = true;
            if (pre == null) {
                edgeTo[vertice] = s;
            } else {
                edgeTo[vertice] = pre.vertice();
            }
            distTo[vertice] = currentNode.dist();
            announce();
            if (marked[t]) {
                targetFound = true;
                return;
            }
            for (int i : maze.adj(vertice)) {
                if (i != edgeTo[vertice]) {
                    q.insert(new SearchNode(i, currentNode, currentNode.dist() + 1, h(i)));
                }
            }
        }
    }

    private class SearchNode implements Comparable<SearchNode> {
        private int index;
        private SearchNode prev;
        private int distance;
        private int priority;
        public SearchNode(int i, SearchNode prev, int current, int estimate) {
            index = i;
            distance = current;
            this.prev = prev;
            priority = current + estimate;
        }

        public int vertice() {
            return index;
        }

        public SearchNode preVertice() {
            return prev;
        }
        public int dist() {
            return distance;
        }

        @Override
        public int compareTo(SearchNode y) {
            return this.priority - y.priority;
        }
    }
    @Override
    public void solve() {
        astar(s);
    }

}

