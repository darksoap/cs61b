package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

public class MapGenerator {

    static final int WIDTH = 60;
    static final int HEIGHT = 40;
    public static final long SEED = 4564645;
    public static final Random RANDOM = new Random(SEED);

    private static class Position {
        private int x;
        private int y;

        public Position(int x, int y) {
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

    public static int[][] overlapRoom = new int[WIDTH][HEIGHT];

    /**
     * fill map with random rooms
     * @param tiles
     */
    public static void fillRoom(TETile[][] tiles, int numRoomTries) {
        for (int i = 0; i < numRoomTries; i += 1) {
            int height = RANDOM.nextInt(HEIGHT / 10 + 2)  + 2;
            int width = RANDOM.nextInt(WIDTH / 10 + 2)  + 2;
            MapGenerator.Position rp = randomPos(WIDTH - width, HEIGHT - height);
            tiles = addRoom(tiles,rp,width,height);
        }
    }

    /**
     * 生成一个房间，如果重叠则返回原数组
     * @param tiles
     * @param position
     * @param width
     * @param height
     * @return
     */
    public static TETile[][] addRoom(TETile[][] tiles, Position position, int width, int height) {
            TETile[][] backup = TETile.copyOf(tiles);
            int[][] roomBackup = overlapRoom.clone();

            for (int x = position.x; x < position.x + width; x += 1){
                for (int y = position.y; y < position.y + height; y += 1){
                    if (overlapRoom[x][y] == 1) {
                        overlapRoom = roomBackup;
                        return backup;
                    }
                    tiles[x][y] = Tileset.WALL;
                    overlapRoom[x][y] = 1;
                }
            }
            return tiles;
    }

    public static void generate(TETile[][] tiles) {
        fillRoom(tiles,100);
    }
}
