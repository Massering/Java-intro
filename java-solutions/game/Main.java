package game;

import util.IntList;

import java.util.List;
import java.util.Scanner;

import static game.Util.getInts;

public class Main {

    public static void main(String[] args) {
        Player player1 = new RandomPlayer();
        Player player2 = new HumanPlayer();

        Scanner scanner = new Scanner(System.in);
        IntList arr = getInts(scanner, 3, List.of("N", "M", "K"));
        int n = arr.get(0);
        int m = arr.get(1);
        int k = arr.get(2);

        Board board = new TicTacToeBoard(n, m, k);

        final Game game = new Game(true, player1, player2);
        int result;
        do {
            result = game.play(board);
            System.out.println("Game result: " + result);
        } while (result == -1);
    }
}
