package byog.lab5;

import byog.TileEngine.TETile;
import byog.TileEngine.TERenderer;
import byog.TileEngine.Tileset;

import java.util.Random;

public class drawHexagon {
    private int N;
    private static final int WIDTH = 50;
    private static final int HEIGHT = 50;

    private static final long SEED = 28712;
    private static final Random RANDOM = new Random(SEED);

    /**
     * constructor
     * @param n
     */
    public drawHexagon(int n) {
        N = n;
    }

    /**
     * choose random tile
     * @return
     */
    private static TETile randomTile() {
        int tileNum = RANDOM.nextInt(4);
        switch (tileNum) {
            case 0: return Tileset.SAND;
            case 1: return Tileset.MOUNTAIN;
            case 2: return Tileset.FLOWER;
            case 3: return Tileset.TREE;
            default: return Tileset.NOTHING;
        }
    }

    /**
     * create a hexagon in (x, y)
     * @param world
     * @param x the x location of hexagon
     * @param y the y location of hexagon
     */
    public void addHexagon(TETile[][] world, int x, int y) {
        if (N < 2) return;
        TETile tile = randomTile();
        for (int i = 0; i < N; i++) {
            addLine(i, world, tile, x, y);
        }
        for (int i = 2 * N - 1; i >= N; i--) {
            addLine(i, world, tile, x, y);
        }

    }

    /**
     * print each line of hexagon
     * @param line
     * @param world
     * @param tile
     * @param x
     * @param y
     */
    private void addLine(int line, TETile[][] world, TETile tile, int x, int y) {
        int dif = line >= N ? 2 * N - 1 - line : line;
        for (int j = N - dif - 1; j < 2 * N - 1 + dif; j++) {
            world[j + x][line + y] = tile;
        }
    }

    /**
     * initialize the word with NOTHING tile
     * @param world
     */
    public void init(TETile[][] world) {
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                world[i][j] = Tileset.NOTHING;
            }
        }
    }

    /**
     * create 3 * 3 Hexagon world
     * @param world
     */
    public void createHexagonWorld(TETile[][] world) {
        drawLine(world, 0, 2 * N, 3);
        drawLine(world, 2 * N - 1, N, 4);
        drawLine(world,4 * N - 2, 0, 5);
        drawLine(world,6 * N - 3, N, 4);
        drawLine(world, 8* N - 4, 2 * N ,3);
    }

    /**
     * draw each line in hexagon world
     * @param world
     * @param x x position of the hexagon
     * @param y y position of the hexagon
     * @param num number of the hexagon of the line
     */
    private void drawLine(TETile[][] world, int x, int y, int num) {
        for (int i = 0; i < num; i++) {
            y = y + 2 * N;
            addHexagon(world, x, y);
        }
    }

    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);


        TETile[][] world = new TETile[WIDTH][HEIGHT];
        drawHexagon a = new drawHexagon(3);
        a.init(world);
        a.createHexagonWorld(world);

        ter.renderFrame(world);
    }
}
