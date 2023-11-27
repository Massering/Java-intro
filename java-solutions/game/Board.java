package game;

public interface Board {
    void clear();

    Position getPosition();

    Cell getCell(int r, int c);

    Cell getTurn();
    Result makeMove(Move move);

    int getNumRows();

    int getNumCols();

    int getK();
}
