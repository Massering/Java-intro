package game;

import util.IntList;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

import static game.Util.getInts;

public class HumanPlayer implements Player {
    private final PrintStream out;
    private final Scanner in;

    public HumanPlayer(final PrintStream out, final Scanner in) {
        this.out = out;
        this.in = in;
    }

    public HumanPlayer() {
        this(System.out, new Scanner(System.in));
    }

    @Override
    public Move move(final Position position, final Cell cell) {
        while (true) {
            out.println("Position:");
            out.println(position);
            out.println(cell + "'s move");
            out.println("Enter row and column");

            IntList arr = getInts(in, 2, List.of("row number (i)", "column number (j)"),
                    new IntList(new int[]{1, 1}), new IntList(List.of(position.getNumRows(), position.getNumCols())));
            int row = arr.get(0) - 1;
            int col = arr.get(1) - 1;

            final Move move = new Move(row, col, cell);

            if (position.isValid(move)) {
                return move;
            }
            out.println();
            out.println("Wrong move. Selected cell wasn't empty. Try again:");
        }
    }
}
