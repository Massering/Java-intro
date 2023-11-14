import java.nio.charset.StandardCharsets;
import java.util.NoSuchElementException;
import java.io.*;


public class Main {
    // A B I J M
    public static void main(String[] args) {
        int a, b, n;
        Scanner scanner = new Scanner(System.in);
        a = Integer.parseInt(scanner.next());
        b = Integer.parseInt(scanner.next());
        n = Integer.parseInt(scanner.next());

        System.out.println(1 + 2 * ((n - b) / (b - a)));
    }
}


class Scanner implements AutoCloseable {
    static final char[] LINE_SEPARATORS = new char[]{'\r', '\n', '\u2028', '\u2029', '\u0085'};

    private final int INIT = 0;
    private final int HAS_NEXT = 1;
    private final int HAS_NEXT_LINE = 2;
    private final int HAS_NEXT_WORD = 3;
    private final int OTHER = 100;

    private final int BUFFER_LENGTH = 1 << 13;
    private final char[] buffer = new char[BUFFER_LENGTH];
    private final Reader reader;
    private int here = 0;
    private int size = 0;
    private int lastCall = INIT;

    public Scanner(InputStream stream) {
        reader = new InputStreamReader(stream, StandardCharsets.UTF_8);
    }

    public Scanner(Reader inputReader) {
        reader = inputReader;
    }

    public void close() {
        try {
            reader.close();
        } catch (IOException e) {
            System.err.println("Error while closing input file:" + e.getMessage());
        }
    }

    public boolean hasNext() {
        if (lastCall == HAS_NEXT) {
            return true;
        }

        while (isHereSafety()) {
            if (!isSpace(buffer[here])) {
                lastCall = HAS_NEXT;
                return true;
            }
            here++;
        }
        return false;
    }

    public String next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        lastCall = OTHER;

        StringBuilder builder = new StringBuilder();
        while (isHereSafety()) {
            char c = buffer[here];
            if (isSpace(c)) {
                break;
            }

            if (isLineSeparator(c)) {
                if (!builder.isEmpty()) {
                    break;
                }
                builder.append(c);
                here++;
                if (!isHereSafety()) {
                    break;
                }
                if (builder.charAt(0) == '\r' && buffer[here] == '\n') {
                    builder.append(buffer[here]);
                    here++;
                }
                break;
            }

            builder.append(c);
            here++;
        }
        return builder.toString();
    }

    public boolean isSpace(char c) {
        return Character.getType(c) == Character.SPACE_SEPARATOR;
    }

    public boolean hasNextLine() {
        if (lastCall == HAS_NEXT_LINE) {
            return true;
        }
        if (!isHereSafety()) {
            return false;
        }
        if (lastCall == INIT) {
            lastCall = HAS_NEXT_LINE;
            return true;
        }
        if (isLineSeparator(buffer[here])) {
            char sep = buffer[here];
            here++;
            if (!isHereSafety()) {
                return false;
            }
            if (sep == '\r' && buffer[here] == '\n') {
                here++;
                if (!isHereSafety()) {
                    return false;
                }
            }
        }
        lastCall = HAS_NEXT_LINE;
        return true;
    }

    public String nextLine() {
        if (!hasNextLine()) {
            throw new NoSuchElementException();
        }
        lastCall = OTHER;

        StringBuilder builder = new StringBuilder();
        while (!isLineSeparator(buffer[here])) {
            builder.append(buffer[here]);
            here++;
            if (!isHereSafety()) {
                break;
            }
        }
        return builder.toString();
    }

    public static boolean isLineSeparator(char c) {
        for (char sep : LINE_SEPARATORS) {
            if (sep == c) {
                return true;
            }
        }
        return false;
    }

    public boolean hasNextWord() {
        if (lastCall == HAS_NEXT_WORD) {
            return true;
        }
        while (isHereSafety()) {
            if (isPartOfWord(buffer[here]) || isLineSeparator(buffer[here])) {
                lastCall = HAS_NEXT_WORD;
                return true;
            }
            here++;
        }
        return false;
    }

    public String nextWord() {
        if (!hasNextWord()) {
            throw new NoSuchElementException();
        }
        lastCall = OTHER;

        StringBuilder builder = new StringBuilder();
        while (isHereSafety()) {
            char c = buffer[here];
            boolean isSep = isLineSeparator(c);
            if (!isPartOfWord(c) && !isSep) {
                break;
            }

            if (isSep) {
                if (builder.isEmpty()) {
                    builder.append(c);
                    c = buffer[++here];
                    if (!isHereSafety()) {
                        break;
                    }
                    if (builder.charAt(0) == '\r' && c == '\n') {
                        builder.append(c);
                        here++;
                    }
                }
                break;
            }

            builder.append(buffer[here]);
            here++;
        }
        return builder.toString();
    }

    public boolean isPartOfWord(char c) {
        int typeC = Character.getType(c);
        return typeC == Character.LOWERCASE_LETTER ||
                typeC == Character.UPPERCASE_LETTER ||
                typeC == Character.DASH_PUNCTUATION ||
                c == '\'';
    }

    private boolean updateBuffer() {
        try {
            size = reader.read(buffer, 0, BUFFER_LENGTH);
        } catch (IOException e) {
            return false;
        }
        if (size == -1) {
            return false;
        }
        here = 0;
        return true;
    }

    private boolean isHereSafety() {
        if (here >= size) {
            return updateBuffer();
        }
        return true;
    }

    public static boolean isLineSeparator(String s) {
        for (char c : s.toCharArray()) {
            if (!Scanner.isLineSeparator(c)) {
                return false;
            }
        }
        return true;
    }
}
