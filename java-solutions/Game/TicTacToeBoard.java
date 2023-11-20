package Game;

import java.util.Arrays;
import java.util.Map;

public class TicTacToeBoard implements Board {
    private static final Map<Cell, Character> SYMBOLS = Map.of(
            Cell.X, 'X',
            Cell.O, 'O',
            Cell.E, '.'
    );

    private final int n;
    private final int m;
    private final int k;
    private int empty;
    private final Cell[][] cells;
    private Cell turn;

    public TicTacToeBoard(int n, int m, int k) {
        this.n = n;
        this.m = m;
        empty = n * m;
        this.k = k;
        cells = new Cell[n][m];
        for (Cell[] row : cells) {
            Arrays.fill(row, Cell.E);
        }
        turn = Cell.X;
    }

    @Override
    public Result makeMove(final Move move) {
        if (!isValid(move)) {
            return Result.LOSE;
        }
        int i = move.getRow();
        int j = move.getCol();
        cells[i][j] = move.getCell();
        empty -= 1;

        int countHorisontal = 0, countVertical = 0, countDiagonal1 = 0, countDiagonal2 = 0;
        for (int offset = -k + 1; offset < k; offset++) {
            if (isValidCell(i + offset, j)) {
                countHorisontal = turn == cells[i + offset][j] ? countHorisontal + 1 : 0;
            }
            if (isValidCell(i, j + offset)) {
                countVertical = turn == cells[i][j + offset] ? countVertical + 1 : 0;
            }
            if (isValidCell(i + offset, j + offset)) {
                countDiagonal1 = turn == cells[i + offset][j + offset] ? countDiagonal1 + 1 : 0;
            }
            if (isValidCell(i - offset, j + offset)) {
                countDiagonal2 = turn == cells[i - offset][j + offset] ? countDiagonal2 + 1 : 0;
            }

            if (Math.max(Math.max(countHorisontal, countVertical), Math.max(countDiagonal1, countDiagonal2)) == k) {
                return Result.WIN;
            }
        }

        turn = turn == Cell.X ? Cell.O : Cell.X;

        if (empty == 0) {
            return Result.DRAW;
        }
        return Result.UNKNOWN;
    }

    @Override
    public Position getPosition() {
        return new TicTacToePosition(this);
    }

    @Override
    public Cell getCell(final int r, final int c) {
        return cells[r][c];
    }

    @Override
    public Cell getTurn() {
        return turn;
    }

    @Override
    public int getNumRows() {
        return n;
    }

    @Override
    public int getNumCols() {
        return m;
    }

    @Override
    public int getK() {
        return k;
    }

    private boolean isValidCell(int i, int j) {
        return 0 <= i && i < n && 0 <= j && j < m;
    }

    public boolean isValid(Move move) {
        return
                isValidCell(move.getRow(), move.getCol()) &&
                cells[move.getRow()][move.getCol()] == Cell.E &&
                move.getCell() == turn;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        for (int r = 0; r < n; r++) {
            if (r != 0) {
                sb.append("\n");
            }
            for (int c = 0; c < m; c++) {
                sb.append(SYMBOLS.get(cells[r][c]));
            }
        }
        return sb.toString();
    }
}
