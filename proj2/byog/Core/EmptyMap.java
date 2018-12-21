package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

public class EmptyMap {
    static final int WIDTH = 60;
    static final int HEIGHT = 40;

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
     * Return a empty map
     * @return
     */
    public static TETile[][] emptymap(){
        TETile[][] tiles= new TETile[WIDTH][HEIGHT];
        fillWithEmpty(tiles);
        return tiles;
    }
}
