import util.Scanner;
import util.IntList;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public final class WsppSortedFirst {
    public static void main(final String[] args) {
        if (args.length < 2) {
            System.out.println("Specify input and output filenames as command-line arguments");
            return;
        }

        Map<String, IntList> words = new TreeMap<>();
        int wordNumber = 0;

        try (Scanner myScanner = new Scanner(new FileInputStream(args[0]))) {
            myScanner.setIsNextFunction(Scanner.WORD);
            Set<String> set = new HashSet<>();
            while (myScanner.hasNext()) {
                String word = myScanner.next();

                if (Scanner.isLineSeparator(word)) {
                    set = new HashSet<>();
                    continue;
                }

                word = word.toLowerCase();
                wordNumber++;

                IntList list = words.get(word);
                if (list == null) {
                    list = new IntList();
                    words.put(word, list);
                    list.append(0);
                }
                list.put(0, list.get(0) + 1);

                if (!set.contains(word)) {
                    set.add(word);
                    list.append(wordNumber);
                }
            }
        } catch (IOException e) {
            System.err.println("Error while reading input file: " + e.getMessage());
            return;
        }

        try {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(args[1], StandardCharsets.UTF_8))) {
                for (String string : words.keySet()) {
                    writer.write(string);
                    writer.write(" ");
                    writer.write(words.get(string).toString());
                    writer.newLine();
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Output file cannot be created: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error while output: " + e.getMessage());
        }
    }
}