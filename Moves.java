import java.util.ArrayList;
import java.util.List;

/**
 * @author ruzicka
 * @since 2024-08-22
 */
public class Moves {
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

    public static List<ResultPair> validMoves = new ArrayList<>();

    public static void AllPossibleMoves(int[] startHorse) {
        validMoves.clear();
        for (Direction direction : Direction.values()) {
            ResultPair result = direction.apply(startHorse[0], startHorse[1]);
            if (result.getX() < 0 || result.getY() < 0 || result.getX() >= Chesse.size_board || result.getY() >= Chesse.size_board) {
                continue;
            }
            System.out.println(direction + ": " + result);
            validMoves.add(result);
        }
    }
}
