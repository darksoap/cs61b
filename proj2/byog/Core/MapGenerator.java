package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

public class MapGenerator {

    public static final long SEED = 4564645;
    public static final Random RANDOM = new Random(SEED);

    public static int[][] overRoom = new int[EmptyMap.WIDTH][EmptyMap.HEIGHT];

    private static class Position {
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
     * fill map with random rooms
     * @param tiles
     */
    public static void fillRoom(TETile[][] tiles, int numRoomTries) {
        for (int i = 0; i < numRoomTries; i += 1) {
            int height = RANDOM.nextInt(EmptyMap.HEIGHT / 10 + 2)  + 2;
            int width = RANDOM.nextInt(EmptyMap.WIDTH / 10 + 2)  + 2;
            Position rp = randomPos(EmptyMap.WIDTH - width, EmptyMap.HEIGHT - height);
            tiles = generateRoom(tiles,rp,width,height);
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
    public static TETile[][] generateRoom(TETile[][] tiles, Position position, int width, int height) {
            TETile[][] backup = TETile.copyOf(tiles);
            int[][] roombackup = overRoom.clone();
            for (int x = position.x; x < position.x + width; x += 1){
                for (int y = position.y; y < position.y + height; y += 1){
                    if (overRoom[x][y] == 1) {
                        overRoom = roombackup;
                        return backup;
                    }
                    tiles[x][y] = Tileset.WALL;
                    overRoom[x][y] = 1;
                }
            }

            return tiles;
    }

    public static void generate(TETile[][] tiles) {
        fillRoom(tiles,100);
    }
}
