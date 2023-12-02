public final class Sum {
    public static void main(final String[] args) {
        String inputStr = String.join(" ", args) + " ";

        int sum = 0;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < inputStr.length(); i++) {
            char c = inputStr.charAt(i);
            if ('0' <= c && c <= '9' || c == '-') {
                builder.append(c);
            } else if (!builder.isEmpty()) {
                sum += Integer.parseInt(builder.toString());
                builder.setLength(0);
            }
        }

        System.out.println(sum);
    }
}