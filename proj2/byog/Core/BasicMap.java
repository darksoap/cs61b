package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

public class BasicMap {
    static final int WIDTH = 60;
    static final int HEIGHT = 40;
    public static final long SEED = 4564645;
    public static final Random RANDOM = new Random(SEED);

    static class Position {
        private int x;
        private int y;

        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    /**
     * Return a random position
     * @return
     */
    public static Position randomPos(int xRange, int yRange) {
        int x = RANDOM.nextInt(xRange);
        int y = RANDOM.nextInt(yRange);
        Position rp = new Position(x, y);

        return rp;
    }

    /**
     * Fills the given 2D array of tiles with RANDOM tiles.
     * @param tiles
     */
    public static void fillWithEmpty(TETile[][] tiles) {
        int height = tiles[0].length;
        int width = tiles.length;
        for (int x = 0; x < width; x += 1) {
            for (int y = 0; y < height; y += 1) {
                tiles[x][y] = Tileset.NOTHING;
            }
        }
    }
}
