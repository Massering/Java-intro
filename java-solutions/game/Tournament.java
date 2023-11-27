package game;

import java.util.*;

import static game.Util.binPow;
import static game.Util.log2;

public class Tournament {
    private List<Player> players;
    private final boolean log;

    public Tournament(boolean log, List<Player> players) {
        this.log = log;
        this.players = players;
    }

    public Map<Integer, List<Player>> play(Board board) {
        Map<Integer, List<Player>> rating = new HashMap<>();

        while (players.size() > 1) {
            myShuffle(players);
            List<Player> winners = new ArrayList<>();
            List<Player> losers = new ArrayList<>();
            for (int i = 0; i < players.size(); i += 2) {
                Player player1 = players.get(i);
                Player player2 = players.get(i + 1);

                int result;
                if (player1 == null) {
                    result = 2;
                } else if (player2 == null) {
                    result = 1;
                } else {
                    final Game game = new Game(log, player1, player2);
                    do {
                        board.clear();
                        result = game.play(board);
                    } while (result == 0);
                }

                winners.add(result == 1 ? player1 : player2);
                Player loser = result == 1 ? player2 : player1;
                if (loser != null) {
                    losers.add(loser);
                }
            }
            players = winners;
            rating.put(players.size() + 1, losers);
        }
        rating.put(1, players);

        return rating;
    }

    private static void myShuffle(List<Player> players) {
        Random random = new Random();
        Collections.shuffle(players, random);

        int binPowPlayersNumber = binPow(2, (int) Math.ceil(log2(players.size())));
        int size = players.size();
        while (players.size() < binPowPlayersNumber) {
            players.add(size--, null);
        }
    }

    private void log(final String message) {
        if (log) {
            System.out.println(message);
        }
    }
}
