package game;

public class Game {
    private final boolean log;
    private final Player player1, player2;

    public Game(final boolean log, final Player player1, final Player player2) {
        this.log = log;
        this.player1 = player1;
        this.player2 = player2;
    }

    public int play(Board board) {
        while (true) {
            final int result1 = move(board, player1, 1);
            if (result1 != -1) {
                return result1;
            }

            final int result2 = move(board, player2, 2);
            if (result2 != -1) {
                return result2;
            }
        }
    }

    private int move(final Board board, final Player player, final int no) {
        Result result;
        try {
            Move move = player.move(board.getPosition(), board.getTurn());
            result = board.makeMove(move);
            log("Player " + no + " move: " + move);
        } catch (Exception e) {
            result = Result.LOSE;
            log("Player " + no + " moves invalid move. He's losing.");
        }
        log("Position:\n" + board);

        switch (result) {
            case WIN: {
                log("Player " + no + " won");
                return no;
            }
            case LOSE: {
                log("Player " + no + " lose");
                return 3 - no;
            }
            case DRAW: {
                log("Draw");
                return 0;
            }
            default: {
                return -1;
            }
        }
    }

    private void log(final String message) {
        if (log) {
            System.out.println(message);
        }
    }
}
