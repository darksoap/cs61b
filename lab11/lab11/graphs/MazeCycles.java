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
    private int s;
    private int[] helpEdgeTo;
    private int endVertice = -1;
    private boolean hasCycle = false;
    private Maze maze;

    public MazeCycles(Maze m) {
        super(m);
        maze = m;
        s = maze.xyTo1D(1, 1);
        helpEdgeTo = new int[maze.V()];
        helpEdgeTo[s] = s;
    }

    @Override
    public void solve() {
        // TODO: Your code here!
        findCy(s);
        if (endVertice == -1) {
            System.out.println("The graph doesn't have cycle");
        }
    }

    // Helper methods go here

    public void findCy(int v) {
        marked[v] = true;
        announce();
        for (int i : maze.adj(v)) {
            if (!marked[i]) {
                helpEdgeTo[i] = v;
                findCy(i);
            } else if (marked[i] && i != helpEdgeTo[v]) {
                edgeTo[i] = v;
                announce();
                endVertice = i;
                int lastVertice = edgeTo[endVertice];
                while (lastVertice != endVertice) {
                    edgeTo[lastVertice] = helpEdgeTo[lastVertice];
                    announce();
                    lastVertice = edgeTo[lastVertice];
                }
                hasCycle = true;
                return;
            } else {
                continue;
            }
            if (hasCycle) {
                return;
            }
        }
    }
}

