package md2html;

import util.Scanner;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Md2Html {
    static String PRE_MD = "```";

    // :NOTE: new class
    // :NOTE: -> final
    static String[][] MdToHtmlMap = {
            {"--", "s"},
            {"**", "strong"},
            {"__", "strong"},
            {"*", "em"},
            {"_", "em"},
            {PRE_MD, "pre"},
            {"`", "code"}
    };

    public static void main(String[] args) {
        StringBuilder builder = new StringBuilder();
        try (Scanner scanner = new Scanner(new FileInputStream(args[0]))) {
            String line;
            int h = 0;
            StringBuilder paraSB = new StringBuilder();
            String paraTag = "";

            while (scanner.hasNextLine()) {
                line = scanner.nextLine();

                if (line.isEmpty()) {
                    if (!paraSB.isEmpty()) {
                        replaceAndAddParagraph(paraSB.toString(), builder, paraTag);
                        paraSB.setLength(0);
                        builder.append(System.lineSeparator());
                    }
                } else if (paraSB.isEmpty()) {
                    int level = 0;
                    h = 0;
                    while (line.charAt(level) == '#') {
                        level++;
                    }
                    if (level > 0 && line.charAt(level) == ' ') {
                        paraSB.append(line.substring(level + 1));
                        if (level == 1 && h > 0) {
                            level = h;
                        } else {
                            h = level;
                        }
                        paraTag = "h" + level;
                    } else {
                        paraTag = "p";
                        paraSB.append(line);
                    }
                } else {
                    paraSB.append("\n").append(line);
                }
            }

            if (!paraSB.isEmpty()) {
                replaceAndAddParagraph(paraSB.toString(), builder, paraTag);
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Input error: " + e.getMessage());
        }

        try (Writer writer = new BufferedWriter(new FileWriter(args[1], StandardCharsets.UTF_8))) {
            writer.write(builder.toString());
        }
        catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Output error: " + e.getMessage());
        }
    }

    static void replaceAndAddParagraph(String oldParagraph, StringBuilder builder, CharSequence ParagraphTag) {
        builder.append(openTag(ParagraphTag.toString()));
        Map<String, Integer> opened = new HashMap<>();
        boolean stop = false;
        boolean slash = false;
        for (int i = 0; i < oldParagraph.length(); i++) {
            if (!slash) {
                for (String[] pat : MdToHtmlMap) {
                    String oldPat = pat[0];
                    String newPat = pat[1];

                    if (oldParagraph.startsWith(oldPat, i)) {
                        if (opened.containsKey(oldPat)) {
                            int ind = builder.indexOf(oldPat, opened.get(oldPat));
                            builder.replace(ind, ind + oldPat.length(), openTag(newPat));
                            builder.append(closeTag(newPat));
                            opened.remove(oldPat);
                        }

                        else {
                            if (oldPat.equals(PRE_MD)) {
                                int endInd = oldParagraph.indexOf(PRE_MD, i + 1);
                                builder.append(openTag(newPat));
                                if (endInd > i) {
                                    builder.append(oldParagraph.substring(i + 3, endInd)).append(closeTag(newPat));
                                    i = endInd;
                                }
                            }
                            else {
                                opened.put(oldPat, builder.length() - 1);
                                builder.append(oldPat);
                            }
                        }
                        i += oldPat.length() - 1;
                        stop = true;
                        break;
                    }
                }
            }
            slash = oldParagraph.charAt(i) == '\\';
            if (!stop && !slash) {
                if (oldParagraph.charAt(i) == '<') {
                    builder.append("&lt;");
                } else if (oldParagraph.charAt(i) == '>') {
                    builder.append("&gt;");
                } else if (oldParagraph.charAt(i) == '&') {
                    builder.append("&amp;");
                } else {
                    builder.append(oldParagraph.charAt(i));
                }
            }
            stop = false;
        }
        builder.append(closeTag(ParagraphTag.toString()));
    }

    static String openTag(String tag) {
        return new StringBuilder("<").append(tag).append(">").toString();
    }

    static String closeTag(String tag) {
        return new StringBuilder("</").append(tag).append(">").toString();
    }
}