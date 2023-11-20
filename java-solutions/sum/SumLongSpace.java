package sum;

public final class SumLongSpace {
    public static void main(final String[] args) {
        long sum = 0;
        StringBuilder builder = new StringBuilder();
        for (String line : args) {
            for (int i = 0; i <= line.length(); i++) {
                if (i != line.length() && !isSpace(line.charAt(i))) {
                    builder.append(line.charAt(i));
                }
                else if (!builder.isEmpty()) {
                    sum += Long.parseLong(builder.toString());
                    builder.setLength(0);
                }
            }
        }

        System.out.println(sum);
    }

    public static boolean isSpace(char c) {
        return Character.getType(c) == Character.SPACE_SEPARATOR;
    }
}