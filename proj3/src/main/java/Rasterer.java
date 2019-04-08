import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * This class provides all code necessary to take a query box and produce
 * a query result. The getMapRaster method must return a Map containing all
 * seven of the required fields, otherwise the front end code will probably
 * not draw the output correctly.
 */
public class Rasterer {
    private double lrlon;
    private double ullon;
    private double ullat;
    private double lrlat;

    public Rasterer() {
        // YOUR CODE HERE
        lrlon = 0;
        ullon = 0;
        ullat = 0;
        lrlat = 0;
    }

    /**
     * Takes a user query and finds the grid of images that best matches the query. These
     * images will be combined into one big image (rastered) by the front end. <br>
     *
     *     The grid of images must obey the following properties, where image in the
     *     grid is referred to as a "tile".
     *     <ul>
     *         <li>The tiles collected must cover the most longitudinal distance per pixel
     *         (LonDPP) possible, while still covering less than or equal to the amount of
     *         longitudinal distance per pixel in the query box for the user viewport size. </li>
     *         <li>Contains all tiles that intersect the query bounding box that fulfill the
     *         above condition.</li>
     *         <li>The tiles must be arranged in-order to reconstruct the full image.</li>
     *     </ul>
     *
     * @param params Map of the HTTP GET request's query parameters - the query box and
     *               the user viewport width and height.
     *
     * @return A map of results for the front end as specified: <br>
     * "render_grid"   : String[][], the files to display. <br>
     * "raster_ul_lon" : Number, the bounding upper left longitude of the rastered image. <br>
     * "raster_ul_lat" : Number, the bounding upper left latitude of the rastered image. <br>
     * "raster_lr_lon" : Number, the bounding lower right longitude of the rastered image. <br>
     * "raster_lr_lat" : Number, the bounding lower right latitude of the rastered image. <br>
     * "depth"         : Number, the depth of the nodes of the rastered image <br>
     * "query_success" : Boolean, whether the query was able to successfully complete; don't
     *                    forget to set this to true on success! <br>
     */
    public Map<String, Object> getMapRaster(Map<String, Double> params) {
        lrlon = params.get("lrlon");
        ullon = params.get("ullon");
        ullat = params.get("ullat");
        lrlat = params.get("lrlat");
        double width = params.get("w");
        double height = params.get("h");

        Map<String, Object> results = new HashMap<>();
        if (lrlon <= MapServer.ROOT_ULLON || lrlat >= MapServer.ROOT_ULLAT
            || ullon >= MapServer.ROOT_LRLON || ullat <= MapServer.ROOT_LRLAT
            || ullon >= lrlon || ullat <= lrlat) {
            setFalse(results);
            return results;
        }

        int depth = fileDepth(ullon, lrlon, width);
        double perw = (MapServer.ROOT_LRLON - MapServer.ROOT_ULLON) / (Math.pow(2, depth));
        double perh = (MapServer.ROOT_ULLAT - MapServer.ROOT_LRLAT) / (Math.pow(2, depth));

        if (ullon < MapServer.ROOT_ULLON) {
            ullon = MapServer.ROOT_ULLON;
        }

        if (lrlon > MapServer.ROOT_LRLON) {
            lrlon = MapServer.ROOT_LRLON;
        }

        if (ullat > MapServer.ROOT_ULLAT) {
            ullat = MapServer.ROOT_ULLAT;
        }

        if (lrlat < MapServer.ROOT_LRLAT) {
            lrlat = MapServer.ROOT_LRLAT;
        }

        int sx = (int) Math.floor((ullon - MapServer.ROOT_ULLON) / perw);
        int sy = (int) Math.floor((MapServer.ROOT_ULLAT - ullat) / perh);

        double lonDist = lrlon - ((perw * (double) (sx + 1)) + MapServer.ROOT_ULLON);
        double latDist = (MapServer.ROOT_ULLAT - (perh * (double) (sy + 1)) - lrlat);

        int tx = (int) Math.ceil(lonDist / perw) + sx;
        int ty = (int) Math.ceil(latDist / perh) + sy;


        //write result
        results.put("render_grid", genString(depth, sx, sy, tx, ty));
        results.put("raster_ul_lon", sx * perw + MapServer.ROOT_ULLON);
        results.put("raster_ul_lat", MapServer.ROOT_ULLAT - sy * perh);
        results.put("raster_lr_lon", (tx + 1) * perw + MapServer.ROOT_ULLON);
        results.put("raster_lr_lat", MapServer.ROOT_ULLAT - (ty + 1) * perh);
        results.put("depth", depth);
        results.put("query_success", true);
        //System.out.println(results);
        return results;
    }

    private int fileDepth(double ullon, double lrlon, double w) {
        double originDPP = (MapServer.ROOT_LRLON - MapServer.ROOT_ULLON) / MapServer.TILE_SIZE;
        double lonDPP = (lrlon - ullon) / w;
        int depth = 0;
        while (originDPP > lonDPP) {
            depth += 1;
            originDPP = originDPP / 2.0;
        }
        if (depth > 7) {
            return 7;
        }
        return depth;
    }

    private String[][] genString(int d, int sx, int sy, int tx, int ty) {
        int xi = tx - sx + 1;
        int yi = ty - sy + 1;
        String[][] res = new String[yi][xi];
        for (int i = 0; i < yi; i++) {
            for (int j = 0; j < xi; j++) {
                res[i][j] = "d" + d + "_x" + (sx + j) +"_y" + (sy + i) +".png";
            }
        }
        return res;
    }

    private void setFalse(Map<String, Object> results) {
        results.put("raster_ul_lon", Math.random());
        results.put("raster_ul_lat", Math.random());
        results.put("raster_lr_lon", Math.random());
        results.put("raster_lr_lat", Math.random());
        results.put("depth", Math.random());
        results.put("query_success", false);
        results.put("render_grid", Math.random());
    }
}