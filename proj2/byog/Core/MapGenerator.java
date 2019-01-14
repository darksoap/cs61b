package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

public class MapGenerator extends BasicMap {

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
