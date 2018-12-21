package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static final int WIDTH = 40;
    private static final int HEIGHT = 40;

    private static class Position {
        private int x;
        private int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    /**
     * Computesrelative x coordinate of the leftmost tile in the ith
     * row of a hexagon, assuming that the bottom row has an x-coordinate
     * of zero.
     * @param s size of the hexagon
     * @param i row num of the hexagon, where i = 0 is the bottom
     * @return
     */
    public static int hexrowoffx(int s, int i) {
        if (i >= s) {
            return i - 2 * s + 1;
        }
        return -i;
    }

    /**
     * Computes the width of row i for a size s hexagon.
     *@param s size of the hexagon
     *@param i row num of the hexagon, where i = 0 is the bottom
     *@return
     */
    public static int hexrowWidth(int s, int i) {
        int effectiveI = i;
        if (i >= s) {
            effectiveI = 2 * s - 1 - effectiveI;
        }

        return s + 2 * effectiveI;
    }

    /**
     * Adds a row of the same tile.
     * @param world the world to draw on
     * @param p the leftmost position of the row
     * @param width the number of tiles wide to draw
     * @param t the tile to draw
     */
    public static void addrow(TETile[][] world, Position p, int width, TETile t) {
        for (int xi = 0;xi < width;xi += 1) {
            world[p.x + xi][p.y] = t;
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

    /** Adds a hexagon to the world.
     * @param world the world to draw on
     * @param p the bottom left coordinate of the hexagon
     * @param s the size of the hexagon
     * @param t the tile to draw
     */
    public static void addHexagon(TETile[][] world, Position p, int s, TETile t) {
        if (s < 2) {
            throw new IllegalArgumentException("Hexagon must be at least size 2.");
        }

        //total 2*s rows
        for (int yi = 0;yi < 2 * s; yi += 1) {
            int thisrowy = p.y + yi;

            int thisrowx = p.x + hexrowoffx(s, yi);
            Position rowStartP = new Position(thisrowx, thisrowy);

            int rowWidth = hexrowWidth(s, yi);

            addrow(world, rowStartP,rowWidth,t);
        }
    }


    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        TETile[][] tiles = new TETile[WIDTH][HEIGHT];
        fillWithEmpty(tiles);

        Position p = new Position(20, 20);
        addHexagon(tiles, p, 5,Tileset.FLOWER);

        ter.renderFrame(tiles);
    }
}
