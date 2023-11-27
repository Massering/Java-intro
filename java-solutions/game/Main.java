package game;

import util.IntList;

import java.util.*;

import static game.Util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Board board;
        if (getInts(scanner, 1, List.of("if field circled (1 or 0)"), 0, 1).get(0) == 1) {
            IntList numbers = getInts(scanner, 2, List.of("diameter", "K"));
            board = new TicTacToeBoard(numbers.get(0), numbers.get(1));
        } else {
            IntList numbers = getInts(scanner, 3, List.of("height", "width", "K"));
            board = new TicTacToeBoard(numbers.get(0), numbers.get(1), numbers.get(2));
        }

        if (getInts(scanner, 1, List.of("1 if play tournament else 0"), 0, 1).get(0) == 1) {
            List<Player> players = new ArrayList<>();

            int playersNumber = getInts(scanner, 1,
                    List.of("number of RandomPlayer participants"), 0, Integer.MAX_VALUE).get(0);
            for (int i = 1; i <= playersNumber; i++) {
                players.add(new RandomPlayer("RandomPlayer №" + i));
            }

            int humanPlayersNumber = getInts(scanner, 1,
                    List.of("number of HumanPlayer participants"), 1, Integer.MAX_VALUE).get(0);
            for (int i = 1; i <= humanPlayersNumber; i++) {
                players.add(new HumanPlayer(getString(scanner, "name of HumanPlayer №" + i)));
            }

            var rating = new Tournament(false, players).play(board);

            System.out.println("Winner is: " + rating.get(1).get(0).getName());
            for (int place = 2; rating.containsKey(place); place = (place - 1) * 2 + 1) {
                System.out.print(place + "'s place: ");
                List<Player> onThisPlace = rating.get(place);
                for (Player player : onThisPlace) {
                    if (player != onThisPlace.get(0)) {
                        System.out.print(", ");
                    }
                    System.out.print(player.getName());
                }
                System.out.println();
            }
        } else {
            Player player1 = new HumanPlayer("HumanPlayer");
            Player player2 = new HumanPlayer("RandomPlayer");

            int result;
            final Game game = new Game(false, player1, player2);
            do {
                board.clear();
                result = game.play(board);
            } while (result == 0);
            System.out.println("Game result: " + result);
        }
    }
}
