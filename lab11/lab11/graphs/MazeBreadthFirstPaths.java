package lab11.graphs;

import java.util.PriorityQueue;
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
    private boolean targetFound = false;
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
    private void bfs(int v) {
        // TODO: Your code here. Don't forget to update distTo, edgeTo, and marked, as well as call announce()
        marked[v] = true;
        announce();
        if (v == t) {
            targetFound = true;
        }

        if (targetFound) {
            return;
        }

        Queue<Integer> q = new PriorityQueue<>();
        q.add(v);

        while (!q.isEmpty()) {
            int tempv = q.remove();
            for (int w : maze.adj(tempv)) {
                if (!marked[w]) {
                    edgeTo[w] = tempv;
                    distTo[w] = distTo[tempv] + 1;
                    marked[w] = true;
                    announce();
                    if(marked[t]) {
                        return;
                    }
                    q.add(w);
                }
            }
        }
    }


    @Override
    public void solve() {
        bfs(s);
    }
}

