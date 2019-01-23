package byog.lab5;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static final int WIDTH = 50;
    private static final int HEIGHT = 50;

    private static final long SEED = 2873123;
    private static final Random RANDOM = new Random(SEED);

    private static class Position {
        private int x;
        private int y;

        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void fillWithEmpty(TETile[][] tiles) {
        int height = tiles[0].length;
        int width = tiles.length;
        for (int x = 0; x < width; x += 1) {
            for (int y = 0; y < height; y += 1) {
                tiles[x][y] = Tileset.NOTHING;
            }
        }
    }

    /** Picks a RANDOM tile with a 33% change of being
     *  a wall, 33% chance of being a flower, and 33%
     *  chance of being empty space.
     */
    private static TETile randomTile() {
        int tileNum = RANDOM.nextInt(5);
        switch (tileNum) {
            case 0: return Tileset.TREE;
            case 1: return Tileset.FLOWER;
            case 2: return Tileset.GRASS;
            case 3: return Tileset.WATER;
            case 4: return Tileset.MOUNTAIN;
            default: return Tileset.SAND;
        }
    }

    //六边形模块
    public static void addHexagon(TETile[][] tiles, Position outset, int size) {
        TETile tile = randomTile();

        for (int i = 0; i < 2 * size; i += 1) {
            int width = rowWidth(i, size);
            setRow(rowPos(i, size, outset), width, tiles, tile);
        }
    }

    public static int rowWidth(int i, int s) {
        int effectiveI = i;
        if (i >= s) {
            effectiveI = 2 * s - 1 - effectiveI;
        }

        return s + 2 * effectiveI;
    }

    public static void setRow(Position p, int width, TETile[][] tiles, TETile tile) {
        for (int i = 0; i < width; i += 1) {
            tiles[p.x + i][p.y] = tile;
        }
    }

    public static Position rowPos(int i, int size, Position outset) {
        if (i >= size) {
            return new Position((outset.x - 2 * size + 2 + i), (outset.y + i - 1));
        }
        return new Position((outset.x - i + 1), (outset.y + i - 1));
    }


    //以下为矩阵模块
    //单列六边形
    public static void hexagonLine(TETile[][] tiles, Position outset, int n, int size) {
        for (int i = 0; i < n; i += 1) {
            addHexagon(tiles, outset, size);
            outset.y += 2 * size;
        }
    }

    //生成阵列
    public static void tesselate(TETile[][] tiles, Position outset, int size) {
        int n = 3;
        for (int i = 0; i < 3; i += 1) {
            hexagonLine(tiles, linePos(outset, i, size), n, size);
            n += 1;
        }
        hexagonLine(tiles, linePos(outset, 3, size), 4, size);
        hexagonLine(tiles, linePos(outset, 4, size), 3, size);
    }

    //单列起点
    public static Position linePos(Position p, int i, int size) {
        int x = p.x + i * (2 * size - 1);
        if (i <= 2) {
            int y = p.y - i * size;
            return new Position(x, y);
        }
        int y = p.y - (4 - i) * size;
        return new Position(x, y);
    }

    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        TETile[][] world = new TETile[WIDTH][HEIGHT];

        fillWithEmpty(world);
        tesselate(world, new Position(6, 10), 4);

        ter.renderFrame(world);
    }
}
