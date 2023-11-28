package game;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import util.IntList;

public abstract class Util {
    static IntList getInts(Scanner scanner, int numberOfInts, List<String> names) {
        return getInts(scanner, numberOfInts, names, 1, Integer.MAX_VALUE);
    }

    static IntList getInts(Scanner scanner, int numberOfInts, List<String> names, int min, int max) {
        IntList mins = new IntList();
        mins.fill(numberOfInts, min);
        IntList maxs = new IntList();
        maxs.fill(numberOfInts, max);
        return getInts(scanner, numberOfInts, names, mins, maxs);
    }

    static String getString(Scanner scanner, String whatToEnter) {
        boolean error;
        String line = null;
        do {
            error = false;
            System.out.println("Enter " + whatToEnter + ":");
            try {
                line = scanner.nextLine();
                if (line.isEmpty()) {
                    System.out.println("Please enter a non-empty string");
                    error = true;
                }
            } catch (NoSuchElementException e) {
                System.out.println("Please don't press Ctrl+D. It makes me cry");
                error = true;
            }
        } while (error);

        return line;
    }

    static IntList getInts(Scanner scanner, int numberOfInts, List<String> names, IntList mins, IntList maxs) {
        boolean error;
        IntList ints;

        String joinedNames = String.join(", ", names);
        do {
            error = false;
            ints = new IntList();
            Scanner scanLine = new Scanner(getString(scanner, joinedNames));
            for (int i = 0; i < numberOfInts; i++) {
                Integer x = getInt(scanLine, mins.get(i), maxs.get(i));
                if (x == null) {
                    System.out.print("Try again. ");
                    error = true;
                    break;
                }
                ints.append(x);
            }
            if (!error && scanLine.hasNext()) {
                System.out.println("Too many input numbers.");
                System.out.print("Try again. ");
                error = true;
            }
        } while (error);

        return ints;
    }

    static Integer getInt(Scanner scanner) {
        return getInt(scanner, 1, Integer.MAX_VALUE);
    }

    static Integer getInt(Scanner scanner, int min, int max) {
        String input;

        if (!scanner.hasNext()) {
            System.out.println("Too few input numbers.");
            return null;
        }

        input = scanner.next();
        for (int i = 0; i < input.length(); i++) {
            if (Character.getType(input.charAt(i)) != Character.DECIMAL_DIGIT_NUMBER && input.charAt(i) != '-') {
                System.out.println("`" + input + "` is not a number. Please, enter decimal digits.");
                return null;
            }
        }

        int x = Integer.parseInt(input);
        if (x < min) {
            System.out.println("Invalid number. The number is less than expected (" + x + " < " + min + ")");
            return null;
        } else if (x > max) {
            System.out.println("Invalid number. The number is larger than expected (" + x + " > " + max + ")");
            return null;
        }

        return x;
    }

    static double log2(int x) {
        return Math.log(x) / Math.log(2);
    }

    static int binPow(int n, int power) {
        if (power == 0) {
            return 1;
        }
        if (power % 2 == 0) {
            int x = binPow(n, power / 2);
            return x * x;
        }
        return binPow(n, power - 1) * n;
    }
}
