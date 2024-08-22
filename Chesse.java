import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ruzicka
 * @since 2024-08-22
 */
public class Chesse {

    static int size_board = 6;
    static int finalMoves = (size_board * size_board) - 1;
    public static List<Moves.ResultPair> moveHistory = new ArrayList<>();
    public static int countOfMoves = 0;

    public void init() {
        Map<String, int[]> board = new HashMap<>();
        for (int i = 0; i < size_board; i++) {
            for (int j = 0; j < size_board; j++) {
                String key = "(" + i + "," + j + ")";
                board.put(key, new int[]{i, j});
            }
        }
        /*for (Map.Entry<String, int[]> entry : board.entrySet()) {
            System.out.println(entry.getKey() + " = " + Arrays.toString(entry.getValue()));
        }*/

        int[] start_horse = board.get("(0,0)");
        Moves.AllPossibleMoves(start_horse);
    }

    //--------------------------------------------------------------------------------


    public static Moves.ResultPair choosePossibleMove(){
        countOfMoves++;
        if (!Moves.validMoves.isEmpty()) {
            Moves.ResultPair horsePosition = Moves.validMoves.remove(0);
            moveHistory.add(horsePosition);
            return horsePosition;
        }
        return null;
    }

    public void run() {
        while (countOfMoves < finalMoves && !Moves.validMoves.isEmpty()) {
            Moves.ResultPair move = choosePossibleMove();
            if (move == null) break;

            System.out.println("Move : " + move);
            System.out.println("ount : " + countOfMoves);

            Moves.AllPossibleMoves(new int[]{move.getX(), move.getY()});
        }
    }

    //--------------------------------------------------------------------------------

    public static void main(String[] args) {
        Chesse chesse = new Chesse();
        chesse.init();
        chesse.run();
    }
}
