package expression;

import java.math.BigInteger;
import java.util.Objects;

public class Const implements MyExpression {
    private final Number value;

    public Const(int value) {
        this.value = value;
    }

    public Const(BigInteger value) {
        this.value = value;
    }

    @Override
    public int evaluate(int x) {
        return value.intValue();
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public BigInteger evaluate(BigInteger x) {
        return (BigInteger) value;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return value.intValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Const aConst = (Const) o;
        return Objects.equals(value, aConst.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value.intValue() * 17);
    }
}
