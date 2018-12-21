package byog.Core;

import byog.TileEngine.TERenderer;
import byog.Core.MapGenerator;
import byog.TileEngine.TETile;

public class MapVisualTest {
    public static final TERenderer ter = new TERenderer();

    public static void main(String[] args) {
        TETile[][] world = EmptyMap.emptymap();
        MapGenerator.generate(world);
        ter.initialize(EmptyMap.WIDTH, EmptyMap.HEIGHT);
        ter.renderFrame(world);
    }
}
