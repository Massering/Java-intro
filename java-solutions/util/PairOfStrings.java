package util;

public final class PairOfStrings {
    public final String first;
    public final String second;

    public PairOfStrings(final String first, final String second) {
        this.first = first;
        this.second = second;
    }

    public String first() {
        return first;
    }

    public String second() {
        return second;
    }
}
