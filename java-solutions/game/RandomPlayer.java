package game;

import java.util.Random;

public class RandomPlayer implements Player {
    private final Random random;
    private final String name;

    public RandomPlayer(final Random random, String name) {
        this.random = random;
        this.name = name;
    }

    public RandomPlayer(String name) {
        this(new Random(), name);
    }

    @Override
    public Move move(final Position position, final Cell cell) {
        while (true) {
            int r = random.nextInt(position.getNumRows());
            int c = random.nextInt(position.getNumCols());
            final Move move = new Move(r, c, cell);
            if (position.isValid(move)) {
                return move;
            }
        }
    }

    @Override
    public String getName() {
        return name;
    }
}
