import java.io.*;
import java.util.ArrayList;

public class Reverse {
    public static void main(String[] args) {
        ArrayList<IntList> list = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        StringBuilder builder = new StringBuilder();

        IntList ints = new IntList();
        while (scanner.hasNext()) {
            String word = scanner.next();
            if (Scanner.isLineSeparator(word)) {
                list.add(ints);
                ints = new IntList();
            }

            parseWord(word, ints, builder);
        }
        if (ints.size() > 0) {
            list.add(ints);
        }

        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            for (int i = list.size() - 1; i >= 0; i--) {
                printReversed(writer, list.get(i));
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void parseWord(String word, IntList ints, StringBuilder builder) {
        for (char c : word.toCharArray()) {
            int typeC = Character.getType(c);
            if (typeC == Character.DECIMAL_DIGIT_NUMBER || typeC == Character.DASH_PUNCTUATION) {
                builder.append(c);
            } else if (!builder.isEmpty()) {
                ints.append(Integer.parseInt(builder.toString()));
                builder.setLength(0);
            }
        }
        if (!builder.isEmpty()) {
            ints.append(Integer.parseInt(builder.toString()));
            builder.setLength(0);
        }
    }

    public static void printReversed(BufferedWriter writer, IntList a) throws IOException {
        for (int i = a.size() - 1; i >= 0; i--) {
            String number = Integer.toString(a.get(i));
            writer.write(number);
            writer.write(" ");
        }
        writer.newLine();
    }
}
