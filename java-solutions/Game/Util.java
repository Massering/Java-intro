package Game;

import java.util.List;
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

    static IntList getInts(Scanner scanner, int numberOfInts, List<String> names, IntList mins, IntList maxs) {
        boolean error;
        IntList ints;

        String joinedNames = String.join(", ", names);

        do {
            System.out.println("Enter " + joinedNames + ":");
            error = false;
            ints = new IntList();
            Scanner scanLine = new Scanner(scanner.nextLine());
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
        }
        else if (x > max) {
            System.out.println("Invalid number. The number is larger than expected (" + x + " > " + max + ")");
            return null;
        }

        return x;
    }
}
