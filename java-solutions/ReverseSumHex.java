import util.Scanner;
import util.IntList;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReverseSumHex {
    public static void main(String[] args) {
        ArrayList<IntList> list = new ArrayList<>();
        int maxLength = 0;

        try (Scanner scanner = new Scanner(System.in)) {
            IntList ints = new IntList();
            while (scanner.hasNext()) {
                String word = scanner.next();

                if (Scanner.isLineSeparator(word)) {
                    maxLength = Math.max(maxLength, ints.size());
                    list.add(ints);
                    ints = new IntList();
                } else {
                    ints.append(Integer.parseUnsignedInt(word, 16));
                }
            }
            if (ints.size() > 0) {
                maxLength = Math.max(maxLength, ints.size());
                list.add(ints);
            }
        } catch (IOException e) {
            System.err.println("Error while reading input file: " + e.getMessage());
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            countSums(writer, list, maxLength);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void countSums(BufferedWriter writer, List<IntList> list, int maxLength) throws IOException {
        int[] upSum = new int[maxLength];   // сумма столбца сверху

        for (IntList intList : list) {
            for (int j = 0; j < intList.size(); j++) {
                upSum[j] += intList.get(j);
            }
            int[] dSum = new int[intList.size()];

            if (intList.size() > 0) {
                dSum[0] = upSum[0];
            }
            for (int j = 1; j < intList.size(); j++) {
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
