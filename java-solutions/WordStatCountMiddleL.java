import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public final class WordStatCountMiddleL {
    public static void main(final String[] args) {
        if (args.length < 2) {
            System.out.println("Specify input and output filenames as command-line arguments");
            return;
        }

        Map<String, Integer> words = new HashMap<>();
        ArrayList<String> arrayWords = new ArrayList<>();

        try (Scanner myScanner = new Scanner(new FileInputStream(args[0]))) {
            String word;
            while (myScanner.hasNextWord()) {
                word = myScanner.nextWord().toLowerCase();
                addWord(word, words, arrayWords);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Input file not found: " + e.getMessage());
            return;
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return;
        }

        arrayWords.sort(new Comparator<>() {
            @Override
            public int compare(String o1, String o2) {
                return words.get(o1) - words.get(o2);
            }
        });

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(args[1],
                StandardCharsets.UTF_8))) {
            for (String string : arrayWords) {
                writer.write(string);
                writer.write(" ");
                writer.write(Integer.toString(words.get(string)));
                writer.newLine();
            }
        } catch (FileNotFoundException e) {
            System.err.println("Output file cannot be created: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error while output: " + e.getMessage());
        }
    }

    public static void addWord(String word, Map<String, Integer> words, ArrayList<String> arrayWords) {
        if (word.length() < 5) {
            return;
        }
        word = word.substring(2, word.length() - 2);
        int value = words.getOrDefault(word, 0);
        if (value == 0) {
            arrayWords.add(word);
        }
        words.put(word, value + 1);
    }
}