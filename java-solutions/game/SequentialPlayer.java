package game;

public class SequentialPlayer implements Player {
    private final String name;

    public SequentialPlayer(String name) {
        this.name = name;
    }

    @Override
    public Move move(final Position position, final Cell cell) {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                final Move move = new Move(r, c, cell);
                if (position.isValid(move)) {
                    return move;
                }
            }
        }
        throw new IllegalStateException("No valid moves");
    }

    @Override
    public String getName() {
        return name;
    }
}
