package Game;

public interface Position {
    boolean isValid(Move move);

    Cell getCell(int r, int c);

    int getNumRows();

    int getNumCols();

    int getK();

    String toString();
}
