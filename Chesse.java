import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ruzicka
 * @since 2024-08-22
 */
public class Chesse {

    //--------------------------------------------------------------------------------

    public static void init() {
        int size_board = 6;
        int final_moves = ((size_board * size_board) - 1);

        System.out.println("final_moves: " + final_moves);

        Map<String, int[]> board = new HashMap<>();

        for (int i = 0; i < size_board; i++) {
            for (int j = 0; j < size_board; j++) {
                String key = "(" + i + "," + j + ")";
                board.put(key, new int[]{i, j});
            }
        }
        for (Map.Entry<String, int[]> entry : board.entrySet()) {
            System.out.println(entry.getKey() + " = " + Arrays.toString(entry.getValue()));
        }

        int[] start_horse = board.get("(0,0)");
    }

    //--------------------------------------------------------------------------------

    public static class ResultPair {
        private final int x;
        private final int y;

        public ResultPair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        @Override
        public String toString() {
            return "(" + x + ", " + y + ")";
        }
    }

    //--------------------------------------------------------------------------------

    public static void movexmore(int[] start, int[] end) {
        int x = Math.abs(start[0] - end[0]);
        int y = Math.abs(start[1] - end[1]);
        System.out.println("x: " + x + " y: " + y);
    }

    public enum Direction {
        UP_LEFT {
            @Override
            public ResultPair apply(int x, int y) {
                return new ResultPair(x - 1, y + 2);
            }
        },
        UP_RIGHT {
            @Override
            public ResultPair apply(int x, int y) {
                return new ResultPair(x + 1, y + 2);
            }
        },
        RIGHT_UP {
            @Override
            public ResultPair apply(int x, int y) {
                return new ResultPair(x + 2, y + 1);
            }
        },
        RIGHT_DOWN {
            @Override
            public ResultPair apply(int x, int y) {
                return new ResultPair(x + 2, y - 1);
            }
        },
        DOWN_LEFT {
            @Override
            public ResultPair apply(int x, int y) {
                return new ResultPair(x - 1, y - 2);
            }
        },
        DOWN_RIGHT {
            @Override
            public ResultPair apply(int x, int y) {
                return new ResultPair(x + 1, y - 2);
            }
        },
        LEFT_UP {
            @Override
            public ResultPair apply(int x, int y) {
                return new ResultPair(x - 2, y + 1);
            }
        },
        LEFT_DOWN {
            @Override
            public ResultPair apply(int x, int y) {
                return new ResultPair(x - 2, y - 1);
            }
        };

        public abstract ResultPair apply(int x, int y);
    }

    //--------------------------------------------------------------------------------

    public static void main(String[] args) {
        init();
    }
}
