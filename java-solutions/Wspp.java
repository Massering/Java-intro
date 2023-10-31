import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;

public final class Wspp {
    public static void main(final String[] args) {
        if (args.length < 2) {
            System.out.println("Specify input and output filenames as command-line arguments");
            return;
        }

        HashMap<String, IntList> words = new HashMap<>();
        ArrayList<String> arrayWords = new ArrayList<>();
        int wordNumber = 0;

        try (Scanner myScanner = new Scanner(new FileInputStream(args[0]))) {
            String word;
            while (myScanner.hasNextWord()) {
                word = myScanner.nextWord();
                if (!Scanner.isLineSeparator(word)) {
                    wordNumber = addWord(word.toLowerCase(), words, arrayWords, wordNumber);
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(args[1],
                StandardCharsets.UTF_8))) {
            for (String string : arrayWords) {
                writer.write(string);
                writer.write(" ");
                writer.write(Integer.toString(words.get(string).length()));
                writer.write(" ");
                writer.write(words.get(string).toString());
                writer.newLine();
            }
        } catch (FileNotFoundException e) {
            System.err.println("Output file cannot be created: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error while output: " + e.getMessage());
        }
    }

    public static int addWord(String word, HashMap<String, IntList> words, ArrayList<String> arrayWords, int wordNumber) {
        IntList list = words.get(word);
        if (list == null) {
            arrayWords.add(word);
            list = new IntList();
            words.put(word, list);
        }
        wordNumber++;
        list.append(wordNumber);
        return wordNumber;
    }
}