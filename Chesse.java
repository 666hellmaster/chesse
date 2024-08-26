import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author ruzicka
 * @since 2024-08-22
 */
public class Chesse {

    //--------------------------------------------------------------------------------

    static int size_board = 6;
    static int finalMoves = (size_board * size_board) - 1;
    public static List<Moves.Move> moveHistory = new ArrayList<>();
    public static int countOfMoves = 0;

    //---init-------------------------------------------------------------------------

    public void init() {
        Map<String, Moves.Field> board = new HashMap<>();
        for (int i = 0; i < size_board; i++) {
            for (int j = 0; j < size_board; j++) {
                String key = "(" + i + "," + j + ")";
                board.put(key, new Moves.Field(i, j));
            }
        }

        Moves.Field start_horse = board.get("(0,0)");
        Moves.AllPossibleMoves(start_horse);
    }

    //---choosePossibleMove-----------------------------------------------------------


    public static Moves.Move choosePossibleMove(){
        for (Moves.Move move : Moves.validMoves){
            if (!moveHistory.contains(move)){
               return move;
            }
        }
        return null;
    }

    //---run--------------------------------------------------------------------------

    public void run() {

        while (countOfMoves < finalMoves && !Moves.validMoves.isEmpty()) {
            Moves.Move move = choosePossibleMove();
            if (move == null){
                break;
            }

            moveHistory.add(move);
            countOfMoves++;

            System.out.println("Move : " + move);
            System.out.println("ount : " + countOfMoves);
            Moves.AllPossibleMoves(move.getDestField());
        }
    }

    //---main-------------------------------------------------------------------------

    public static void main(String[] args) {
        Chesse chesse = new Chesse();
        chesse.init();
        chesse.run();
    }
}
