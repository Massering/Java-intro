package game;

import util.IntList;

import java.util.*;

import static game.Util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        IntList list = getInts(scanner, 1, List.of("is field circled (1 or 0)"), 0, 1);
        Board board;
        if (list.get(0) == 1) {
            list = getInts(scanner, 2, List.of("diameter", "K"));
            board = new TicTacToeBoard(list.get(0), list.get(1));
        } else {
            list = getInts(scanner, 3, List.of("height", "width", "K"));
            board = new TicTacToeBoard(list.get(0), list.get(1), list.get(2));
        }

        int playersNumber = getInts(scanner, 1,
                List.of("number of RandomPlayer participants"), 0, Integer.MAX_VALUE).get(0);
        List<Player> players = new ArrayList<>(playersNumber);
        for (int i = 1; i <= playersNumber; i++) {
            players.add(new RandomPlayer( "RandomPlayer №" + i));
        }

        int humanPlayersNumber = getInts(scanner, 1,
                List.of("number of HumanPlayer participants"), 0, Integer.MAX_VALUE).get(0);
        for (int i = 1; i <= humanPlayersNumber; i++) {
            players.add(new HumanPlayer(getString(scanner, "name of HumanPlayer №" + i)));
        }

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
                    final Game game = new Game(false, player1, player2);
                    do {
                        result = game.play(board);
                        board.clear();
                    } while (result == 0);
                }

                winners.add(result == 1 ? player1 : player2);
                Player loser = result == 1 ? player2 : player1;
                if (loser != null) {
                    losers.add(loser);
                }
            }
            players = winners;
            System.out.print((players.size() + 1) + "'s place: ");
            for (var loser : losers) {
                if (loser != losers.get(0)) {
                    System.out.print(", ");
                }
                System.out.print(loser.getName());
            }
            System.out.println();
        }
        System.out.print("1's place: " + players.get(0).getName());
        System.out.println();
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
}
