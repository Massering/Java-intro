package Game;

public class TicTacToePosition implements Position {
    private final TicTacToeBoard board;

    public TicTacToePosition(TicTacToeBoard board) {
        this.board = board;
    }

    @Override
    public boolean isValid(Move move) {
        return board.isValid(move);
    }

    @Override
    public Cell getCell(final int r, final int c) {
        return board.getCell(r, c);
    }

    @Override
    public int getNumRows() {
        return board.getNumRows();
    }

    @Override
    public int getNumCols() {
        return board.getNumCols();
    }

    @Override
    public int getK() {
        return board.getK();
    }

    @Override
    public String toString() {
        return board.toString();
    }
}
