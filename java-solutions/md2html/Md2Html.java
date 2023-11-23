package md2html;

import util.Scanner;
import util.PairOfStrings;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Md2Html {
    static String PRE_MD = "```";

    static final PairOfStrings[] MarkToTagMap = new PairOfStrings[]{
            new PairOfStrings("--", "s"),
            new PairOfStrings("**", "strong"),
            new PairOfStrings("__", "strong"),
            new PairOfStrings("*", "em"),
            new PairOfStrings("_", "em"),
            new PairOfStrings(PRE_MD, "pre"),
            new PairOfStrings("`", "code")
    };

    static final Map<Character, String> htmlEscapeMap = new HashMap<>() {{
        put('<', "&lt;");
        put('>', "&gt;");
        put('&', "&amp;");
    }};

    public static void main(String[] args) {
        StringBuilder builder = new StringBuilder();
        try (Scanner scanner = new Scanner(new FileInputStream(args[0]))) {
            String line;
            StringBuilder paraSB = new StringBuilder();

            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                System.out.println(line);

                if (line.isEmpty()) {
                    if (!paraSB.isEmpty()) {
                        replaceAndAddParagraph(paraSB.toString(), builder);
                        paraSB.setLength(0);
                        builder.append(System.lineSeparator());
                    }
                } else {
                    if (!paraSB.isEmpty()) {
                        paraSB.append(System.lineSeparator());
                    }
                    paraSB.append(line);
                }
            }
            if (!paraSB.isEmpty()) {
                replaceAndAddParagraph(paraSB.toString(), builder);
            }

        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Input error: " + e.getMessage());
        }

        try (Writer writer = new BufferedWriter(new FileWriter(args[1], StandardCharsets.UTF_8))) {
            writer.write(builder.toString());
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Output error: " + e.getMessage());
        }
    }

    static void replaceAndAddParagraph(String oldParagraph, StringBuilder builder) {
        int level = 0;
        while (oldParagraph.charAt(level) == '#') {
            level++;
        }
        String paragraphTag;
        if (level > 0 && oldParagraph.charAt(level) == ' ') {
            paragraphTag = "h" + level;
            level += 1;
        } else {
            paragraphTag = "p";
            level = 0;
        }
        builder.append(openTag(paragraphTag));

        final Map<String, Integer> opened = new HashMap<>();
        boolean slash = false;
        for (int i = level; i < oldParagraph.length(); i++) {
            boolean found = false;
            if (!slash) {
                for (var pat : MarkToTagMap) {
                    String oldPat = pat.first;
                    String newPat = pat.second;

                    if (oldParagraph.startsWith(oldPat, i)) {
                        if (opened.containsKey(oldPat)) {
                            int ind = builder.indexOf(oldPat, opened.get(oldPat));
                            builder.replace(ind, ind + oldPat.length(), openTag(newPat));
                            builder.append(closeTag(newPat));
                            opened.remove(oldPat);
                        } else {
                            if (oldPat.equals(PRE_MD)) {
                                int endInd = oldParagraph.indexOf(PRE_MD, i + 1);
                                builder.append(openTag(newPat));
                                if (endInd > i) {
                                    builder.append(oldParagraph.substring(i + 3, endInd)).append(closeTag(newPat));
                                    i = endInd;
                                }
                            } else {
                                opened.put(oldPat, builder.length() - 1);
                                builder.append(oldPat);
                            }
                        }
                        i += oldPat.length() - 1;
                        found = true;
                        break;
                    }
                }
            }
            slash = oldParagraph.charAt(i) == '\\';
            if (!found && !slash) {
                char c = oldParagraph.charAt(i);
                builder.append(htmlEscapeMap.getOrDefault(c, String.valueOf(c)));
            }
        }
        builder.append(closeTag(paragraphTag));
    }

    static String openTag(String tag) {
        return new StringBuilder("<").append(tag).append(">").toString();
    }

    static String closeTag(String tag) {
        return new StringBuilder("</").append(tag).append(">").toString();
    }
}