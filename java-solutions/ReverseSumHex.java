import java.io.*;
import java.util.ArrayList;

public class ReverseSumHex {
    public static void main(String[] args) {
        ArrayList<IntList> list = new ArrayList<>();
        int maxLength = 0;

        Scanner scanner = new Scanner(System.in);
        int linesCount = 0;

        IntList ints = new IntList();
        while (scanner.hasNext()) {
            String word = scanner.next();

            if (Scanner.isLineSeparator(word)) {
                maxLength = Math.max(maxLength, ints.size());
                list.add(ints);
                ints = new IntList();
            }
            else {
                ints.append(Integer.parseUnsignedInt(word, 16));
            }
        }
        if (ints.size() > 0) {
            maxLength = Math.max(maxLength, ints.size());
            list.add(ints);
        }

        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            countSums(writer, list, maxLength, linesCount);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void countSums(BufferedWriter writer, ArrayList<IntList> list, int maxLength, int linesCount) throws IOException {
        int[] upSum = new int[maxLength];   // сумма столбца сверху

        for (int i = 0; i < linesCount; i++) {
            for (int j = 0; j < list.get(i).size(); j++) {
                IntList temp = list.get(i);
                int x = temp.get(j);
                upSum[j] += x;
            }
            int[] dSum = new int[list.get(i).size()];

            if (list.get(i).size() > 0) {
                dSum[0] = upSum[0];
            }
            for (int j = 1; j < list.get(i).size(); j++) {
                dSum[j] += dSum[j - 1] + upSum[j];
            }
            print(writer, dSum);
        }
    }

    public static void print(BufferedWriter writer, int[] a) throws IOException {
        for (int i : a) {
            String number = Integer.toHexString(i);
            writer.write(number);
            writer.write(" ");
        }
        writer.newLine();
    }
}
