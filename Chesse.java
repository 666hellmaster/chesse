import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;


/**
 * @author ruzicka
 * @since 2024-08-22
 */
public class Chesse {

    //--------------------------------------------------------------------------------

    static int size_board = 6; //change for different board sizes. it is n x n  (sizes under 5 have no solution)
    static int finalMoves = (size_board * size_board) - 1;
    public static Set<String> visitedPositions = new HashSet<>();
    public static Map<String, List<Moves.Move>> movesMap = new HashMap<>();
    public static int countOfMoves = 0;
    public Stack<String> stack = new Stack<>(); // stack of visited positions
    public String start = "(0,0)"; // starting position for the horse

    //---init-------------------------------------------------------------------------

    public void init() { // initialize the board and the starting position of the horse
        Map<String, Moves.Field> board = new HashMap<>();
        for (int i = 0; i < size_board; i++) {
            for (int j = 0; j < size_board; j++) {
                String key = "(" + i + "," + j + ")";
                board.put(key, new Moves.Field(i, j));
            }
        }

        Moves.Field start_horse = board.get(start);
        visitedPositions.add(start);
        stack.push(start); // push the starting position to the stack
        List<Moves.Move> initialMoves = Moves.AllPossibleMoves(start_horse);
        movesMap.put(start, initialMoves); // valid moves for the starting position
    }

    //---choosePossibleMove-----------------------------------------------------------

    public Moves.Move choosePossibleMove(String position) {
        List<Moves.Move> validMoves = movesMap.get(position);
        if (validMoves != null && !validMoves.isEmpty()) {
                    return validMoves.get(0);
            }
        return null;
    }

    //---run--------------------------------------------------------------------------

    public void run() {
        String currentPosition = stack.peek();
        while (countOfMoves < finalMoves){ // while the horse has not visited all the fields
            Moves.Move move = choosePossibleMove(currentPosition);
            if (move == null) { // if there are no more valid moves (dead end)
                backtrack();
                if (stack.isEmpty()) { // if the horse has visited all the fields and there are no more moves
                    break;
                }
                currentPosition = stack.peek();
                continue;
            }

            String newPosition = "(" + move.getDestField().getX() + "," + move.getDestField().getY() + ")";
            visitedPositions.add(newPosition);
            stack.push(newPosition);
            countOfMoves++;

            //uncomment for printing out the progress
            // System.out.println("Move : " + move); // move from the current position to the destination
            // System.out.println("Count : " + countOfMoves); // number of moves
            // System.out.println("Stack : " + stack); //the path of the horse

            List<Moves.Move>  nextMoves = Moves.AllPossibleMoves(move.getDestField());
            movesMap.put(newPosition, nextMoves);
            currentPosition = newPosition;
        }

        if (countOfMoves == finalMoves) {
            System.out.println("The horse has visited all the fields.");
            System.out.println("Stack : " + stack); //prints the path of the horse
        } else {
            System.out.println("There is no valid solution (maybe try differernt board size).");
        }
    }

    //---backtrack--------------------------------------------------------------------

    public void backtrack(){ // backtracking using stack of used moves
       if (!stack.isEmpty()) {
              String lastPosition = stack.pop();
              visitedPositions.remove(lastPosition);
              countOfMoves--;
              if (!stack.isEmpty()) {
                  String previousPosition = stack.peek();
                  movesMap.get(previousPosition).remove(choosePossibleMove(previousPosition));
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
