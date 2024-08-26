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
    public static Set<String> visitedPositions = new HashSet<>();
    public static Map<String, List<Moves.Move>> movesMap = new HashMap<>();
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
        visitedPositions.add("(0,0)");
        List<Moves.Move> initialMoves = Moves.AllPossibleMoves(start_horse);
        movesMap.put("(0,0)", initialMoves); // valid moves for the starting position
    }

    //---choosePossibleMove-----------------------------------------------------------

    public Moves.Move choosePossibleMove(String position) {
        List<Moves.Move> validMoves = movesMap.get(position);
        if (validMoves != null) {
            for (Moves.Move move : validMoves) {
                String destPosition = "(" + move.getDestField().getX() + "," + move.getDestField().getY() + ")";
                if (!visitedPositions.contains(destPosition)) {
                    return move;
                }
            }
        }
        return null;
    }

    //---run--------------------------------------------------------------------------

    public void run() {
        String currentPosition = "(0,0)";

        while (countOfMoves < finalMoves && !movesMap.get(currentPosition).isEmpty()) {
            Moves.Move move = choosePossibleMove(currentPosition);
            if (move == null) {
                break;
            }

            moveHistory.add(move);
            String newPosition = "(" + move.getDestField().getX() + "," + move.getDestField().getY() + ")";
            visitedPositions.add(newPosition);
            countOfMoves++;

            System.out.println("Move : " + move);
            System.out.println("ount : " + countOfMoves);

            List<Moves.Move>  nextMoves = Moves.AllPossibleMoves(move.getDestField());
            movesMap.put(newPosition, nextMoves);
            currentPosition = newPosition;

            if (countOfMoves < finalMoves && nextMoves.isEmpty()) {
                visitedPositions.remove(newPosition);
                moveHistory.remove(move);
                countOfMoves--;
                currentPosition = "(" + move.getSourceField().getX() + "," + move.getSourceField().getY() + ")"; // Revert to the previous position
            }
        }
    }

    //---main-------------------------------------------------------------------------

    public static void main(String[] args) {
        Chesse chesse = new Chesse();
        chesse.init();
        chesse.run();
    }
}
