package game;

import java.util.*;

public class TicTacToeBoard implements Board {
    private static final Map<Cell, String> SYMBOLS = Map.of(
            Cell.X, " X ",
            Cell.O, " O ",
            Cell.E, " . ",
            Cell.D, " # "
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
        this.k = k;
        empty = n * m;
        cells = new Cell[n][m];
        for (Cell[] row : cells) {
            Arrays.fill(row, Cell.E);
        }
        turn = Cell.X;
    }

    public TicTacToeBoard(int d, int k) {
        n = d;
        m = d;
        this.k = k;
        empty = 0;
        cells = new Cell[d][d];
        for (int i = 0; i < d; i++) {
            for (int j = 0; j < d; j++) {
                cells[i][j] = Cell.D;
                if (isInCircle(i, j)) {
                    cells[i][j] = Cell.E;
                    empty++;
                }
            }
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
        boolean extraMove = false;

        int[][] counters = getSeqLengths(i, j);

        for (int dirI = -1; dirI < 1; dirI++) {
            for (int dirJ = -1; dirJ < 2; dirJ++) {
                // Выбираем только верхние и правую клетки, чтобы не проходить дважды
                if (dirI < dirJ || dirI == -1) {
                    int firstSeq = counters[dirI + 1][dirJ + 1];
                    int secondSeq = counters[-dirI + 1][-dirJ + 1];

                    if (firstSeq < 4 && secondSeq < 4 && firstSeq + secondSeq + 1 >= 4) {
                        extraMove = true;
                    }
                    if (firstSeq + secondSeq + 1 >= k) {
                        return Result.WIN;
                    }
                }
            }
        }

        if (empty == 0) {
            return Result.DRAW;
        }

        if (extraMove) {
            return Result.EXTRA_MOVE;
        }
        turn = turn == Cell.X ? Cell.O : Cell.X;
        return Result.UNKNOWN;
    }

    private int[][] getSeqLengths(int i, int j) {
        int[][] counters = new int[3][3];
        for (int dirI = -1; dirI < 2; dirI++) {
            for (int dirJ = -1; dirJ < 2; dirJ++) {
                if (dirI != dirJ || dirI != 0) {
                    counters[dirI + 1][dirJ + 1] = getSeqLength(i, j, dirI, dirJ);
                }
            }
        }
        return counters;
    }

    private int getSeqLength(int i, int j, int dirI, int dirJ) {
        int count = 1;
        while (isPartOfSequence(i + count * dirI, j + count * dirJ)) {
            count++;
        }
        return count - 1;
    }

    private boolean isPartOfSequence(int i, int j) {
        return isValidCell(i, j) && getCell(i, j) == getTurn();
    }

    private boolean isInCircle(int i, int j) {
        float center = (float) (n - 1) / 2;
        return (center - i) * (center - i) + (center - j) * (center - j) < n * (n - 1) / 4;
    }

    private boolean isValidCell(int i, int j) {
        return 0 <= i && i < n && 0 <= j && j < m && cells[i][j] != Cell.D;
    }

    public boolean isValid(Move move) {
        return
                isValidCell(move.getRow(), move.getCol()) &&
                        cells[move.getRow()][move.getCol()] == Cell.E &&
                        move.getCell() == turn;
    }

    @Override
    public void clear() {
        empty = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (cells[i][j] != Cell.D) {
                    cells[i][j] = Cell.E;
                    empty++;
                }
            }
        }
        turn = Cell.X;
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
}
