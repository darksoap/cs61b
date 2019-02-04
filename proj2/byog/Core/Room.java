package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

public class Room {
    /**
     * 生成一个房间，如果重叠则返回原数组
     * @param tiles
     * @param p
     * @param width
     * @param height
     */
    public static void generateRoom(TETile[][] tiles, Position p, int width, int height) {
        for (int x = 0; x <= width; x += 1) {
            for (int y = 0; y <= height; y += 1) {
                if (tiles[p.x + x][p.y + y] != Tileset.NOTHING) {
                    return;
                }
            }
        }

        roomWall(tiles, p, width, height);
        roomFloor(tiles, new Position(p.x + 1, p.y + 1), width -1 , height - 1);
    }

    public static void roomWall(TETile[][] tiles, Position p, int width, int height) {
        tilesLine(tiles, p, Tileset.WALL, width, 0);
        tilesLine(tiles, p, Tileset.WALL, height, 1);

        Position tmp = new Position(p.x, p.y + height);
        tilesLine(tiles, tmp, Tileset.WALL, width + 1, 0);

        tmp = new Position(p.x + width, p.y);
        tilesLine(tiles, tmp, Tileset.WALL, height, 1);
    }

    public static void roomFloor(TETile[][] tiles, Position p, int width, int height) {
        for (int i = 0; i < height; i += 1) {
            tilesLine(tiles, new Position(p.x, p.y + i), Tileset.FLOOR, width, 0);
        }
    }

    /**
     * 填充单列s长度的x或y
     * @param tiles
     * @param p 起点
     * @param s
     * @param xOry 0为x，1为y
     */
    private static void tilesLine(TETile[][] tiles, Position p, TETile t, int s, int xOry) {
        if (xOry == 0) {
            for (int i = 0; i < s; i += 1) {
                tiles[p.x + i][p.y] = t;
            }
        }

        if (xOry == 1) {
            for (int i = 0; i < s; i += 1) {
                tiles[p.x][p.y + i] = t;
            }
        }
    }
}
