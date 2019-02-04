package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

public class MapGenerator {
    public static final long SEED = 123456;
    public static final Random RANDOM = new Random(SEED);

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
     * fill map with random rooms
     * @param tiles
     */
    public static void fillRect(TETile[][] tiles, int roomNum) {
        for (int i = 0; i < roomNum; i += 1) {
            int height = RANDOM.nextInt(tiles[0].length / 10 + 2)  + 4;
            int width = RANDOM.nextInt(tiles.length / 10 + 2)  + 4;
            Position rp = randomPos(tiles.length - width, tiles[0].length - height);
            Room.generateRoom(tiles,rp,width,height);
        }
    }

    public static void floodFill(TETile[][] tiles) {

    }

    public static void connect(TETile[][] tiles) {

    }

    public static void simplify(TETile[][] tiles) {

    }

    /**
     * Generate the world
     * @param tiles
     */
    public static void generate(TETile[][] tiles) {
        fillWithEmpty(tiles);
        fillRect(tiles,80);
    }
}
