import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ruzicka
 * @since 2024-08-22
 */
public class Chesse {

    //--------------------------------------------------------------------------------

    static int size_board = 6;
    static int final_moves = ((size_board * size_board) - 1);

    //--------------------------------------------------------------------------------

    public static void init() {

        //System.out.println("final_moves: " + final_moves);

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

    static int countOfMoves = 0;

    public static Moves.ResultPair choosePossibleMove(){
        countOfMoves++;
        Moves.ResultPair horsePosition = Moves.validMoves.getFirst();
        return horsePosition;
    }

    //--------------------------------------------------------------------------------

    public static void saveMoves(){
        List<Moves.ResultPair> savedMoves = new ArrayList<>();
        savedMoves.add(Moves.validMoves.getFirst());
        Moves.validMoves.removeFirst();
    }

    //--------------------------------------------------------------------------------

    public static void run(){
        while (countOfMoves < final_moves){
            choosePossibleMove();
            saveMoves();
            //System.out.println("position: " + Moves.ResultPair.toString());
        }
    }

    //--------------------------------------------------------------------------------

    public static void main(String[] args) {
        init();
        run();

    }
}
