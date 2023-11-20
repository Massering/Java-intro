package Game;

public final class Move {
    private final int row;
    private final int column;
    private final Cell value;

    public Move(final int row, final int column, final Cell value) {
        this.row = row;
        this.column = column;
        this.value = value;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return column;
    }

    public Cell getCell() {
        return value;
    }

    @Override
    public String toString() {
        return "row=" + row + ", column=" + column + ", value=" + value;
    }
}
